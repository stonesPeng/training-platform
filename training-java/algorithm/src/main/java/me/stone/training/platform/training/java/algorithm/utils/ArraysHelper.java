package me.stone.training.platform.training.java.algorithm.utils;

import lombok.var;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/25 11:09
 */
public interface ArraysHelper {

    /**
     * 指定长度，数组生成器
     */
    Function<Integer, int[]> ARRAY_GENERATOR = len -> {
        final var array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = ((int) (Math.random() * len));
        }
        return array;
    };
    /**
     * 数组有序性检查器
     */
    Consumer<int[]> ORDERED_ASSERTER = array -> {
        System.out.println("Start to assert is ordered...");
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                throw new RuntimeException("UnOrdered...");
            }
        }
        System.out.println("Assert that the array is ordered");
    };

    /**
     * 交换数组两个索引的值
     *
     * @param element 原数组
     * @param left    下标索引left
     * @param right   下标索引right
     */
    static void swap(int[] element, int left, int right) {
        int temp = element[left];
        element[left] = element[right];
        element[right] = temp;
    }
}
