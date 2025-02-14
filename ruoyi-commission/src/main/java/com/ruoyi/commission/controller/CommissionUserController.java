package com.ruoyi.commission.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.commission.domain.CommissionAccountBalance;
import com.ruoyi.commission.service.ICommissionAccountBalanceService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.commission.domain.CommissionUser;
import com.ruoyi.commission.service.ICommissionUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户管理Controller
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
@RestController
@RequestMapping("/commission/commission_user")
public class CommissionUserController extends BaseController
{
    @Autowired
    private ICommissionUserService commissionUserService;
    @Autowired
    private ICommissionAccountBalanceService commissionAccountBalanceService;
    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_user:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommissionUser commissionUser)
    {
        startPage();
        List<CommissionUser> list = commissionUserService.selectCommissionUserList(commissionUser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_user:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommissionUser commissionUser)
    {
        List<CommissionUser> list = commissionUserService.selectCommissionUserList(commissionUser);
        ExcelUtil<CommissionUser> util = new ExcelUtil<CommissionUser>(CommissionUser.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_user:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(commissionUserService.selectCommissionUserByUserId(userId));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommissionUser commissionUser)
    {
            // 新用户无资格设置用户级别
            commissionUser.setRankLevel(null);
            // 1. 加密密码
            commissionUser.setPassword(SecurityUtils.encryptPassword(commissionUser.getPassword()));

            // 2. 插入用户 并创建余额
            return toAjax(commissionUserService.insertCommissionUser(commissionUser));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommissionUser commissionUser)
    {
        // 无法直接修改密码
        commissionUser.setPassword(null);
        return toAjax(commissionUserService.updateCommissionUser(commissionUser));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(commissionUserService.deleteCommissionUserByUserIds(userIds));
    }
}
