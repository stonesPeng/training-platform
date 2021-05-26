package me.stone.training.platform.training.java.algorithm;

import me.stone.training.platform.training.java.algorithm.sorting.BubbleSort;
import me.stone.training.platform.training.java.algorithm.sorting.InsertionSort;
import me.stone.training.platform.training.java.algorithm.sorting.SelectionSort;
import me.stone.training.platform.training.java.algorithm.utils.SorterTestHelper;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/26 15:40
 */
public interface SortTestApp {

    int LEN = 10000;

    static void testInsertionSort() {
        SorterTestHelper.SORT_TESTER.accept(InsertionSort.class, LEN);
    }

    static void testSelectionSort() {
        SorterTestHelper.SORT_TESTER.accept(SelectionSort.class, LEN);
    }

    static void testBubbleSort() {
        SorterTestHelper.SORT_TESTER.accept(BubbleSort.class, LEN);
    }

    static void main(String[] args) {
        SortTestApp.testBubbleSort();
    }
}
