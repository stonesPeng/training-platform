package me.stone.training.platform.training.java.algorithm;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc 插入排序, 加一减一排序
 * @since 2021/5/23 22:23
 */
public class InsertionSort implements IMutableSorter {
    /**
     * 排序
     *
     * @param elements 数组
     */
    @Override
    public void sort(int[] elements) {
        for (int i = 1; i < elements.length; i++) {
            int reference = elements[i];
            int j = i;
            for (; j > 0 && elements[j - 1] > reference; j--) {
                elements[j] = elements[j - 1];
            }
            elements[j] = reference;
        }
    }
}
