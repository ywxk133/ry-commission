package com.ruoyi.commission.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户销售额管理对象 commission_user_sales_summary
 * 
 * @author xing
 * @date 2025-02-14
 */
public class CommissionUserSalesSummary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 直推销售额 */
    @Excel(name = "直推销售额")
    private BigDecimal directSales;

    /** 团队销售额 */
    @Excel(name = "团队销售额")
    private BigDecimal teamSales;

    /** 个人消费金额 */
    @Excel(name = "个人消费金额")
    private BigDecimal personalSales;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDirectSales(BigDecimal directSales) 
    {
        this.directSales = directSales;
    }

    public BigDecimal getDirectSales() 
    {
        return directSales;
    }
    public void setTeamSales(BigDecimal teamSales) 
    {
        this.teamSales = teamSales;
    }

    public BigDecimal getTeamSales() 
    {
        return teamSales;
    }
    public void setPersonalSales(BigDecimal personalSales) 
    {
        this.personalSales = personalSales;
    }

    public BigDecimal getPersonalSales() 
    {
        return personalSales;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("directSales", getDirectSales())
            .append("teamSales", getTeamSales())
            .append("personalSales", getPersonalSales())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
