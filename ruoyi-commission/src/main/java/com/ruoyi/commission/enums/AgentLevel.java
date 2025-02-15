package com.ruoyi.commission.enums;

public enum AgentLevel {
    PROVINCE("province"),
    CITY("city");

    private String description;

    // 构造函数
    AgentLevel(String description) {
        this.description = description;
    }

    // 获取描述
    public String getDescription() {
        return description;
    }

    // 根据名称获取对应的枚举
    public static AgentLevel fromString(String text) {
        for (AgentLevel level : AgentLevel.values()) {
            if (level.description.equalsIgnoreCase(text)) {
                return level;
            }
        }
        return null; // 如果没有匹配到，则返回 null
    }

    @Override
    public String toString() {
        return description;
    }
}
