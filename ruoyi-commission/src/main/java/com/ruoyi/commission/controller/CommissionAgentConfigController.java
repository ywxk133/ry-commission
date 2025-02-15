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
import com.ruoyi.commission.domain.CommissionAgentConfig;
import com.ruoyi.commission.service.ICommissionAgentConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 代理配置管理Controller
 * 
 * @author xing
 * @date 2025-02-15
 */
@RestController
@RequestMapping("/commission/commission_agent_config")
public class CommissionAgentConfigController extends BaseController
{
    @Autowired
    private ICommissionAgentConfigService commissionAgentConfigService;

    /**
     * 查询代理配置管理列表
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_agent_config:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommissionAgentConfig commissionAgentConfig)
    {
        startPage();
        List<CommissionAgentConfig> list = commissionAgentConfigService.selectCommissionAgentConfigList(commissionAgentConfig);
        return getDataTable(list);
    }

    /**
     * 导出代理配置管理列表
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_agent_config:export')")
    @Log(title = "代理配置管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommissionAgentConfig commissionAgentConfig)
    {
        List<CommissionAgentConfig> list = commissionAgentConfigService.selectCommissionAgentConfigList(commissionAgentConfig);
        ExcelUtil<CommissionAgentConfig> util = new ExcelUtil<CommissionAgentConfig>(CommissionAgentConfig.class);
        util.exportExcel(response, list, "代理配置管理数据");
    }

    /**
     * 获取代理配置管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_agent_config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(commissionAgentConfigService.selectCommissionAgentConfigById(id));
    }

    /**
     * 新增代理配置管理
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_agent_config:add')")
    @Log(title = "代理配置管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommissionAgentConfig commissionAgentConfig)
    {
        return toAjax(commissionAgentConfigService.insertCommissionAgentConfig(commissionAgentConfig));
    }

    /**
     * 修改代理配置管理
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_agent_config:edit')")
    @Log(title = "代理配置管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommissionAgentConfig commissionAgentConfig)
    {
        return toAjax(commissionAgentConfigService.updateCommissionAgentConfig(commissionAgentConfig));
    }

    /**
     * 删除代理配置管理
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_agent_config:remove')")
    @Log(title = "代理配置管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(commissionAgentConfigService.deleteCommissionAgentConfigByIds(ids));
    }
}
