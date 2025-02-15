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
import com.ruoyi.commission.domain.CommissionMembershipConfig;
import com.ruoyi.commission.service.ICommissionMembershipConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会员配置管理Controller
 * 
 * @author xing
 * @date 2025-02-14
 */
@RestController
@RequestMapping("/commission/commission_membership_config")
public class CommissionMembershipConfigController extends BaseController
{
    @Autowired
    private ICommissionMembershipConfigService commissionMembershipConfigService;

    /**
     * 查询会员配置管理列表
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_membership_config:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommissionMembershipConfig commissionMembershipConfig)
    {
        startPage();
        List<CommissionMembershipConfig> list = commissionMembershipConfigService.selectCommissionMembershipConfigList(commissionMembershipConfig);
        return getDataTable(list);
    }

    /**
     * 导出会员配置管理列表
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_membership_config:export')")
    @Log(title = "会员配置管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommissionMembershipConfig commissionMembershipConfig)
    {
        List<CommissionMembershipConfig> list = commissionMembershipConfigService.selectCommissionMembershipConfigList(commissionMembershipConfig);
        ExcelUtil<CommissionMembershipConfig> util = new ExcelUtil<CommissionMembershipConfig>(CommissionMembershipConfig.class);
        util.exportExcel(response, list, "会员配置管理数据");
    }

    /**
     * 获取会员配置管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_membership_config:query')")
    @GetMapping(value = "/{membershipLevel}")
    public AjaxResult getInfo(@PathVariable("membershipLevel") String membershipLevel)
    {
        return success(commissionMembershipConfigService.selectCommissionMembershipConfigByMembershipLevel(membershipLevel));
    }

    /**
     * 新增会员配置管理
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_membership_config:add')")
    @Log(title = "会员配置管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommissionMembershipConfig commissionMembershipConfig)
    {
        return toAjax(commissionMembershipConfigService.insertCommissionMembershipConfig(commissionMembershipConfig));
    }

    /**
     * 修改会员配置管理
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_membership_config:edit')")
    @Log(title = "会员配置管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommissionMembershipConfig commissionMembershipConfig)
    {
        return toAjax(commissionMembershipConfigService.updateCommissionMembershipConfig(commissionMembershipConfig));
    }

    /**
     * 删除会员配置管理
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_membership_config:remove')")
    @Log(title = "会员配置管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{membershipLevels}")
    public AjaxResult remove(@PathVariable String[] membershipLevels)
    {
        return toAjax(commissionMembershipConfigService.deleteCommissionMembershipConfigByMembershipLevels(membershipLevels));
    }
}
