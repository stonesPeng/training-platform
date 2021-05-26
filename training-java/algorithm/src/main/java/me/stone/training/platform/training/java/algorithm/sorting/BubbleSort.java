package me.stone.training.platform.training.java.algorithm.sorting;

import me.stone.training.platform.training.java.algorithm.utils.ArraysHelper;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc 冒泡排序
 * @since 2021/5/25 9:12
 */
public class BubbleSort implements IMutableSorter {
    /**
     * 排序
     *
     * @param elements 数组
     */
    @Override
    public void sort(int[] elements) {
        for (int i = elements.length - 1; i >= 0; i--) {
            bubble(elements, 0, i + 1);
        }
    }

    private void bubble(int[] elements, int from, int to) {
        for (int j = from; j < to - 1; j++) {
            if (elements[j] > elements[j + 1]) {
                ArraysHelper.swap(elements, j, j + 1);
            }
        }
    }


}
