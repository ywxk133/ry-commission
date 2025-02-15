package com.ruoyi.commission.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.commission.domain.CommissionLevelUpgradeConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 记录各级别的升级条件、团队极差奖励等信息Mapper接口
 * 
 * @author xing
 * @date 2025-02-14
 */
@Mapper
public interface CommissionLevelUpgradeConfigMapper 
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
     * 删除记录各级别的升级条件、团队极差奖励等信息
     * 
     * @param rankLevel 记录各级别的升级条件、团队极差奖励等信息主键
     * @return 结果
     */
    public int deleteCommissionLevelUpgradeConfigByRankLevel(String rankLevel);

    /**
     * 批量删除记录各级别的升级条件、团队极差奖励等信息
     * 
     * @param rankLevels 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommissionLevelUpgradeConfigByRankLevels(String[] rankLevels);

    CommissionLevelUpgradeConfig exampleQueryUserLevelUpgradeInformation(Long userId);

    List<CommissionLevelUpgradeConfig>  levelConfiguration();
}
