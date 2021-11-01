package com.dz.algorithms.util;

public class Utilities {
    public static void swap(int[] a, int i, int j) {
        if (i!=j){
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    public static void swap(Integer[] a, int i, int j) {
        if (i!=j){
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    public static int maxNonNegative(int[] a){
        int max = -1;
        for (int i=0; i<a.length; ++i){
            if (a[i]>max){
                max=a[i];
            }
        }

        return max;
    }

    private Utilities(){
        //util
    }
}
