package com.base.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/10/26.
 */
public class TextUtils {
    public static boolean isEmpty(String text) {
        return StringUtils.isEmpty(text);
    }

    public static boolean hasParameter(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        if (isEmpty(value)) return false;
        return true;
    }

    public static <T> T getParameter(HttpServletRequest request, String name, T defValue) {
        String value = request.getParameter(name);
        if (isEmpty(value)) return defValue;
        if (defValue instanceof String) {
            return (T) String.valueOf(value);
        }
        if (defValue instanceof Boolean) {
            return (T) Boolean.valueOf(value);
        }
        if (defValue instanceof Integer) {
            return (T) Integer.valueOf(value);
        }
        if (defValue instanceof Long) {
            return (T) Long.valueOf(value);
        }
        if (defValue instanceof Float) {
            return (T) Float.valueOf(value);
        }
        return null;
    }
}
