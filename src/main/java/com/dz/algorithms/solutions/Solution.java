package com.dz.algorithms.solutions;

import java.util.List;

public class Solution {
    static int sockMerchant(int n, List<Integer> ar) {
        int num = 0;

        if (n < 2) return num;

        int[] buffer = new int[100];

        for (int i = 0; i < n; ++i) {
            int color = ar.get(i);
            buffer[color - 1]++;
        }

        for (int i = 0; i < 100; ++i) {
            num += buffer[i] / 2;
        }

        return num;

    }

    static int countingValleys(int steps, String path) {
        int level = 0;
        int lastLevel = 0;
        int count = 0;

        for (int i = 0; i < steps; ++i) {
            char c = path.charAt(i);
            switch (c) {
                case 'U':
                    level++;
                    break;
                case 'D':
                    level--;
                    break;
                default:

            }

            if (level == -1 && lastLevel == 0) {
                count++;
            }

            lastLevel = level;
        }

        return count;
    }

    private Solution(){
        //util
    }
}
