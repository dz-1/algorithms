package com.dz.algorithms.solutions;

import java.util.*;

public class Solutions2 {
    // not optimal
    static long largestRectangle(List<Integer> h) {
        int n = h.size();
        long max = 0;

        for (int i = 0; i < n; ++i) {
            int minHeight = h.get(i);

            for (int j = i; j < n; ++j) {
                int height = h.get(j);
                if (height < minHeight) {
                    minHeight = height;
                }

                long rect = (j - i + 1) * (long) minHeight;

                if (rect > max) {
                    max = rect;
                }
            }

        }

        return max;
    }

    // not optimal
    static long[] riddle(long[] arr) {
        int n = arr.length;
        Stack<Long> stack = new Stack<>();

        for (int size = n; size > 0; --size) {
            long max = -1;
            for (int i = 0; i <= n - size; ++i) {
                long m = min(arr, i, i + size);

                if (m > max) {
                    max = m;
                }
            }

            stack.push(max);
        }

        long[] ans = new long[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = stack.pop();
        }

        return ans;
    }

    static long min(long[] arr, int begin, int end) {
        long min = Long.MAX_VALUE;

        for (int i = begin; i < end; ++i) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        return min;
    }

    static int fibonacci(int n) {
        if (n==0){
            return 0;
        }else if (n<=2){
            return 1;
        }

        return fibonacci(n-1) + fibonacci(n-2);
    }

    static int stepPerms(int n) {
        long[] mem = new long[n+3];
        mem[0]=0;
        mem[1]=1;
        mem[2]=2;
        mem[3]=4;

        for (int i=4; i<=n; ++i){
            mem[i] = mem[i-1] + mem[i-2] + mem[i-3];
        }

        return (int)(mem[n]%10000000007L);
    }

    static int superDigit(String n, int k) {
        long firstSum = sumStringNum(n) * k;

        String s = Long.toString(firstSum);

        while(s.length()>1){
            long sum = sumStringNum(s);
            s = Long.toString(sum);
        }

        return Integer.parseInt(s);
    }

    static long sumStringNum(String s){
        long sum = 0;

        for (int i=0; i<s.length(); ++i){
            sum += (s.charAt(i)-'0');
        }

        return sum;
    }

    static long flippingBits(long n) {
        String binaryStr = leftPad(Long.toBinaryString(n), 32);

        StringBuilder ansBuilder = new StringBuilder();

        for (int i=0; i<binaryStr.length(); ++i){
            char c = binaryStr.charAt(i);
            ansBuilder.append(c=='0'?'1':'0');
        }

        return Long.parseLong(ansBuilder.toString(), 2);
    }

    static String leftPad(String s, int length){
        StringBuilder builder = new StringBuilder();
        int padNum = length - s.length();

        for (int i=0; i<padNum; ++i){
            builder.append('0');
        }

        builder.append(s);

        return builder.toString();
    }

    static String primality(int n) {
        if (n==1){
            return "Not prime";
        }else if (n<=3){
            return "Prime";
        }

        int[] knowPrims = new int[]{5, 7, 11, 13, 17, 19, 23, 29, 31, 37};

        for (int p: knowPrims){
            if (n==p){
                return "Prime";
            }else if (n%p==0){
                return "Not prime";
            }
        }

        int d = n%10;

        switch (d){
            case 0:
            case 2:
            case 4:
            case 5:
            case 6:
            case 8:
                return "Not prime";
            default:
        }

        int sum = sum(Integer.toString(n));

        if (sum%3==0){
            return "Not prime";
        }

        long upperLimit = Math.round(Math.sqrt(n));

        for (int i=2; i<upperLimit; ++i){
            if (n%i==0){
                return "Not prime";
            }
        }

        return "Prime";
    }

    static int sum(String s){
        int sum = 0;

        for (int i=0; i<s.length(); ++i){
            sum += (s.charAt(i)-'0');
        }

        return sum;
    }

    static int[] maxCircle(int[][] queries) {
        List<Set<Integer>> groups = new ArrayList<>();
        int n = queries.length;
        int[] ans = new int[n];

        for (int i=0; i<n; ++i){
            int p1 = queries[i][0];
            int p2 = queries[i][1];

            Set<Integer> group1 = findGroup(p1, groups);
            Set<Integer> group2 = findGroup(p2, groups);

            group1.addAll(group2);

            groups.add(group1);

            ans[i] = maxGroupSize(groups);
        }

        return ans;
    }

    static int maxGroupSize(List<Set<Integer>> groups){
        int maxSize = 0;

        for (Set<Integer> group: groups){
            int size = group.size();
            if (size>maxSize){
                maxSize=size;
            }
        }

        return maxSize;
    }

    static Set<Integer> findGroup(int p, List<Set<Integer>> groups){
        for (int i=0; i<groups.size(); ++i){
            Set<Integer> group = groups.get(i);

            if (group.contains(p)){
                return groups.remove(i);
            }
        }

        Set<Integer>  group =  new HashSet<>();
        group.add(p);

        return group;
    }

}
