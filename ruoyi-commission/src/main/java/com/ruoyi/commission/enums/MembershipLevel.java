package com.ruoyi.commission.enums;

public enum MembershipLevel {
        // 普通用户
        ORDINARY("ordinary"),
        //普通会员
        ORDINARY_MEMBER("ordinaryMember"),
        // 金牌会员
        GOLD("gold");


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
