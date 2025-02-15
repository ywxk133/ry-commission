package com.ruoyi.commission.service;

import java.util.List;
import com.ruoyi.commission.domain.CommissionMembershipConfig;

/**
 * 会员配置管理Service接口
 * 
 * @author xing
 * @date 2025-02-14
 */
public interface ICommissionMembershipConfigService 
{
    /**
     * 查询会员配置管理
     * 
     * @param membershipLevel 会员配置管理主键
     * @return 会员配置管理
     */
    public CommissionMembershipConfig selectCommissionMembershipConfigByMembershipLevel(String membershipLevel);

    /**
     * 查询会员配置管理列表
     * 
     * @param commissionMembershipConfig 会员配置管理
     * @return 会员配置管理集合
     */
    public List<CommissionMembershipConfig> selectCommissionMembershipConfigList(CommissionMembershipConfig commissionMembershipConfig);

    /**
     * 新增会员配置管理
     * 
     * @param commissionMembershipConfig 会员配置管理
     * @return 结果
     */
    public int insertCommissionMembershipConfig(CommissionMembershipConfig commissionMembershipConfig);

    /**
     * 修改会员配置管理
     * 
     * @param commissionMembershipConfig 会员配置管理
     * @return 结果
     */
    public int updateCommissionMembershipConfig(CommissionMembershipConfig commissionMembershipConfig);

    /**
     * 批量删除会员配置管理
     * 
     * @param membershipLevels 需要删除的会员配置管理主键集合
     * @return 结果
     */
    public int deleteCommissionMembershipConfigByMembershipLevels(String[] membershipLevels);

    /**
     * 删除会员配置管理信息
     * 
     * @param membershipLevel 会员配置管理主键
     * @return 结果
     */
    public int deleteCommissionMembershipConfigByMembershipLevel(String membershipLevel);

    CommissionMembershipConfig checkIfTheUserHasAMatchingOffer(Long userId);

    CommissionMembershipConfig referralRewardInquiry(Long userId);
}
