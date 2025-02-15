package com.ruoyi.commission.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 记录各级别的升级条件、团队极差奖励等信息对象 commission_level_upgrade_config
 * 
 * @author xing
 * @date 2025-02-14
 */
public class CommissionLevelUpgradeConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 级别 */
    private String rankLevel;

    /** 会员数量 */
    @Excel(name = "金牌数量")
    private Long requiredGoldMember;

    /** 普通会员数量 */
    @Excel(name = "普通会员数量")
    private Long requiredOrdinaryMember;

    /** 总销售额 */
    @Excel(name = "总销售额")
    private BigDecimal requiredTotalSales;

    /** 团队极差奖励，团队成员达成条件时，团队奖励比例 */
    @Excel(name = "团队极差奖励，团队成员达成条件时，团队奖励比例")
    private BigDecimal teamDiffReward;

    /** 经理数量 */
    @Excel(name = "经理数量")
    private Integer managerCount;

    /** 总监数量 */
    @Excel(name = "总监数量")
    private Integer directorCount;

    public void setRankLevel(String rankLevel) 
    {
        this.rankLevel = rankLevel;
    }

    public String getRankLevel() 
    {
        return rankLevel;
    }
    public void setRequiredGoldMember(Long requiredGoldMember) 
    {
        this.requiredGoldMember = requiredGoldMember;
    }

    public Long getRequiredGoldMember() 
    {
        return requiredGoldMember;
    }
    public void setRequiredOrdinaryMember(Long requiredOrdinaryMember) 
    {
        this.requiredOrdinaryMember = requiredOrdinaryMember;
    }

    public Long getRequiredOrdinaryMember() 
    {
        return requiredOrdinaryMember;
    }
    public void setRequiredTotalSales(BigDecimal requiredTotalSales) 
    {
        this.requiredTotalSales = requiredTotalSales;
    }

    public BigDecimal getRequiredTotalSales() 
    {
        return requiredTotalSales;
    }

    public Integer getManagerCount() {
        return managerCount;
    }

    public void setManagerCount(Integer managerCount) {
        this.managerCount = managerCount;
    }

    public Integer getDirectorCount() {
        return directorCount;
    }

    public void setDirectorCount(Integer directorCount) {
        this.directorCount = directorCount;
    }

    public void setTeamDiffReward(BigDecimal teamDiffReward)
    {
        this.teamDiffReward = teamDiffReward;
    }

    public BigDecimal getTeamDiffReward() 
    {
        return teamDiffReward;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rankLevel", getRankLevel())
            .append("requiredGoldMember", getRequiredGoldMember())
            .append("requiredOrdinaryMember", getRequiredOrdinaryMember())
            .append("requiredTotalSales", getRequiredTotalSales())
            .append("teamDiffReward", getTeamDiffReward())
            .append("directorCount", getDirectorCount())
            .append("managerCount", getManagerCount())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
