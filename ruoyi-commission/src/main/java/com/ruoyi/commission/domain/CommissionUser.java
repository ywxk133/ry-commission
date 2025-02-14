package com.ruoyi.commission.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理对象 commission_user
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
public class CommissionUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 密码 */
    private String password;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 性别 */
    @Excel(name = "性别")
    private Integer gender;

    /** 上级用户ID */
    @Excel(name = "上级用户ID")
    private Long parentId;

    /** 会员等级 */
    @Excel(name = "会员等级")
    private String membershipLevel;

    /** 用户级别 */
    @Excel(name = "用户级别")
    private String rankLevel;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setGender(Integer gender) 
    {
        this.gender = gender;
    }

    public Integer getGender() 
    {
        return gender;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setMembershipLevel(String membershipLevel) 
    {
        this.membershipLevel = membershipLevel;
    }

    public String getMembershipLevel() 
    {
        return membershipLevel;
    }
    public void setRankLevel(String rankLevel) 
    {
        this.rankLevel = rankLevel;
    }

    public String getRankLevel() 
    {
        return rankLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("email", getEmail())
            .append("phone", getPhone())
            .append("gender", getGender())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("parentId", getParentId())
            .append("membershipLevel", getMembershipLevel())
            .append("rankLevel", getRankLevel())
            .toString();
    }
}
