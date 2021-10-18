package com.dz.algorithms.classic.sorting;

public class MergeSort {
    private MergeSort() {
        //util
    }

    static void merge(int[] a, int low, int mid, int high, int[] buffer) {
        int i = low;
        int j = mid + 1;
        int p = low;

        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                buffer[p++] = a[i++];
            } else {
                buffer[p++] = a[j++];
            }
        }

        while (i <= mid) {
            buffer[p++] = a[i++];
        }

        while (j <= high) {
            buffer[p++] = a[j++];
        }

        for (int k=low; k<=high; ++k){
            a[k] = buffer[k];
        }
    }

    static void sort(int[] a, int low, int high, int[] buffer) {
        if (low < high) {
            int mid = (low + high) / 2;

            sort(a, low, mid, buffer);
            sort(a, mid + 1, high, buffer);

            merge(a, low, mid, high, buffer);
        }
    }

    static int[] sort(int[] a) {
        int n = a.length;
        int[] buffer = new int[n];
        sort(a, 0, n-1, buffer);
        return a;
    }
}
