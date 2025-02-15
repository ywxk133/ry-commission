package com.ruoyi.commission.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.commission.mapper.CommissionMembershipConfigMapper;
import com.ruoyi.commission.domain.CommissionMembershipConfig;
import com.ruoyi.commission.service.ICommissionMembershipConfigService;

/**
 * 会员配置管理Service业务层处理
 * 
 * @author xing
 * @date 2025-02-14
 */
@Service
public class CommissionMembershipConfigServiceImpl implements ICommissionMembershipConfigService 
{
    @Autowired
    private CommissionMembershipConfigMapper commissionMembershipConfigMapper;

    /**
     * 查询会员配置管理
     * 
     * @param membershipLevel 会员配置管理主键
     * @return 会员配置管理
     */
    @Override
    public CommissionMembershipConfig selectCommissionMembershipConfigByMembershipLevel(String membershipLevel)
    {
        return commissionMembershipConfigMapper.selectCommissionMembershipConfigByMembershipLevel(membershipLevel);
    }

    /**
     * 查询会员配置管理列表
     * 
     * @param commissionMembershipConfig 会员配置管理
     * @return 会员配置管理
     */
    @Override
    public List<CommissionMembershipConfig> selectCommissionMembershipConfigList(CommissionMembershipConfig commissionMembershipConfig)
    {
        return commissionMembershipConfigMapper.selectCommissionMembershipConfigList(commissionMembershipConfig);
    }

    /**
     * 新增会员配置管理
     * 
     * @param commissionMembershipConfig 会员配置管理
     * @return 结果
     */
    @Override
    public int insertCommissionMembershipConfig(CommissionMembershipConfig commissionMembershipConfig)
    {
        commissionMembershipConfig.setCreateTime(DateUtils.getNowDate());
        return commissionMembershipConfigMapper.insertCommissionMembershipConfig(commissionMembershipConfig);
    }

    /**
     * 修改会员配置管理
     * 
     * @param commissionMembershipConfig 会员配置管理
     * @return 结果
     */
    @Override
    public int updateCommissionMembershipConfig(CommissionMembershipConfig commissionMembershipConfig)
    {
        commissionMembershipConfig.setUpdateTime(DateUtils.getNowDate());
        return commissionMembershipConfigMapper.updateCommissionMembershipConfig(commissionMembershipConfig);
    }

    /**
     * 批量删除会员配置管理
     * 
     * @param membershipLevels 需要删除的会员配置管理主键
     * @return 结果
     */
    @Override
    public int deleteCommissionMembershipConfigByMembershipLevels(String[] membershipLevels)
    {
        return commissionMembershipConfigMapper.deleteCommissionMembershipConfigByMembershipLevels(membershipLevels);
    }

    /**
     * 删除会员配置管理信息
     * 
     * @param membershipLevel 会员配置管理主键
     * @return 结果
     */
    @Override
    public int deleteCommissionMembershipConfigByMembershipLevel(String membershipLevel)
    {
        return commissionMembershipConfigMapper.deleteCommissionMembershipConfigByMembershipLevel(membershipLevel);
    }

    @Override
    public CommissionMembershipConfig checkIfTheUserHasAMatchingOffer(Long userId) {
        return commissionMembershipConfigMapper.checkIfTheUserHasAMatchingOffer(userId);
    }

    @Override
    public CommissionMembershipConfig referralRewardInquiry(Long userId) {
        return commissionMembershipConfigMapper.referralRewardInquiry(userId);
    }
}
