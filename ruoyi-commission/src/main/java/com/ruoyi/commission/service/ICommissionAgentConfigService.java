package com.ruoyi.commission.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.commission.domain.CommissionAgentConfig;

/**
 * 代理配置管理Service接口
 * 
 * @author xing
 * @date 2025-02-15
 */
public interface ICommissionAgentConfigService 
{
    /**
     * 查询代理配置管理
     * 
     * @param id 代理配置管理主键
     * @return 代理配置管理
     */
    public CommissionAgentConfig selectCommissionAgentConfigById(Long id);

    /**
     * 查询代理配置管理列表
     * 
     * @param commissionAgentConfig 代理配置管理
     * @return 代理配置管理集合
     */
    public List<CommissionAgentConfig> selectCommissionAgentConfigList(CommissionAgentConfig commissionAgentConfig);

    /**
     * 新增代理配置管理
     * 
     * @param commissionAgentConfig 代理配置管理
     * @return 结果
     */
    public int insertCommissionAgentConfig(CommissionAgentConfig commissionAgentConfig);

    /**
     * 修改代理配置管理
     * 
     * @param commissionAgentConfig 代理配置管理
     * @return 结果
     */
    public int updateCommissionAgentConfig(CommissionAgentConfig commissionAgentConfig);

    /**
     * 批量删除代理配置管理
     * 
     * @param ids 需要删除的代理配置管理主键集合
     * @return 结果
     */
    public int deleteCommissionAgentConfigByIds(Long[] ids);

    /**
     * 删除代理配置管理信息
     * 
     * @param id 代理配置管理主键
     * @return 结果
     */
    public int deleteCommissionAgentConfigById(Long id);

    Map<String,CommissionAgentConfig> queryingConfigurationInformation();
}
