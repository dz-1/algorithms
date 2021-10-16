package com.dz.algorithms.classic.sorting;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortingAlgorithmsTest {

    @Test
    void testBubbleSort() {
        test(BubbleSort::sort);
    }

    @Test
    void testInsertionSort() {
        test(InsertionSort::sort);
    }

    @Test
    void testSelectionSort() {
        test(SelectionSort::sort);
    }

    void test(Function<int[], int[]> sortMethod){
        assertArrayEquals(new int[]{}, sortMethod.apply(new int[]{}));
        assertArrayEquals(new int[]{1}, sortMethod.apply(new int[]{1}));
        assertArrayEquals(new int[]{1, 2}, sortMethod.apply(new int[]{2, 1}));
        assertArrayEquals(new int[]{1, 2, 3}, sortMethod.apply(new int[]{3, 2, 1}));
        assertArrayEquals(new int[]{11, 12, 22, 25, 34, 64, 90}, sortMethod.apply(new int[]{64, 34, 25, 12, 22, 11, 90}));
    }
}
