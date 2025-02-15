package com.ruoyi.commission.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 余额流水对象 commission_account_transaction
 * 
 * @author xing
 * @date 2025-02-13
 */
public class CommissionAccountTransaction extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水记录ID */
    @Excel(name = "流水记录ID")
    private Long transactionId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 账户变动金额 */
    @Excel(name = "账户变动金额")
    private BigDecimal amount;

    /** 变动后的账户余额 */
    @Excel(name = "变动后的账户余额")
    private BigDecimal balanceAfter;

    /** 交易类型 */
    @Excel(name = "交易类型")
    private String transactionType;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transactionTime;

    /** 交易描述 */
    @Excel(name = "交易描述")
    private String description;

    /** 管理员ID，记录执行操作的管理员（如果是管理员调整） */
    @Excel(name = "管理员ID，记录执行操作的管理员", readConverterExp = "如=果是管理员调整")
    private Long adminId;

    /** 变更类型 1表示增加 2表示扣除 */
    @Excel(name = "变更类型")
    private Integer isIncrease;

    /** 下单地址 */
    @Excel(name = "下单地址")
    private String orderAddress;

    /** 下单市区编号 */
    @Excel(name = "下单市区编号")
    private Integer orderDistrictId;
    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal productPrice;

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Integer getOrderDistrictId() {
        return orderDistrictId;
    }

    public void setOrderDistrictId(Integer orderDistrictId) {
        this.orderDistrictId = orderDistrictId;
    }

    public void setTransactionId(Long transactionId)
    {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() 
    {
        return transactionId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setBalanceAfter(BigDecimal balanceAfter) 
    {
        this.balanceAfter = balanceAfter;
    }

    public BigDecimal getBalanceAfter() 
    {
        return balanceAfter;
    }
    public void setTransactionType(String transactionType) 
    {
        this.transactionType = transactionType;
    }

    public String getTransactionType() 
    {
        return transactionType;
    }
    public void setTransactionTime(Date transactionTime) 
    {
        this.transactionTime = transactionTime;
    }

    public Date getTransactionTime() 
    {
        return transactionTime;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setAdminId(Long adminId) 
    {
        this.adminId = adminId;
    }

    public Long getAdminId() 
    {
        return adminId;
    }
    public void setIsIncrease(Integer isIncrease) 
    {
        this.isIncrease = isIncrease;
    }

    public Integer getIsIncrease() 
    {
        return isIncrease;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("transactionId", getTransactionId())
            .append("userId", getUserId())
            .append("amount", getAmount())
            .append("balanceAfter", getBalanceAfter())
            .append("transactionType", getTransactionType())
            .append("transactionTime", getTransactionTime())
            .append("description", getDescription())
            .append("adminId", getAdminId())
            .append("isIncrease", getIsIncrease())
            .append("orderAddress", getOrderAddress())
            .append("orderDistrictId", getOrderDistrictId())
                .append("productPrice", getProductPrice())
            .toString();
    }
}
