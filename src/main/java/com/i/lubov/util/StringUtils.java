package com.i.lubov.util;

import cn.hutool.core.util.StrUtil;

public final class StringUtils {

    public StringUtils() {
    }

    public static String toCamelCase(String str) {
        if (str == null) {
            return null;
        } else {
            str = str.toLowerCase();
            StringBuilder sb = new StringBuilder(str.length());
            boolean upperCase = false;

            for (int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                if (c == '_') {
                    upperCase = true;
                } else if (upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }

    public static String toCapitalizeCamelCase(String str) {
        if (str == null) {
            return null;
        } else {
            str = toCamelCase(str);
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    public static String toLowerFirst(String str) {
        if (StrUtil.isBlank(str)) return null;
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}

