package com.dz.algorithms.classic.sorting;

import static com.dz.algorithms.util.Utilities.swap;

public class HeapSort {
    private HeapSort() {
        //util
    }

    static void heapify(int[] a, int n, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int largest = i;

        if (left < n && a[left] > a[largest]) {
            largest = left;
        }

        if (right < n && a[right] > a[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(a, i, largest);
            heapify(a, n, largest);
        }
    }

    static int[] sort(int[] a) {
        int n = a.length;

        // build heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(a, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(a, 0, i);

            heapify(a, i, 0);
        }

        return a;
    }
}
