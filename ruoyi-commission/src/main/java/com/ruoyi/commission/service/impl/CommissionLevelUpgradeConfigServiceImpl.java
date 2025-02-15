package com.ruoyi.commission.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.commission.enums.RankLevel;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.commission.mapper.CommissionLevelUpgradeConfigMapper;
import com.ruoyi.commission.domain.CommissionLevelUpgradeConfig;
import com.ruoyi.commission.service.ICommissionLevelUpgradeConfigService;

/**
 * 记录各级别的升级条件、团队极差奖励等信息Service业务层处理
 * 
 * @author xing
 * @date 2025-02-14
 */
@Service
public class CommissionLevelUpgradeConfigServiceImpl implements ICommissionLevelUpgradeConfigService 
{
    private static final Logger log = LoggerFactory.getLogger(CommissionLevelUpgradeConfigServiceImpl.class);
    @Autowired
    private CommissionLevelUpgradeConfigMapper commissionLevelUpgradeConfigMapper;

    /**
     * 查询记录各级别的升级条件、团队极差奖励等信息
     * 
     * @param rankLevel 记录各级别的升级条件、团队极差奖励等信息主键
     * @return 记录各级别的升级条件、团队极差奖励等信息
     */
    @Override
    public CommissionLevelUpgradeConfig selectCommissionLevelUpgradeConfigByRankLevel(String rankLevel)
    {
        return commissionLevelUpgradeConfigMapper.selectCommissionLevelUpgradeConfigByRankLevel(rankLevel);
    }

    /**
     * 查询记录各级别的升级条件、团队极差奖励等信息列表
     * 
     * @param commissionLevelUpgradeConfig 记录各级别的升级条件、团队极差奖励等信息
     * @return 记录各级别的升级条件、团队极差奖励等信息
     */
    @Override
    public List<CommissionLevelUpgradeConfig> selectCommissionLevelUpgradeConfigList(CommissionLevelUpgradeConfig commissionLevelUpgradeConfig)
    {
        return commissionLevelUpgradeConfigMapper.selectCommissionLevelUpgradeConfigList(commissionLevelUpgradeConfig);
    }

    /**
     * 新增记录各级别的升级条件、团队极差奖励等信息
     * 
     * @param commissionLevelUpgradeConfig 记录各级别的升级条件、团队极差奖励等信息
     * @return 结果
     */
    @Override
    public int insertCommissionLevelUpgradeConfig(CommissionLevelUpgradeConfig commissionLevelUpgradeConfig)
    {
        commissionLevelUpgradeConfig.setCreateTime(DateUtils.getNowDate());
        return commissionLevelUpgradeConfigMapper.insertCommissionLevelUpgradeConfig(commissionLevelUpgradeConfig);
    }

    /**
     * 修改记录各级别的升级条件、团队极差奖励等信息
     * 
     * @param commissionLevelUpgradeConfig 记录各级别的升级条件、团队极差奖励等信息
     * @return 结果
     */
    @Override
    public int updateCommissionLevelUpgradeConfig(CommissionLevelUpgradeConfig commissionLevelUpgradeConfig)
    {
        commissionLevelUpgradeConfig.setUpdateTime(DateUtils.getNowDate());
        return commissionLevelUpgradeConfigMapper.updateCommissionLevelUpgradeConfig(commissionLevelUpgradeConfig);
    }

    /**
     * 批量删除记录各级别的升级条件、团队极差奖励等信息
     * 
     * @param rankLevels 需要删除的记录各级别的升级条件、团队极差奖励等信息主键
     * @return 结果
     */
    @Override
    public int deleteCommissionLevelUpgradeConfigByRankLevels(String[] rankLevels)
    {
        return commissionLevelUpgradeConfigMapper.deleteCommissionLevelUpgradeConfigByRankLevels(rankLevels);
    }

