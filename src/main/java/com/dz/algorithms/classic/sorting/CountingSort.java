package com.dz.algorithms.classic.sorting;

public class CountingSort {
    static final int RANGE_SIZE = 10;

    private CountingSort() {
        //util
    }

    static int[] sort(int[] a) {
        return sort(a, 1);
    }

    static int getDigit(int n, int exp) {
        return (n / exp) % RANGE_SIZE;
    }

    static int[] sort(int[] a, int exp) {
        int n = a.length;

        int[] count = new int[RANGE_SIZE];

        for (int i = 0; i < n; ++i) {
            count[getDigit(a[i], exp)]++;
        }

        for (int i = 0; i < RANGE_SIZE - 1; ++i) {
            count[i + 1] += count[i];
        }

        int[] buffer = new int[n];

        for (int i = n - 1; i >= 0; --i) {
            int index = getDigit(a[i], exp);
            buffer[count[index] - 1] = a[i];
            count[index]--;
        }

        for (int i = 0; i < n; ++i) {
            a[i] = buffer[i];
        }

        return a;
    }
}
