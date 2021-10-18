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

    @Test
    void testQuickSort() {
        test(QuickSort::sort);
    }

    @Test
    void testHeapSort() {
        test(HeapSort::sort);
    }

    @Test
    void testMergeSort(){
        test(MergeSort::sort);
    }

    @Test
    void testCountingSort(){
        testSmallNumbers(CountingSort::sort);
    }

    @Test
    void testRadixSort(){
        test(RadixSort::sort);
    }

    void testSmallNumbers(Function<int[], int[]> sortMethod) {
        assertArrayEquals(new int[]{}, sortMethod.apply(new int[]{}));
        assertArrayEquals(new int[]{1}, sortMethod.apply(new int[]{1}));
        assertArrayEquals(new int[]{1, 2}, sortMethod.apply(new int[]{2, 1}));
        assertArrayEquals(new int[]{1, 2, 3}, sortMethod.apply(new int[]{3, 2, 1}));
        assertArrayEquals(new int[]{1, 1, 2, 2, 4, 5, 7}, sortMethod.apply(new int[]{1, 4, 1, 2, 7, 5, 2}));
    }

    void test(Function<int[], int[]> sortMethod) {
        testSmallNumbers(sortMethod);
        assertArrayEquals(new int[]{11, 12, 22, 25, 34, 64, 90}, sortMethod.apply(new int[]{64, 34, 25, 12, 22, 11, 90}));
    }
}
