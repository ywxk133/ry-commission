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
import com.ruoyi.commission.domain.CommissionLevelUpgradeConfig;
import com.ruoyi.commission.service.ICommissionLevelUpgradeConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 记录各级别的升级条件、团队极差奖励等信息Controller
 * 
 * @author xing
 * @date 2025-02-14
 */
@RestController
@RequestMapping("/commission/level_upgrade_config")
public class CommissionLevelUpgradeConfigController extends BaseController
{
    @Autowired
    private ICommissionLevelUpgradeConfigService commissionLevelUpgradeConfigService;

    /**
     * 查询记录各级别的升级条件、团队极差奖励等信息列表
     */
    @PreAuthorize("@ss.hasPermi('commission:level_upgrade_config:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommissionLevelUpgradeConfig commissionLevelUpgradeConfig)
    {
        startPage();
        List<CommissionLevelUpgradeConfig> list = commissionLevelUpgradeConfigService.selectCommissionLevelUpgradeConfigList(commissionLevelUpgradeConfig);
        return getDataTable(list);
    }

    /**
     * 导出记录各级别的升级条件、团队极差奖励等信息列表
     */
    @PreAuthorize("@ss.hasPermi('commission:level_upgrade_config:export')")
    @Log(title = "记录各级别的升级条件、团队极差奖励等信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommissionLevelUpgradeConfig commissionLevelUpgradeConfig)
    {
        List<CommissionLevelUpgradeConfig> list = commissionLevelUpgradeConfigService.selectCommissionLevelUpgradeConfigList(commissionLevelUpgradeConfig);
        ExcelUtil<CommissionLevelUpgradeConfig> util = new ExcelUtil<CommissionLevelUpgradeConfig>(CommissionLevelUpgradeConfig.class);
        util.exportExcel(response, list, "记录各级别的升级条件、团队极差奖励等信息数据");
    }

    /**
     * 获取记录各级别的升级条件、团队极差奖励等信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('commission:level_upgrade_config:query')")
    @GetMapping(value = "/{rankLevel}")
    public AjaxResult getInfo(@PathVariable("rankLevel") String rankLevel)
    {
        return success(commissionLevelUpgradeConfigService.selectCommissionLevelUpgradeConfigByRankLevel(rankLevel));
    }

    /**
     * 新增记录各级别的升级条件、团队极差奖励等信息
     */
    @PreAuthorize("@ss.hasPermi('commission:level_upgrade_config:add')")
    @Log(title = "记录各级别的升级条件、团队极差奖励等信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommissionLevelUpgradeConfig commissionLevelUpgradeConfig)
    {
        return toAjax(commissionLevelUpgradeConfigService.insertCommissionLevelUpgradeConfig(commissionLevelUpgradeConfig));
    }

    /**
     * 修改记录各级别的升级条件、团队极差奖励等信息
     */
    @PreAuthorize("@ss.hasPermi('commission:level_upgrade_config:edit')")
    @Log(title = "记录各级别的升级条件、团队极差奖励等信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommissionLevelUpgradeConfig commissionLevelUpgradeConfig)
    {
        return toAjax(commissionLevelUpgradeConfigService.updateCommissionLevelUpgradeConfig(commissionLevelUpgradeConfig));
    }

    /**
     * 删除记录各级别的升级条件、团队极差奖励等信息
     */
    @PreAuthorize("@ss.hasPermi('commission:level_upgrade_config:remove')")
    @Log(title = "记录各级别的升级条件、团队极差奖励等信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rankLevels}")
    public AjaxResult remove(@PathVariable String[] rankLevels)
    {
        return toAjax(commissionLevelUpgradeConfigService.deleteCommissionLevelUpgradeConfigByRankLevels(rankLevels));
    }
}
