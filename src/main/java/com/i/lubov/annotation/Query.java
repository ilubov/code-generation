package com.i.lubov.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {
    String column() default "";

    String blurry() default "";

    boolean sort() default false;

    boolean group() default false;

    Query.Type type() default Query.Type.EQ;

    Query.FieldType fieldType() default Query.FieldType.NUMBER;

    public static enum FieldType {
        NUMBER,
        STRING;

        private FieldType() {
        }
    }

    public interface Attr {
        String BLURRY = "blurry";
        String SORT = "sort";
        String GROUP = "group";
    }

    public static enum Type {
        EQ("="),
        NE("<>"),
        GT(">"),
        GE(">="),
        LT("<"),
        LE("<="),
        LIKE("%str%"),
        NOT_LIKE("%str%"),
        LIKE_LEFT("%str"),
        LIKE_RIGHT("str%"),
        IS_NULL("is null"),
        IS_NOT_NULL("is not null"),
        IN("in"),
        NOT_IN("not in");

        private final String type;

        private Type(String type) {
            this.type = type;
        }
    }
}

