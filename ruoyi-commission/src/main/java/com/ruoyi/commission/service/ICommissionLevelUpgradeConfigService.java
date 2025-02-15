package com.ruoyi.commission.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.commission.domain.CommissionLevelUpgradeConfig;
import com.ruoyi.commission.enums.RankLevel;

/**
 * 记录各级别的升级条件、团队极差奖励等信息Service接口
 * 
 * @author xing
 * @date 2025-02-14
 */
public interface ICommissionLevelUpgradeConfigService 
{
    /**
     * 查询记录各级别的升级条件、团队极差奖励等信息
     * 
     * @param rankLevel 记录各级别的升级条件、团队极差奖励等信息主键
     * @return 记录各级别的升级条件、团队极差奖励等信息
     */
    public CommissionLevelUpgradeConfig selectCommissionLevelUpgradeConfigByRankLevel(String rankLevel);

    /**
     * 查询记录各级别的升级条件、团队极差奖励等信息列表
     * 
     * @param commissionLevelUpgradeConfig 记录各级别的升级条件、团队极差奖励等信息
     * @return 记录各级别的升级条件、团队极差奖励等信息集合
     */
    public List<CommissionLevelUpgradeConfig> selectCommissionLevelUpgradeConfigList(CommissionLevelUpgradeConfig commissionLevelUpgradeConfig);

    /**
     * 新增记录各级别的升级条件、团队极差奖励等信息
     * 
     * @param commissionLevelUpgradeConfig 记录各级别的升级条件、团队极差奖励等信息
     * @return 结果
     */
    public int insertCommissionLevelUpgradeConfig(CommissionLevelUpgradeConfig commissionLevelUpgradeConfig);

    /**
     * 修改记录各级别的升级条件、团队极差奖励等信息
     * 
     * @param commissionLevelUpgradeConfig 记录各级别的升级条件、团队极差奖励等信息
     * @return 结果
     */
    public int updateCommissionLevelUpgradeConfig(CommissionLevelUpgradeConfig commissionLevelUpgradeConfig);

    /**
     * 批量删除记录各级别的升级条件、团队极差奖励等信息
     * 
     * @param rankLevels 需要删除的记录各级别的升级条件、团队极差奖励等信息主键集合
     * @return 结果
     */
    public int deleteCommissionLevelUpgradeConfigByRankLevels(String[] rankLevels);

    /**
     * 删除记录各级别的升级条件、团队极差奖励等信息信息
     * 
     * @param rankLevel 记录各级别的升级条件、团队极差奖励等信息主键
     * @return 结果
     */
    public int deleteCommissionLevelUpgradeConfigByRankLevel(String rankLevel);

    Boolean userUpgradeJudgment(Long userId, RankLevel rankLevel);
    Boolean userUpgradeJudgment(Long userId, String rankLevel);

    Map<String, CommissionLevelUpgradeConfig> levelConfiguration();
}
