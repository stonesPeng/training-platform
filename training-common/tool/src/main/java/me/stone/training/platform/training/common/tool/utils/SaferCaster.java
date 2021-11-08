package me.stone.training.platform.training.common.tool.utils;

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;

import java.util.*;
import java.util.function.Function;

public interface SaferCaster {

    /**
     * 安全转换{@code Map<String,Object>}到指定类型,只检查第一组
     *
     * @param src   source
     * @param key   key class
     * @param value value class
     * @param <K>   key type
     * @param <V>   value type
     * @return Optional
     */
    @SuppressWarnings("unchecked")
    static <K, V> Optional<Map<K, V>> castJsonMap(@Nullable Map<String, Object> src, @NonNull Class<K> key, @NonNull Class<V> value) {
        if (src == null || src.isEmpty()) return Optional.of(new HashMap<>());
        final Map.Entry<String, Object> first = src.entrySet().iterator().next();
        if (!(first.getKey().getClass().isAssignableFrom(key)) || !(first.getValue().getClass().isAssignableFrom(value)))
            return Optional.empty();
        return Optional.of((Map<K, V>) src);
    }

    /**
     * 安全转换{@code Map<String,Object>}到指定类型,全面检查
     *
     * @param src   source
     * @param key   key class
     * @param value value class
     * @param <K>   key type
     * @param <V>   value type
     * @return Optional
     */
    @SuppressWarnings("unchecked")
    static <K, V> Optional<Map<K, V>> castJsonMapFullCheck(Map<String, Object> src, Class<K> key, Class<V> value) {
        if (src == null || src.isEmpty()) return Optional.of(new HashMap<>());
        try {
            src.forEach((k, v) -> {
                if (!k.getClass().isAssignableFrom(key) || !v.getClass().isAssignableFrom(value)) {
                    throw new IllegalArgumentException("");
                }
            });
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of((Map<K, V>) src);
    }

    @SuppressWarnings("unchecked")
    static <I, E extends I> List<E> downListNotNull(Class<E> targetType, List<I> src, Function<I, E> mapper, boolean every) {
        if (src == null || src.isEmpty()) return Collections.emptyList();
        if (targetType.isAssignableFrom(src.get(0).getClass()) && !every) {
            return ((List<E>) src);
        }
        return Seq.seq(src).map(mapper).toList();
    }

    @SuppressWarnings("unchecked")
    static <K, I, E extends I> Map<K, E> downMapValueNotNull(Class<E> targetType, Map<K, I> src, Function<I, E> mapper, boolean every) {
        if (src == null || src.isEmpty()) return Collections.emptyMap();
        if (targetType.isAssignableFrom(src.values().iterator().next().getClass()) && !every) {
            return ((Map<K, E>) src);
        }
        return Seq.seq(src).map(t -> t.map2(mapper)).toMap(Tuple2::v1, Tuple2::v2);
    }

    @SuppressWarnings("unchecked")
    static <I, E extends I> List<I> upListNotNull(List<E> src) {
        if (src == null || src.isEmpty()) return Collections.emptyList();
        return ((List<I>) src);
    }

    @SuppressWarnings("unchecked")
    static <K, I, E extends I> Map<K, I> upMapValueNotNull(Map<K, E> src) {
        if (src == null || src.isEmpty()) return Collections.emptyMap();
        return ((Map<K, I>) src);
    }

    @SuppressWarnings("unchecked")
    static <K, V extends K> V downConvert(Class<V> type, K src, Function<K, V> mapping) {
        if (src == null) return null;
        if (type.isAssignableFrom(src.getClass())) return (V) src;
        return mapping.apply(src);
    }

}
