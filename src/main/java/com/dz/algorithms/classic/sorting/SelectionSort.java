package com.dz.algorithms.classic.sorting;

import static com.dz.algorithms.util.Utilities.swap;

public class SelectionSort {
    static int[] sort(int[] a) {
        int n = a.length;

        for (int i=0; i<n; ++i){
            int v = a[i];
            int p = i;
            int min = v;
            for (int j=i+1; j<n; ++j){
                if (a[j]<min){
                    p = j;
                    min=a[j];
                }
            }

            swap(a, i, p);
        }

        return a;
    }

    private SelectionSort(){
        //util
    }
}
