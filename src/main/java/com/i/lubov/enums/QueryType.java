package com.i.lubov.enums;

public enum QueryType {
    BLURRY(0, "and (a like '%str%' or b like '%str%' or c like '%str%')"),
    EQ(1, "="),
    NE(2, "<>"),
    GT(3, ">"),
    GE(4, ">="),
    LT(5, "<"),
    LE(6, "<="),
    LIKE(7, "like %str%"),
    NOT_LIKE(8, "not like %str%"),
    LIKE_LEFT(9, "like %str"),
    LIKE_RIGHT(10, "like str%"),
    IS_NULL(11, "is null"),
    IS_NOT_NULL(12, "is not null"),
    IN(13, "in"),
    NOT_IN(14, "not in"),
    BETWEEN(15, "between (,]");

    private Integer code;
    private String desc;

    private QueryType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static QueryType ofEnum(String code) {
        QueryType[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            QueryType type = var1[var3];
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        return null;
    }
}

