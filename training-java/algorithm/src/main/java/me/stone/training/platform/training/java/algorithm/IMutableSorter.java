package me.stone.training.platform.training.java.algorithm;

import java.util.Arrays;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/23 22:22
 */
public interface IMutableSorter {

    /**
     * 排序
     *
     * @param elements 数组
     */
    void sort(int[] elements);


    static void main(String[] args) {
        int[] array = new int[]{1, 3, 8, 1, 5, 2};
        final IMutableSorter sorter = new InsertionSort();
        sorter.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
