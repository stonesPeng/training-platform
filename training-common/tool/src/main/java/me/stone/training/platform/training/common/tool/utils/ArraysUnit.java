package me.stone.training.platform.training.common.tool.utils;

import lombok.val;

import java.lang.reflect.Array;

/**
 * Array tools
 */
public interface ArraysUnit {
    Object[] EMPTY_OBJECT = {};

    static <T> boolean isEmptyOrNull(T[] array) {
        return array == null || array.length == 0;
    }

    @SafeVarargs
    static <T> T[] prepend(T[] array, T... other) {
        if (isEmptyOrNull(array)) return other;
        if (isEmptyOrNull(other)) return array;
        val x = java.util.Arrays.copyOf(array, array.length + other.length);
        System.arraycopy(other, 0, x, 0, other.length);
        System.arraycopy(array, 0, x, other.length, array.length);
        return x;
    }


    @SafeVarargs
    static <T> T[] expand(T[] array, T[] prepend, T... append) {
        if (isEmptyOrNull(array)) return append(prepend, append);
        if (isEmptyOrNull(prepend)) return append(array, append);
        if (isEmptyOrNull(append)) return prepend(array, prepend);

        val x = java.util.Arrays.copyOf(array, prepend.length + array.length + append.length);
        System.arraycopy(prepend, 0, x, 0, prepend.length);
        System.arraycopy(array, 0, x, prepend.length, array.length);
        System.arraycopy(append, 0, x, (prepend.length + array.length), append.length);
        return x;
    }

    @SafeVarargs
    static <T> T[] append(T[] array, T... other) {
        if (isEmptyOrNull(array)) return other;
        if (isEmptyOrNull(other)) return array;
        val x = java.util.Arrays.copyOf(array, array.length + other.length);
        System.arraycopy(other, 0, x, array.length, other.length);
        return x;
    }

    @SuppressWarnings("unchecked")
    static <T> T[] empty() {
        return (T[]) EMPTY_OBJECT;
    }

    @SuppressWarnings("unchecked")
    static <T> T[] newArray(Class<T> component, int size) {
        return (T[]) Array.newInstance(component, size);
    }

    @SuppressWarnings("unchecked")
    static <T, R> R[] newArrayGeneric(Class<T> component, int size) {
        return (R[]) Array.newInstance(component, size);
    }
}
