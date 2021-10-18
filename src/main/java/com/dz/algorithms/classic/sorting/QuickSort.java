package com.dz.algorithms.classic.sorting;

import static com.dz.algorithms.util.Utilities.swap;

public class QuickSort {
    private QuickSort() {
        //util
    }

    static int partition(int[] a, int low, int high) {
        int pivot = a[high];

        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (a[j] < pivot) {
                i++;
                swap(a, i, j);
            }
        }

        swap(a, i + 1, high);

        return i + 1;
    }

    static void sort(int[] a, int low, int high) {
        if (low < high) {
            int pi = partition(a, low, high);

            sort(a, low, pi - 1);
            sort(a, pi + 1, high);
        }
    }

    static int[] sort(int[] a) {
        sort(a, 0, a.length - 1);

        return a;
    }
}
