package me.stone.training.platform.training.java.algorithm.utils;

import lombok.var;
import me.stone.training.platform.training.common.tool.utils.TimeIntervalUtils;
import me.stone.training.platform.training.java.algorithm.sorting.IMutableSorter;

import java.util.function.BiConsumer;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/26 9:40
 */
public interface SorterTestHelper {

    /**
     * 排序测试器
     */
    BiConsumer<Class, Integer> SORT_TESTER = (clazz, len) -> {
        try {
            final var constructor = clazz.getConstructor();
            final var o = constructor.newInstance();
            if (o instanceof IMutableSorter) {
                final var lawInst = (IMutableSorter) o;
                final var source = ArraysHelper.ARRAY_GENERATOR.apply(len);
                TimeIntervalUtils.TimeIntervalProcessor.accept(() -> lawInst.sort(source));
                ArraysHelper.ORDERED_ASSERTER.accept(source);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
}
