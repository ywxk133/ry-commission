package com.ruoyi.commission.enums;

public enum RankLevel {
    // 经理
    MANAGER("manager"),
    // 总监
    DIRECTOR("director"),
    // 合伙人
    PARTNER("partner");

    private final String value;

    RankLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

