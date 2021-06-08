package me.stone.training.platform.training.java.algorithm.sorting;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/25 9:12
 */
public interface IImutableSorter {

    /**
     * 排序
     *
     * @param elements 排序前的数组
     * @return 排序后的数组
     */
    int[] sort(int[] elements);
}