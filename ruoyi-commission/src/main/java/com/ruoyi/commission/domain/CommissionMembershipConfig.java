package com.ruoyi.commission.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员配置管理对象 commission_membership_config
 * 
 * @author xing
 * @date 2025-02-14
 */
public class CommissionMembershipConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会员等级 */
    @Excel(name = "会员等级")
    private String membershipLevel;

    /** 直推奖励比例 */
    @Excel(name = "直推奖励比例")
    private BigDecimal directPushReward;

    /** 复购折扣比例 */
    @Excel(name = "复购折扣比例")
    private BigDecimal repurchasePercentage;

    public void setMembershipLevel(String membershipLevel) 
    {
        this.membershipLevel = membershipLevel;
    }

    public String getMembershipLevel() 
    {
        return membershipLevel;
    }
    public void setDirectPushReward(BigDecimal directPushReward) 
    {
        this.directPushReward = directPushReward;
    }

    public BigDecimal getDirectPushReward() 
    {
        return directPushReward;
    }
    public void setRepurchasePercentage(BigDecimal repurchasePercentage) 
    {
        this.repurchasePercentage = repurchasePercentage;
    }

    public BigDecimal getRepurchasePercentage() 
    {
        return repurchasePercentage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("membershipLevel", getMembershipLevel())
            .append("directPushReward", getDirectPushReward())
            .append("repurchasePercentage", getRepurchasePercentage())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
