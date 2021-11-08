/*
 *     Copyright (c) 2020.  MedtreeHealth All Rights Reserved.
 *     @Project: application-common
 *     @Module: common-api
 *     @File: MapBuilder.java
 *     @Author:  zen.liu@medtreehealth.com
 *     @LastModified:  2020-12-12 15:00:06
 */

package me.stone.training.platform.training.common.tool.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/**
 * Map 构建 工具
 *
 * @author Zen.Liu
 * @apiNote
 * @since 2020-12-12
 */

public interface MapBuilder<K, V> {
    MapBuilder<K, V> put(K k, V v);

    MapBuilder<K, V> putIf(@NotNull BooleanSupplier supplier, K k, V v);

    MapBuilder<K, V> putIf(@NotNull Predicate<Map<K, V>> predicate, K k, V v);

    MapBuilder<K, V> putThen(K k, V v, @NotNull Runnable act);

    MapBuilder<K, V> putIfThen(@NotNull BooleanSupplier supplier, K k, V v, @NotNull Runnable act);

    MapBuilder<K, V> putIfThen(@NotNull Predicate<Map<K, V>> predicate, K k, V v, @NotNull Runnable act);

    MapBuilder<K, V> putIfElseThen(@NotNull BooleanSupplier supplier, K k, V v, @NotNull Runnable act);

    MapBuilder<K, V> putIfElseThen(@NotNull Predicate<Map<K, V>> predicate, K k, V v, @NotNull Runnable act);

    @NotNull Map<K, V> get();

    @NotNull Map<K, V> getUnmodifiable();

    @Contract(" -> new")
    static <K, V> @NotNull MapBuilder<K, V> builder() {
        return new MapBuilderImpl<K, V>();
    }

    class MapBuilderImpl<K, V> implements MapBuilder<K, V> {
        final Map<K, V> map = new HashMap<>();

        private MapBuilderImpl() {
        }

        @Override
        public MapBuilder<K, V> put(K k, V v) {
            map.put(k, v);
            return this;
        }

        @Override
        public MapBuilder<K, V> putIf(@NotNull BooleanSupplier supplier, K k, V v) {
            if (supplier.getAsBoolean()) map.put(k, v);
            return this;
        }

        @Override
        public MapBuilder<K, V> putIf(@NotNull Predicate<Map<K, V>> predicate, K k, V v) {
            if (predicate.test(map)) map.put(k, v);
            return this;
        }

        @Override
        public MapBuilder<K, V> putThen(K k, V v, @NotNull Runnable act) {
            map.put(k, v);
            act.run();
            return this;
        }

        @Override
        public MapBuilder<K, V> putIfThen(@NotNull BooleanSupplier supplier, K k, V v, @NotNull Runnable act) {
            if (supplier.getAsBoolean()) {
                map.put(k, v);
                act.run();
            }
            return this;
        }

        @Override
        public MapBuilder<K, V> putIfThen(@NotNull Predicate<Map<K, V>> predicate, K k, V v, @NotNull Runnable act) {
            if (predicate.test(map)) {
                map.put(k, v);
                act.run();
            }
            return this;
        }

        @Override
        public MapBuilder<K, V> putIfElseThen(@NotNull BooleanSupplier supplier, K k, V v, @NotNull Runnable act) {
            if (supplier.getAsBoolean()) {

                act.run();
            } else {
                map.put(k, v);
            }
            return this;
        }

        @Override
        public MapBuilder<K, V> putIfElseThen(@NotNull Predicate<Map<K, V>> predicate, K k, V v, @NotNull Runnable act) {
            if (predicate.test(map)) {
                map.put(k, v);

            } else {
                act.run();
            }
            return this;
        }

        @Override
        public @NotNull Map<K, V> get() {
            return map;
        }

        @Override
        public @NotNull Map<K, V> getUnmodifiable() {
            return Collections.unmodifiableMap(map);
        }
    }
}
