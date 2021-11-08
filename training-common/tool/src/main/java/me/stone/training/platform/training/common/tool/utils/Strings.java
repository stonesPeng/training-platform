package me.stone.training.platform.training.common.tool.utils;

import lombok.val;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Strings {
    String[] EMPTY_ARRAY = {};
    String EMPTY = "";

    static boolean nullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    static String nullOrEmpty(String value, String defaultValue) {
        return value == null || value.isEmpty() ? defaultValue : value;
    }

    static <T> String nullOrEmpty(T target, Function<T, String> supply, String defaultValue) {
        if (target == null) return defaultValue;
        val value = supply.apply(target);
        return value == null || value.isEmpty() ? defaultValue : value;
    }

    static String nullOrEmpty(String value, Supplier<String> defaultValue) {
        return value == null || value.isEmpty() ? defaultValue.get() : value;
    }

    static String repeat(char ch, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count is negative: " + count);
        }
        if (count == 1) {
            return ch + "";
        }
        final char[] multiple = new char[count];
        Arrays.fill(multiple, ch);
        return new String(multiple);
    }

    static String repeat(CharSequence value, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count is negative: " + count);
        }
        if (count == 1) {
            return value.toString();
        }
        final int len = value.length();
        if (len == 0 || count == 0) {
            return "";
        }
        if (len == 1) {
            final char[] single = new char[count];
            Arrays.fill(single, value.charAt(0));
            return new String(single);
        }
        if (Integer.MAX_VALUE / count < len) {
            throw new OutOfMemoryError("Repeating " + len + " bytes String " + count +
                " times will produce a String exceeding maximum size.");
        }
        final int limit = len * count;
        final char[] multiple = new char[limit];
        System.arraycopy(value, 0, multiple, 0, len);
        int copied = len;
        for (; copied < limit - copied; copied <<= 1) {
            System.arraycopy(multiple, 0, multiple, copied, copied);
        }
        System.arraycopy(multiple, 0, multiple, copied, limit - copied);
        return new String(multiple);
    }
}
