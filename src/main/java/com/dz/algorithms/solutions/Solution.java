package com.dz.algorithms.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.dz.algorithms.util.Utilities.swap;

public class Solution {
    private Solution() {
        //util
    }

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

    static int jumpingOnClouds(List<Integer> c) {
        int n = c.size();
        int currentCloud = 0;
        int num = 0;

        while (currentCloud < n - 2) {
            if (c.get(currentCloud + 2) == 0) {
                currentCloud += 2;
            } else {
                currentCloud += 1;
            }

            num++;
        }


        if (currentCloud == n - 1) {
            return num;
        }

        return num + 1;
    }

    static long repeatedString(String s, long n) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        if (s.equals("a")) {
            return n;
        }

        int l = s.length();
        int m = countA(s);

        if (m == 0) {
            return 0;
        }

        long times = n / l;
        int remain = (int) (n % l);

        long count = m * times;

        if (remain == 0) {
            return count;
        }

        return count + countA(s.substring(0, remain));
    }

    static int countA(String s) {
        int l = s.length();
        int m = 0;
        for (int i = 0; i < l; ++i) {
            if (s.charAt(i) == 'a') {
                m++;
            }
        }

        return m;
    }

    static List<Integer> rotLeft(List<Integer> a, int d) {
        // Write your code here
        List<Integer> shifted = new ArrayList<>();

        for (int i = d; i < a.size(); ++i) {
            shifted.add(a.get(i));
        }

        for (int i = 0; i < d; ++i) {
            shifted.add(a.get(i));
        }

        return shifted;
    }

    static int minimumBribes(List<Integer> q) {
        int count = 0;

        int n = q.size();

        for (int i = 0; i < n; ++i) {
            if (q.get(i) - (i + 1) > 2) {
                return -1;
            }

            for (int j = larger(q.get(i) - 2, 0); j < i; j++) {
                if (q.get(j) > q.get(i)) {
                    count++;
                }
            }
        }

        return count;
    }

    static int larger(int i, int j) {
        return i > j ? i : j;
    }

    static int minimumSwaps(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return 0;
        }

        int count = 0;

        int[] indexDict = new int[n + 1];

        indexDict[0] = -1;//never used

        for (int i = 0; i < n; ++i) {
            indexDict[arr[i]] = i;
        }

        for (int i = 0; i < n; ++i) {
            if (arr[i] != i + 1) {
                int v = arr[i];
                int p = indexDict[i + 1];
                swap(arr, i, p);
                count++;
                indexDict[i + 1] = i;
                indexDict[v] = p;
            }
        }

        return count;
    }

    static String twoStrings(String s1, String s2) {
        int[] charDict = new int[26];

        String shortStr = s1;
        String longStr = s2;

        if (s1.length() > s2.length()) {
            shortStr = s2;
            longStr = s1;
        }

        for (int i = 0; i < shortStr.length(); ++i) {
            int index = shortStr.charAt(i) - 'a';

            charDict[index] = 1;
        }

        for (int i = 0; i < longStr.length(); ++i) {
            int index = longStr.charAt(i) - 'a';

            if (charDict[index] == 1) {
                return "YES";
            }
        }

        return "NO";
    }

    static int sherlockAndAnagrams(String s) {
        int n = s.length();
        String[][] dict = new String[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                dict[i][j] = sortChars(s, i, j);
            }
        }

        HashMap<String, Integer> countMap = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                String key = dict[i][j];
                int count = 1;
                if (countMap.containsKey(key)) {
                    count = countMap.get(key) + 1;
                }

                countMap.put(key, count);
            }
        }

        int count = 0;

        for (int v : countMap.values()) {
            if (v > 1) {
                count += (v * (v - 1)) / 2;
            }
        }

        return count;
    }

    static String sortChars(String s, int begin, int end) {
        int letterNum = 26;
        int[] dict = new int[letterNum];

        for (int i = begin; i <= end; ++i) {
            int index = s.charAt(i) - 'a';
            dict[index]++;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < letterNum; ++i) {
            while (dict[i] > 0) {
                builder.append((char) (i + 'a'));
                dict[i]--;
            }
        }

        return builder.toString();
    }

    static long countTriplets(List<Long> arr, long r) {
        if (r == 1L) {
            return countTriplets(arr);
        }

        long count = 0;
        HashMap<Long, Long> numberCountDict = new HashMap<>();
        HashMap<String, Long> pairCountDict = new HashMap<>();

        for (int i = 0; i < arr.size(); ++i) {
            Long v = arr.get(i);
            long n = 1;

            if (numberCountDict.containsKey(v)) {
                n = numberCountDict.get(v) + 1;
            }

            numberCountDict.put(v, n);

            long p = v / r;
            long pRemain = v % r;
            long pp = v / (r*r);
            long ppRemain = v % (r*r);

            if (pRemain==0 && numberCountDict.containsKey(p)){
                String pair = String.format("%d %d", v, p);

                long m = numberCountDict.get(p);
                if (pairCountDict.containsKey(pair)){
                    m += pairCountDict.get(pair);
                }

                pairCountDict.put(pair, m);
            }

            if (ppRemain==0){
                String pair = String.format("%d %d", p, pp);

                if (pairCountDict.containsKey(pair)){
                    count += pairCountDict.get(pair);
                }
            }
        }

        return count;
    }

    static long countTriplets(List<Long> arr) {
        HashMap<Long, Long> dict = new HashMap<>();

        for (int i = 0; i < arr.size(); ++i) {
            Long v = arr.get(i);
            long n = 1;

            if (dict.containsKey(v)) {
                n = dict.get(v) + 1;
            }

            dict.put(v, n);
        }

        long count = 0;

        for (long num : dict.values()) {
            if (num >= 3) {
                count += num * (num - 1) * (num - 2) / 6;
            }
        }

        return count;
    }
}
