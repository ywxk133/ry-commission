package com.ruoyi.commission.domain.dto;

import com.ruoyi.commission.enums.TransactionType;

import java.math.BigDecimal;

public class OrderRequestDTO {
    // 用户ID
    private Long userId;
    // 订单金额
    private BigDecimal orderAmount;
    // 下单地址
    private String address;

    // 城市编号（可以根据需求将其与省市区关系表联动）
    private Integer orderDistrictId;
    // 订单类型
    private String transactionType = TransactionType.ORDER.getValue();

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public Integer getOrderDistrictId() {
        return orderDistrictId;
    }

    public void setOrderDistrictId(Integer orderDistrictId) {
        this.orderDistrictId = orderDistrictId;
    }
    // Getter 和 Setter
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal  getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal  orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                "userId=" + userId +
                ", orderAmount=" + orderAmount +
                ", address='" + address + '\'' +
                ", orderDistrictId=" + orderDistrictId +
                '}';
    }
}
