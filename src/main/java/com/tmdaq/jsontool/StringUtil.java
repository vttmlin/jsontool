package com.tmdaq.jsontool;

public class StringUtil {
    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isNotBlank(String srcStr) {
        return srcStr != null && srcStr.trim().length() > 0;
    }

    public static boolean isBlank(String srcStr) {
        return srcStr == null || srcStr.trim().length() == 0;
    }
}
