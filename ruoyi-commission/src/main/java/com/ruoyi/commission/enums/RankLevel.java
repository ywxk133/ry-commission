package com.ruoyi.commission.enums;


public enum RankLevel {
    // 经理
    MANAGER("manager"),
    // 总监
    DIRECTOR("director"),
    // 合伙人
    PARTNER("partner");

    private final String value;

    // 枚举构造函数
    RankLevel(String value) {
        this.value = value;
    }

    // 获取枚举值
    public String getValue() {
        return value;
    }

    // 覆盖 toString() 方法，返回枚举值
    @Override
    public String toString() {
        return value;
    }

    // 根据 String 值获取对应的枚举
    public static RankLevel fromValue(String value) {
        for (RankLevel rank : RankLevel.values()) {
            if (rank.getValue().equals(value)) {
                return rank;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}