    /**
     * 删除记录各级别的升级条件、团队极差奖励等信息信息
     * 
     * @param rankLevel 记录各级别的升级条件、团队极差奖励等信息主键
     * @return 结果
     */
    @Override
    public int deleteCommissionLevelUpgradeConfigByRankLevel(String rankLevel)
    {
        return commissionLevelUpgradeConfigMapper.deleteCommissionLevelUpgradeConfigByRankLevel(rankLevel);
    }
    /**
     * 用户升级判断
     */
    @Override
    public Boolean userUpgradeJudgment(Long userId, RankLevel rankLevel){
        CommissionLevelUpgradeConfig commissionLevelUpgradeConfig =  commissionLevelUpgradeConfigMapper.exampleQueryUserLevelUpgradeInformation(userId);
        log.info(" commissionLevelUpgradeConfig :{} ",commissionLevelUpgradeConfig);
        if(commissionLevelUpgradeConfig==null){
            return false;
        }
        commissionLevelUpgradeConfig.setRankLevel(rankLevel.getValue());
        CommissionLevelUpgradeConfig systemConfig = selectCommissionLevelUpgradeConfigByRankLevel(commissionLevelUpgradeConfig.getRankLevel());
        // 系统没有指定升级要求
        if(systemConfig == null){
            return true;
        }
        // 修改返回条件为比较三个值是否都大于等于系统配置的值
        if(
                commissionLevelUpgradeConfig.getRequiredGoldMember() >= systemConfig.getRequiredGoldMember() &&
                        commissionLevelUpgradeConfig.getRequiredOrdinaryMember() >= systemConfig.getRequiredOrdinaryMember() &&
                        commissionLevelUpgradeConfig.getDirectorCount() >= systemConfig.getDirectorCount() &&
                        commissionLevelUpgradeConfig.getManagerCount() >= systemConfig.getManagerCount() &&
                        commissionLevelUpgradeConfig.getRequiredTotalSales().compareTo(systemConfig.getRequiredTotalSales()) >= 0
        ){
            return true;
        }
        return false;
    }

    @Override
    public Boolean userUpgradeJudgment(Long userId, String rankLevel) {
        CommissionLevelUpgradeConfig commissionLevelUpgradeConfig =  commissionLevelUpgradeConfigMapper.exampleQueryUserLevelUpgradeInformation(userId);
        log.info(" commissionLevelUpgradeConfig :{} ",commissionLevelUpgradeConfig);
        if(commissionLevelUpgradeConfig==null){
            return false;
        }
        commissionLevelUpgradeConfig.setRankLevel(rankLevel);
        CommissionLevelUpgradeConfig systemConfig = selectCommissionLevelUpgradeConfigByRankLevel(commissionLevelUpgradeConfig.getRankLevel());
        // 系统没有指定升级要求
        if(systemConfig == null){
            return true;
        }

        // 修改返回条件为比较三个值是否都大于等于系统配置的值
        if(
                commissionLevelUpgradeConfig.getRequiredGoldMember() >= systemConfig.getRequiredGoldMember() &&
                        commissionLevelUpgradeConfig.getRequiredOrdinaryMember() >= systemConfig.getRequiredOrdinaryMember() &&
                        commissionLevelUpgradeConfig.getRequiredTotalSales().compareTo(systemConfig.getRequiredTotalSales()) >= 0
        ){
            return true;
        }
        return false;
    }

    @Override
    public Map<String, CommissionLevelUpgradeConfig> levelConfiguration() {
        // 获取所有的级别配置
        List<CommissionLevelUpgradeConfig> configs = commissionLevelUpgradeConfigMapper.selectCommissionLevelUpgradeConfigList(null);

        // 创建一个 Map 来保存结果
        Map<String, CommissionLevelUpgradeConfig> resultMap = new HashMap<>();

        // 遍历 List，将每个 CommissionLevelUpgradeConfig 的 rankLevel 作为 key，配置对象作为 value 放入 Map
        for (CommissionLevelUpgradeConfig config : configs) {
            resultMap.put(config.getRankLevel(), config);
        }

        return resultMap;
    }
}
