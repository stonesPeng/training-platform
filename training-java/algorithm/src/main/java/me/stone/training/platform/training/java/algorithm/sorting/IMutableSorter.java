package me.stone.training.platform.training.java.algorithm.sorting;

import lombok.SneakyThrows;
import lombok.var;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/23 22:22
 */
public interface IMutableSorter {

    @SneakyThrows
    static void testSort(@NotNull Class cls, int len) {
        final var constructor = cls.getConstructor();
        final var rawInst = constructor.newInstance();
        if (rawInst instanceof IMutableSorter) {
            final var gen = gen(len);
            final var sorter = (IMutableSorter) rawInst;
            sorter.sort(gen);
            System.out.println("target:\n " + Arrays.toString(gen));
        }
    }

    static int[] gen(int len) {
        //todo
        int[] array = new int[]{1, 3, 8, 1, 5, 2};
        System.out.println("source:\n " + Arrays.toString(array));
        return array;
    }

    static void main(String[] args) {
        IMutableSorter.testSort(BubbleSort.class, 10);
    }

    /**
     * 排序
     *
     * @param elements 数组
     */
    void sort(int[] elements);
}
