package me.stone.training.platform.training.java.algorithm.sorting;

import lombok.var;
import me.stone.training.platform.training.java.algorithm.utils.ArraysHelper;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc 选择排序
 * @since 2021/5/26 9:34
 */
public class SelectionSort implements IMutableSorter {
    /**
     * 排序
     *
     * @param elements 数组
     */
    @Override
    public void sort(int[] elements) {
        for (int i = elements.length - 1; i >= 0; i--) {
            int maxIndex = maxIndex(elements, 0, i + 1);
            ArraysHelper.swap(elements, maxIndex, i);
        }
    }

    private int maxIndex(int[] elements, int from, int to) {
        var maxIndex = from;
        var max = elements[maxIndex];
        for (int j = 0; j < to; j++) {
            if (elements[j] > max) {
                maxIndex = j;
                max = elements[maxIndex];
            }
        }
        return maxIndex;
    }
}
