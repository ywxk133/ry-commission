package com.ruoyi.commission.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户代理管理对象 user_agent
 * 
 * @author xing
 * @date 2025-02-14
 */
public class UserAgent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID，唯一标识每个用户 */
    private Long userId;

    /** 代理级别：省代理 1 、市代理 2 */
    @Excel(name = "代理级别：省代理 1 、市代理 2")
    private Integer agentLevel;

    /** 省级ID，关联到 city 表 */
    @Excel(name = "省级ID，关联到 city 表")
    private Long provinceId;

    /** 市级ID，关联到 city 表 */
    @Excel(name = "市级ID，关联到 city 表")
    private Long cityId;

    /** 是否启用代理：0 表示禁用，1 表示启用 */
    @Excel(name = "是否启用代理：0 表示禁用，1 表示启用")
    private Integer isEnabled;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAgentLevel(Integer agentLevel) 
    {
        this.agentLevel = agentLevel;
    }

    public Integer getAgentLevel() 
    {
        return agentLevel;
    }
    public void setProvinceId(Long provinceId) 
    {
        this.provinceId = provinceId;
    }

    public Long getProvinceId() 
    {
        return provinceId;
    }
    public void setCityId(Long cityId)
    {
        this.cityId = cityId;
    }

    public Long getCityId()
    {
        return cityId;
    }
    public void setIsEnabled(Integer isEnabled) 
    {
        this.isEnabled = isEnabled;
    }

    public Integer getIsEnabled() 
    {
        return isEnabled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("agentLevel", getAgentLevel())
            .append("provinceId", getProvinceId())
            .append("cityId", getCityId())
            .append("isEnabled", getIsEnabled())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
