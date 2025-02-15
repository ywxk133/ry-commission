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
import com.ruoyi.commission.domain.UserAgent;
import com.ruoyi.commission.service.IUserAgentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户代理管理Controller
 * 
 * @author xing
 * @date 2025-02-14
 */
@RestController
@RequestMapping("/commission/agent")
public class UserAgentController extends BaseController
{
    @Autowired
    private IUserAgentService userAgentService;

    /**
     * 查询用户代理管理列表
     */
    @PreAuthorize("@ss.hasPermi('commission:agent:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserAgent userAgent)
    {
        startPage();
        List<UserAgent> list = userAgentService.selectUserAgentList(userAgent);
        return getDataTable(list);
    }

    /**
     * 导出用户代理管理列表
     */
    @PreAuthorize("@ss.hasPermi('commission:agent:export')")
    @Log(title = "用户代理管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserAgent userAgent)
    {
        List<UserAgent> list = userAgentService.selectUserAgentList(userAgent);
        ExcelUtil<UserAgent> util = new ExcelUtil<UserAgent>(UserAgent.class);
        util.exportExcel(response, list, "用户代理管理数据");
    }

    /**
     * 获取用户代理管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('commission:agent:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(userAgentService.selectUserAgentByUserId(userId));
    }

    /**
     * 新增用户代理管理
     */
    @PreAuthorize("@ss.hasPermi('commission:agent:add')")
    @Log(title = "用户代理管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserAgent userAgent)
    {
        return toAjax(userAgentService.insertUserAgent(userAgent));
    }

    /**
     * 修改用户代理管理
     */
    @PreAuthorize("@ss.hasPermi('commission:agent:edit')")
    @Log(title = "用户代理管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserAgent userAgent)
    {
        UserAgent userAgent1 = userAgentService.selectUserAgentByUserId(userAgent.getUserId());
        logger.info("userAgent :{}",userAgent);
        logger.info("userAgent1 :{}",userAgent1);
        if(userAgent1 == null){
            return toAjax(userAgentService.insertUserAgent(userAgent));
        }

        return toAjax(userAgentService.updateUserAgent(userAgent));
    }

    /**
     * 删除用户代理管理
     */
    @PreAuthorize("@ss.hasPermi('commission:agent:remove')")
    @Log(title = "用户代理管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userAgentService.deleteUserAgentByUserIds(userIds));
    }
}
