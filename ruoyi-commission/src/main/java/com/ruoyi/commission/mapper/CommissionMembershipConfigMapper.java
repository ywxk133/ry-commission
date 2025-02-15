package com.ruoyi.commission.mapper;

import java.util.List;
import com.ruoyi.commission.domain.CommissionMembershipConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 会员配置管理Mapper接口
 * 
 * @author xing
 * @date 2025-02-14
 */
public interface CommissionMembershipConfigMapper 
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
     * 删除会员配置管理
     * 
     * @param membershipLevel 会员配置管理主键
     * @return 结果
     */
    public int deleteCommissionMembershipConfigByMembershipLevel(String membershipLevel);

    /**
     * 批量删除会员配置管理
     * 
     * @param membershipLevels 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommissionMembershipConfigByMembershipLevels(String[] membershipLevels);

    CommissionMembershipConfig checkIfTheUserHasAMatchingOffer(@Param("userId") Long userId);

    CommissionMembershipConfig referralRewardInquiry(@Param("userId") Long userId);
}
