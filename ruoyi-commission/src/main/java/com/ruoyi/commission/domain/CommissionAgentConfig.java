package com.ruoyi.commission.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 代理配置管理对象 commission_agent_config
 * 
 * @author xing
 * @date 2025-02-15
 */
public class CommissionAgentConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键，唯一标识代理配置 */
    @Excel(name = "主键，唯一标识代理配置")
    private Long id;

    /** 代理级别 */
    @Excel(name = "代理级别")
    private String agentLevel;

    /** 代理的抽成比例 */
    @Excel(name = "代理的抽成比例")
    private BigDecimal commissionRate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAgentLevel(String agentLevel) 
    {
        this.agentLevel = agentLevel;
    }

    public String getAgentLevel() 
    {
        return agentLevel;
    }
    public void setCommissionRate(BigDecimal commissionRate)
    {
        this.commissionRate = commissionRate;
    }

    public BigDecimal getCommissionRate()
    {
        return commissionRate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("agentLevel", getAgentLevel())
            .append("commissionRate", getCommissionRate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
