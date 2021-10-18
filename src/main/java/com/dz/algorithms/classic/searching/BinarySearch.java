package com.dz.algorithms.classic.searching;

public class BinarySearch {
    private BinarySearch() {
        //util
    }

    static int search(int[] a, int v) {
        int low = 0;
        int high = a.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (a[mid] == v) {
                return mid;
            } else if (a[mid] > v) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }

        return -1;
    }

}
