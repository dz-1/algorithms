package com.dz.algorithms.util;

public class Utilities {
    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private Utilities(){
        //util
    }
}
