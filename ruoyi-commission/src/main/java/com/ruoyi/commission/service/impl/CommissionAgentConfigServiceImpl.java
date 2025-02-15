package com.ruoyi.commission.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.commission.mapper.CommissionAgentConfigMapper;
import com.ruoyi.commission.domain.CommissionAgentConfig;
import com.ruoyi.commission.service.ICommissionAgentConfigService;

/**
 * 代理配置管理Service业务层处理
 * 
 * @author xing
 * @date 2025-02-15
 */
@Service
public class CommissionAgentConfigServiceImpl implements ICommissionAgentConfigService 
{
    @Autowired
    private CommissionAgentConfigMapper commissionAgentConfigMapper;

    /**
     * 查询代理配置管理
     * 
     * @param id 代理配置管理主键
     * @return 代理配置管理
     */
    @Override
    public CommissionAgentConfig selectCommissionAgentConfigById(Long id)
    {
        return commissionAgentConfigMapper.selectCommissionAgentConfigById(id);
    }

    /**
     * 查询代理配置管理列表
     * 
     * @param commissionAgentConfig 代理配置管理
     * @return 代理配置管理
     */
    @Override
    public List<CommissionAgentConfig> selectCommissionAgentConfigList(CommissionAgentConfig commissionAgentConfig)
    {
        return commissionAgentConfigMapper.selectCommissionAgentConfigList(commissionAgentConfig);
    }

    /**
     * 新增代理配置管理
     * 
     * @param commissionAgentConfig 代理配置管理
     * @return 结果
     */
    @Override
    public int insertCommissionAgentConfig(CommissionAgentConfig commissionAgentConfig)
    {
        commissionAgentConfig.setCreateTime(DateUtils.getNowDate());
        return commissionAgentConfigMapper.insertCommissionAgentConfig(commissionAgentConfig);
    }

    /**
     * 修改代理配置管理
     * 
     * @param commissionAgentConfig 代理配置管理
     * @return 结果
     */
    @Override
    public int updateCommissionAgentConfig(CommissionAgentConfig commissionAgentConfig)
    {
        commissionAgentConfig.setUpdateTime(DateUtils.getNowDate());
        return commissionAgentConfigMapper.updateCommissionAgentConfig(commissionAgentConfig);
    }

    /**
     * 批量删除代理配置管理
     * 
     * @param ids 需要删除的代理配置管理主键
     * @return 结果
     */
    @Override
    public int deleteCommissionAgentConfigByIds(Long[] ids)
    {
        return commissionAgentConfigMapper.deleteCommissionAgentConfigByIds(ids);
    }

    /**
     * 删除代理配置管理信息
     * 
     * @param id 代理配置管理主键
     * @return 结果
     */
    @Override
    public int deleteCommissionAgentConfigById(Long id)
    {
        return commissionAgentConfigMapper.deleteCommissionAgentConfigById(id);
    }

    @Override
    public Map<String,CommissionAgentConfig> queryingConfigurationInformation() {
        List<CommissionAgentConfig> commissionAgentConfigs = selectCommissionAgentConfigList(null);
        return null;
    }
}
