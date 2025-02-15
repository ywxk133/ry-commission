package com.ruoyi.commission.enums;

public enum TransactionType {
    // 充值
    RECHARGE("recharge"),
    // 订单
    ORDER("order"),
    // 退款
    REFUND("refund"),
    // 管理员调整
    MANUAL_ADJUSTMENT("manual_adjustment"),
    // 直推奖励
    DIRECT_PUSH_REWARD("direct_push_reward"),
    // 团队分成
    TEAM_DIFF_REWARD("team_differential_reward"),
    // 代理分成
    AGENCY_SHARING("agency_sharing");
    // 枚举值
    private final String value;

    // 构造方法
    TransactionType(String value) {
        this.value = value;
    }

    // 获取枚举值对应的描述
    public String getValue() {
        return value;
    }

    // 根据value获取枚举类型
    public static TransactionType fromValue(String value) {
        for (TransactionType type : TransactionType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知的交易类型: " + value);
    }
}
