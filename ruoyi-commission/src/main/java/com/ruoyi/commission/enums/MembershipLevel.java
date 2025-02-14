package com.ruoyi.commission.enums;

public enum MembershipLevel {
        // 普通会员
        ORDINARY("ordinary"),
        // 金牌会员
        GOLD("gold"),
        // 铂金会员
        PLATINUM("platinum");

        private final String value;
        /**
         * 构造函数 数值value
         * @param value
         */
        MembershipLevel(String value) {
            this.value = value;
        }

        /**
         * 获取值 value
         * @return
         */
        public String getValue() {
            return value;
        }

}
