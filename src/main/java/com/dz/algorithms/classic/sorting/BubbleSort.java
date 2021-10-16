package com.dz.algorithms.classic.sorting;

import static com.dz.algorithms.util.Utilities.swap;

public class BubbleSort {
    static int[] sort(int[] a) {
        int n = a.length;
        for (int i = n - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (a[j + 1] < a[j]) {
                    swap(a, j, j + 1);
                }
            }
        }

        return a;
    }

    private BubbleSort(){
        //util
    }
}
