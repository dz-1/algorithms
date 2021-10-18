package com.dz.algorithms.classic.sorting;

import static com.dz.algorithms.util.Utilities.*;

public class RadixSort {
    private RadixSort(){
        //util
    }

    static int[] sort(int[] a){
        int max = maxNonNegative(a);

        for(int exp=1; max/exp>0; exp *= 10){
            CountingSort.sort(a, exp);
        }

        return a;
    }
}
