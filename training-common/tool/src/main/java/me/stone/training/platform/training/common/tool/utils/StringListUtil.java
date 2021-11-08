/*
 *     Copyright (c) 2020.  MedtreeHealth All Rights Reserved.
 *     @Project: application-common
 *     @Module: common-api
 *     @File: StringListUtil.java
 *     @Author:  zen.liu@medtreehealth.com
 *     @LastModified:  2020-12-12 14:59:30
 */

package me.stone.training.platform.training.common.tool.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;

/**
 * 字符串数组工具</br>
 * 将简单数组/列表保存为字符串模式
 *
 * @author Zen.Liu
 * @apiNote
 */

public interface StringListUtil {
    String EMPTY = ",,";
    char SPLICER = ',';

    static <T> @NotNull List<T> extractListFrom(@Nullable String source, @NotNull Function<String, T> mapper) {
        if (source == null || source.isEmpty() || source.equals(EMPTY)) return Collections.emptyList();
        List<T> list = new ArrayList<>();
        for (String i : source.split(SPLICER + "")) {
            if (!i.trim().isEmpty()) {
                T t = mapper.apply(i);
                list.add(t);
            }
        }
        return list;
    }

    static <T> String buildFrom(@Nullable List<T> source, @NotNull Function<T, String> mapper) {
        if (source == null || source.isEmpty()) return EMPTY;
        StringJoiner joiner = new StringJoiner(",");
        for (T t : source) {
            String s = mapper.apply(t);
            joiner.add(s.trim());
        }
        return SPLICER + joiner.toString() + SPLICER;
    }

    static @NotNull List<Long> extractLongsFrom(@Nullable String source) {
        if (source == null || source.isEmpty() || source.equals(EMPTY)) return Collections.emptyList();
        List<Long> list = new ArrayList<>();
        for (String i : source.split(SPLICER + "")) {
            if (!i.trim().isEmpty()) {
                list.add(Long.valueOf(i));
            }
        }
        return list;
    }

    static @NotNull String buildLongsFrom(@Nullable List<Long> source) {
        if (source == null || source.isEmpty()) return EMPTY;
        StringJoiner joiner = new StringJoiner(",");
        for (Long t : source) {
            joiner.add(t + "");
        }
        return SPLICER + joiner.toString() + SPLICER;
    }

    static @NotNull List<String> extractStringsFrom(@Nullable String source) {
        if (source == null || source.isEmpty() || source.equals(EMPTY)) return Collections.emptyList();
        List<String> list = new ArrayList<>();
        for (String i : source.split(SPLICER + "")) {
            if (!i.trim().isEmpty()) {
                list.add(i);
            }
        }
        return list;
    }

    static @NotNull String buildStringsFrom(@Nullable List<String> source) {
        if (source == null || source.isEmpty()) return EMPTY;
        StringJoiner joiner = new StringJoiner(",");
        for (String t : source) {
            if (!t.trim().isEmpty()) {
                joiner.add(t.trim());
            }
        }
        return SPLICER + joiner.toString() + SPLICER;
    }
}
