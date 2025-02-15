package com.ruoyi.commission.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.commission.domain.CommissionUserSalesSummary;
import com.ruoyi.commission.service.ICommissionUserSalesSummaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户销售额管理Controller
 * 
 * @author xing
 * @date 2025-02-14
 */
@RestController
@RequestMapping("/commission/commission_user_sales_summary")
public class CommissionUserSalesSummaryController extends BaseController
{
    @Autowired
    private ICommissionUserSalesSummaryService commissionUserSalesSummaryService;

    /**
     * 查询用户销售额管理列表
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_user_sales_summary:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommissionUserSalesSummary commissionUserSalesSummary)
    {
        startPage();
        List<CommissionUserSalesSummary> list = commissionUserSalesSummaryService.selectCommissionUserSalesSummaryList(commissionUserSalesSummary);
        return getDataTable(list);
    }

    /**
     * 导出用户销售额管理列表
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_user_sales_summary:export')")
    @Log(title = "用户销售额管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommissionUserSalesSummary commissionUserSalesSummary)
    {
        List<CommissionUserSalesSummary> list = commissionUserSalesSummaryService.selectCommissionUserSalesSummaryList(commissionUserSalesSummary);
        ExcelUtil<CommissionUserSalesSummary> util = new ExcelUtil<CommissionUserSalesSummary>(CommissionUserSalesSummary.class);
        util.exportExcel(response, list, "用户销售额管理数据");
    }

    /**
     * 获取用户销售额管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_user_sales_summary:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(commissionUserSalesSummaryService.selectCommissionUserSalesSummaryByUserId(userId));
    }

    /**
     * 新增用户销售额管理
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_user_sales_summary:add')")
    @Log(title = "用户销售额管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommissionUserSalesSummary commissionUserSalesSummary)
    {
        return toAjax(commissionUserSalesSummaryService.insertCommissionUserSalesSummary(commissionUserSalesSummary));
    }

    /**
     * 修改用户销售额管理
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_user_sales_summary:edit')")
    @Log(title = "用户销售额管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommissionUserSalesSummary commissionUserSalesSummary)
    {
        return toAjax(commissionUserSalesSummaryService.updateCommissionUserSalesSummary(commissionUserSalesSummary));
    }

    /**
     * 删除用户销售额管理
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_user_sales_summary:remove')")
    @Log(title = "用户销售额管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(commissionUserSalesSummaryService.deleteCommissionUserSalesSummaryByUserIds(userIds));
    }
}
