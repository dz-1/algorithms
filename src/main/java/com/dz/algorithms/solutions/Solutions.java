package com.dz.algorithms.solutions;

import java.util.*;

import static com.dz.algorithms.util.Utilities.swap;

public class Solutions {
    private Solutions() {
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
            long pp = v / (r * r);
            long ppRemain = v % (r * r);

            if (pRemain == 0 && numberCountDict.containsKey(p)) {
                String pair = String.format("%d %d", v, p);

                long m = numberCountDict.get(p);
                if (pairCountDict.containsKey(pair)) {
                    m += pairCountDict.get(pair);
                }

                pairCountDict.put(pair, m);
            }

            if (ppRemain == 0) {
                String pair = String.format("%d %d", p, pp);

                if (pairCountDict.containsKey(pair)) {
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

    static List<Integer> freqQuery(List<List<Integer>> queries) {
        HashMap<Integer, Integer> valueFreqStore = new HashMap<>();
        HashMap<Integer, Integer> freqValueStore = new HashMap<>();

        List<Integer> result = new ArrayList<>();
        for (List<Integer> query : queries) {
            int operation = query.get(0);
            int value = query.get(1);

            switch (operation) {
                case 1:
                    addValue(value, valueFreqStore, freqValueStore);
                    break;
                case 2:
                    removeValue(value, valueFreqStore, freqValueStore);
                    break;
                case 3:
                    if (freqValueStore.containsKey(value)) {
                        result.add(1);
                    } else {
                        result.add(0);
                    }

                    break;
                default:
            }
        }

        return result;
    }

    static void addValue(int value, HashMap<Integer, Integer> valueFreqStore, HashMap<Integer, Integer> freqValueStore) {
        int lastFreq = 0;

        if (valueFreqStore.containsKey(value)) {
            lastFreq = valueFreqStore.get(value);
        }

        int freq = lastFreq + 1;

        valueFreqStore.put(value, freq);

        if (lastFreq != 0) {
            int n = freqValueStore.get(lastFreq) - 1;

            if (n == 0) {
                freqValueStore.remove(lastFreq);
            } else {
                freqValueStore.put(lastFreq, n);
            }
        }

        int m = 1;

        if (freqValueStore.containsKey(freq)) {
            m = freqValueStore.get(freq) + 1;
        }

        freqValueStore.put(freq, m);
    }

    static void removeValue(int value, HashMap<Integer, Integer> valueFreqStore, HashMap<Integer, Integer> freqValueStore) {
        if (!valueFreqStore.containsKey(value)) {
            return;
        }

        int lastFreq = valueFreqStore.get(value);

        int freq = lastFreq - 1;

        if (freq == 0) {
            valueFreqStore.remove(value);
        } else {
            valueFreqStore.put(value, freq);
        }

        int n = freqValueStore.get(lastFreq) - 1;

        if (n == 0) {
            freqValueStore.remove(lastFreq);
        } else {
            freqValueStore.put(lastFreq, n);
        }

        if (freq != 0) {
            int m = 1;

            if (freqValueStore.containsKey(freq)) {
                m = freqValueStore.get(freq) + 1;
            }

            freqValueStore.put(freq, m);
        }
    }

    static int[] quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pivot = a[high];

            int i = low - 1;

            for (int j = low; j <= high; ++j) {
                if (a[j] < pivot) {
                    i++;
                    swap(a, i, j);
                }
            }

            swap(a, i + 1, high);

            quickSort(a, low, i);
            quickSort(a, i + 1, high);
        }

        return a;
    }

    static int[] heapSort(int[] a) {
        int n = a.length;

        for (int i = (n - 1) / 2; i >= 0; i--) {
            heapify(a, i, n);
        }

        for (int i = n - 1; i >= 0; i--) {
            swap(a, i, 0);

            heapify(a, 0, i);
        }

        return a;
    }

    static void heapify(int[] a, int r, int n) {
        int left = 2 * r + 1;
        int right = 2 * r + 2;
        int smallest = r;

        if (left < n && a[left] < a[smallest]) {
            smallest = left;
        }

        if (right < n && a[right] < a[smallest]) {
            smallest = right;
        }

        if (r != smallest) {
            swap(a, r, smallest);

            heapify(a, smallest, n);
        }
    }

    static int maximumToys(int[] a, int k) {
        int n = a.length;

        for (int i = (n - 1) / 2; i >= 0; i--) {
            heapify(a, i, n);
        }

        int count = 0;
        int used = 0;

        for (int i = n - 1; i >= 0; i--) {
            swap(a, i, 0);

            heapify(a, 0, i);

            used += a[i];

            if (used < k) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    static int activityNotifications2(List<Integer> expenditure, int d) {
        if (expenditure.size() <= d) {
            return 0;
        }

        List<Integer> window = new LinkedList<>();

        for (int i = 0; i < d; ++i) {
            window.add(expenditure.get(i));
        }

        window.sort((a, b) -> a - b);
        int pos = (d - 1) / 2;

        int count = 0;
        for (int i = d; i < expenditure.size(); ++i) {
            int limit = window.get(pos);
            if (d % 2 == 0) {
                limit += window.get(pos + 1);
            } else {
                limit *= 2;
            }

            int exp = expenditure.get(i);

            if (exp >= limit) {
                count++;

                //System.out.println("count="+count);
            }

            window.remove(expenditure.get(i - d));
            window.add(exp);

            window.sort((a, b) -> a - b);
        }

        return count;
    }

    static int activityNotifications(List<Integer> expenditure, int d) {
        if (expenditure.size() <= d) {
            return 0;
        }

        Map<Integer, Integer> countMap = new TreeMap<>();

        for (int i = 0; i < d; ++i) {
            int exp = expenditure.get(i);

            int count = 1;

            if (countMap.containsKey(exp)) {
                count = countMap.get(exp) + 1;
            }

            countMap.put(exp, count);
        }

        int count = 0;
        for (int i = d; i < expenditure.size(); ++i) {
            int limit = getLimit(countMap, d);
            int exp = expenditure.get(i);

            if (exp >= limit) {
                count++;
            }

            refreshWindow(countMap, expenditure.get(i - d), exp);
        }

        return count;
    }

    static int getLimit(Map<Integer, Integer> countMap, int d) {
        int range = 0;
        int pos = (d - 1) / 2;

        if (d % 2 == 1) {
            for (Map.Entry<Integer, Integer> e : countMap.entrySet()) {
                range += e.getValue();

                if (range > pos) {
                    return e.getKey() * 2;
                }
            }
        } else {
            int sum = 0;

            for (Map.Entry<Integer, Integer> e : countMap.entrySet()) {
                range += e.getValue();

                if (range > pos && sum == 0) {
                    sum += e.getKey();
                }

                if (range > pos + 1) {
                    sum += e.getKey();
                    break;
                }
            }

            return sum;
        }

        return -1;
    }

    static void refreshWindow(Map<Integer, Integer> countMap, Integer firstInWindow, Integer newValue) {
        if (firstInWindow.equals(newValue)) {
            return;
        }

        int freq = countMap.get(firstInWindow);

        if (freq == 1) {
            countMap.remove(firstInWindow);
        } else {
            countMap.put(firstInWindow, freq - 1);
        }

        freq = 1;

        if (countMap.containsKey(newValue)) {
            freq = countMap.get(newValue) + 1;
        }

        countMap.put(newValue, freq);
    }

    static int[] radixSort(int[] a) {
        int max = getMax(a);


        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(a, exp);
        }

        return a;
    }

    static int getMax(int[] a) {
        int max = -1;

        for (int v : a) {
            if (v > max) {
                max = v;
            }
        }

        return max;
    }

    static int[] countingSort(int[] a, int exp) {
        int n = a.length;
        int rangeSize = 10;
        int[] count = new int[rangeSize];

        for (int i = 0; i < n; ++i) {
            count[(a[i] / exp) % rangeSize]++;
        }

        for (int i = 1; i < rangeSize; ++i) {
            count[i] += count[i - 1];
        }

        int[] b = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            b[count[(a[i] / exp) % rangeSize] - 1] = a[i];
            count[(a[i] / exp) % rangeSize]--;
        }

        for (int i = 0; i < n; ++i) {
            a[i] = b[i];
        }

        return b;
    }

    static long countInversions1(int[] arr) {
        int count = 0;
        int n = arr.length;
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            for (int i = 0; i < n - 1; ++i) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    count++;
                    swapped = true;
                }
            }
        }

        return count;
    }

    static long countInversions(int[] arr) {
        int n = arr.length;

        int[] buff = new int[n];
        List<Long> countList = new ArrayList<>();

        mergeSort(arr, 0, n - 1, buff, countList);

        long count = 0;

        for (long c : countList) {
            count += c;
        }

        return count;
    }

    static void mergeSort(int[] arr, int low, int high, int[] buff, List<Long> countList) {
        if (low >= high) {
            return;
        }

        int mid = (low + high) / 2;

        mergeSort(arr, low, mid, buff, countList);
        mergeSort(arr, mid + 1, high, buff, countList);

        merge(arr, low, mid, high, buff, countList);
    }

    static void merge(int[] arr, int low, int mid, int high, int[] buff, List<Long> countList) {
        int i = low;
        int j = mid + 1;
        int k = low;

        long count = 0;

        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                buff[k] = arr[i];
                i++;
            } else {
                buff[k] = arr[j];
                j++;

                count += (mid - i) + 1;
            }

            k++;
        }

        while (i <= mid) {
            buff[k] = arr[i];
            i++;
            k++;
        }

        while (j <= high) {
            buff[k] = arr[j];
            j++;
            k++;
        }

        for (int p = low; p <= high; ++p) {
            arr[p] = buff[p];
        }

        countList.add(count);
    }

    static int makeAnagram(String a, String b) {
        int n = 26;
        int[] countA = new int[n];
        int[] countB = new int[n];

        for (int i = 0; i < a.length(); ++i) {
            char c = a.charAt(i);
            countA[c - 'a']++;
        }

        for (int i = 0; i < b.length(); ++i) {
            char c = b.charAt(i);
            countB[c - 'a']++;
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            int diff = countA[i] - countB[i];

            if (diff > 0) {
                count += diff;
            } else {
                count -= diff;
            }
        }

        return count;
    }

    static int alternatingCharacters(String s) {
        int count = 0;
        int c = s.charAt(0);
        for (int i = 1; i < s.length(); ++i) {
            int d = s.charAt(i);
            if (d == c) {
                count++;
            }

            c = d;
        }

        return count;
    }

    static String isValid(String s) {
        int n = 26;
        int[] countLetters = new int[n];

        for (int i = 0; i < s.length(); ++i) {
            countLetters[s.charAt(i) - 'a']++;
        }

        Map<Integer, Integer> freqMap = new TreeMap<>();

        for (int i = 0; i < n; ++i) {
            int freq = countLetters[i];
            if (freq != 0) {
                int count = 1;

                if (freqMap.containsKey(freq)) {
                    count = freqMap.get(freq) + 1;
                }

                freqMap.put(freq, count);
            }
        }

        if (freqMap.size() == 1) {
            return "YES";
        } else if (freqMap.size() != 2) {
            return "NO";
        }

        int[][] freqArray = new int[2][2];
        int i = 0;

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            freqArray[i][0] = entry.getKey();
            freqArray[i][1] = entry.getValue();
            i++;
        }

        if (freqArray[0][1] == 1) {
            return "YES";
        } else if (freqArray[1][1] == 1 && freqArray[1][0] - freqArray[0][0] == 1) {
            return "YES";
        }

        return "NO";
    }

    static long substrCount(int n, String s) {
        long count = n;

        List<Integer> lengthBuff = new ArrayList<>();
        List<Character> charBuff = new ArrayList<>();

        Map<Integer, Integer> lengthFreq = new HashMap();

        char c = s.charAt(0);
        int num = 1;

        for (int i = 1; i < n; ++i) {
            char d = s.charAt(i);
            if (d == c) {
                num++;
            } else {
                lengthBuff.add(num);
                charBuff.add(c);

                addFreq(lengthFreq, num);

                num = 1;
            }

            c = d;
        }

        lengthBuff.add(num);
        charBuff.add(c);
        addFreq(lengthFreq, num);

        for (int len = 2; len <= n; len++) {
            for (Map.Entry<Integer, Integer> entry : lengthFreq.entrySet()) {
                int repeatNum = entry.getKey();
                int freq = entry.getValue();

                if (repeatNum >= len) {
                    count += (repeatNum - len + 1) * freq;
                }
            }
        }

        Map<Integer, Integer> oddFreq = new HashMap();

        for (int i = 0; i < lengthBuff.size(); ++i) {
            if (lengthBuff.get(i) == 1
                    && i > 0
                    && i < lengthBuff.size() - 1
                    && charBuff.get(i - 1).equals(charBuff.get(i + 1))) {
                int left = lengthBuff.get(i - 1);
                int right = lengthBuff.get(i + 1);
                int min = left <= right ? left : right;

                int subLen = min * 2 + 1;

                addFreq(oddFreq, subLen);
            }
        }

        for (int len = 3; len <= n; len += 2) {
            for (Map.Entry<Integer, Integer> entry : oddFreq.entrySet()) {
                if (entry.getKey() >= len) {
                    count += entry.getValue();
                }
            }
        }

        return count;
    }

    static void addFreq(Map<Integer, Integer> freqMap, int value) {
        int freq = 1;
        if (freqMap.containsKey(value)) {
            freq = freqMap.get(value) + 1;
        }

        freqMap.put(value, freq);
    }

    static int commonChild(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] mem = new int[m+1][n+1];

        // all finals are 0

        for (int i=m-1; i>=0; --i){
            for (int j=n-1; j>=0; --j){
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(j);

                if (c1==c2){
                    mem[i][j] = mem[i+1][j+1]+1;
                }else{
                    mem[i][j] = max(max(mem[i+1][j], mem[i][j+1]), mem[i+1][j+1]);
                }
            }
        }

        return mem[0][0];
    }

    static int commonChild2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] cache = new int[m + 1][n + 1];

        for (int i = 1; i <= m; ++i) {
            char x = s1.charAt(i - 1);

            for (int j = 1; j <= n; ++j) {
                char y = s2.charAt(j - 1);

                if (x == y) {
                    cache[i][j] = cache[i - 1][j - 1] + 1;
                } else {
                    cache[i][j] = max(cache[i - 1][j], cache[i][j - 1]);
                }
            }
        }

        return cache[m][n];
    }

    static int max(int i, int j) {
        return i >= j ? i : j;
    }

    static int minimumAbsoluteDifference(List<Integer> arr) {
        arr.sort((a, b) -> a - b);

        int min = Integer.MAX_VALUE;

        for (int i = 1; i < arr.size(); ++i) {
            int diff = Math.abs(arr.get(i) - arr.get(i - 1));

            if (diff < min) {
                min = diff;
            }
        }

        return min;
    }

    static int luckBalance(int k, List<List<Integer>> contests) {
        int maxLuck = 0;

        List<Integer> importantLucks = new ArrayList<>();

        for (List<Integer> contest : contests) {
            if (contest.get(1) == 0) {
                maxLuck += contest.get(0);
            } else {
                importantLucks.add(contest.get(0));
            }
        }

        if (k == importantLucks.size()) {
            for (int luck : importantLucks) {
                maxLuck += luck;
            }
        } else {
            importantLucks.sort((a, b) -> a - b);
            int start = max(importantLucks.size() - k, 0);

            for (int i = start; i < importantLucks.size(); ++i) {
                maxLuck += importantLucks.get(i);
            }

            for (int i = 0; i < start; ++i) {
                maxLuck -= importantLucks.get(i);
            }
        }

        return maxLuck;
    }

    static int getMinimumCost(int k, int[] c) {
        int range = 100;
        int minCost = 0;
        int n = c.length;

        if (k >= n) {
            for (int v : c) {
                minCost += v;
            }
        } else {
            Arrays.sort(c);

            int times = 0;
            for (int i = n - 1; i >= 0; i -= k) {
                for (int j = 0; j < k && j <= i; j++) {
                    minCost += (times + 1) * c[i - j];
                }

                times++;
            }
        }

        return minCost;
    }

    static int maxMin(int k, List<Integer> arr) {
        int min = Integer.MAX_VALUE;
        int n = arr.size();

        arr.sort((a, b) -> a - b);

        for (int i = 0; i < n - (k - 1); ++i) {
            int diff = arr.get(i + k - 1) - arr.get(i);

            if (diff < min) {
                min = diff;
            }
        }

        return min;
    }

    static int[] whatFlavors(List<Integer> cost, int money) {
        Map<Integer, List<Integer>> costDict = new HashMap<>();

        for (int i = 0; i < cost.size(); ++i) {
            int c = cost.get(i);

            List<Integer> indexes = costDict.computeIfAbsent(c, k -> new ArrayList<Integer>());

            indexes.add(i + 1);
        }

        for (Map.Entry<Integer, List<Integer>> entry : costDict.entrySet()) {
            int c = entry.getKey();

            int d = money - c;

            if (costDict.containsKey(d)) {
                if (d == c && entry.getValue().size() >= 2) {
                    return buildResult(entry.getValue().get(0), entry.getValue().get(1));
                } else {
                    return buildResult(entry.getValue().get(0), costDict.get(d).get(0));
                }
            }
        }

        return new int[0];
    }

    static int[] buildResult(int i, int j) {
        int[] result = new int[2];

        if (i > j) {
            result[0] = j;
            result[1] = i;
        } else {
            result[0] = i;
            result[1] = j;
        }

        return result;
    }

    static int pairs(int k, List<Integer> arr) {
        Map<Integer, Integer> valueDict = new HashMap<>();

        for (int v : arr) {
            int count = 1;
            if (valueDict.containsKey(v)) {
                count = valueDict.get(v) + 1;
            }

            valueDict.put(v, count);
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : valueDict.entrySet()) {
            int v = entry.getKey();
            int d = v + k;
            if (valueDict.containsKey(d)) {
                count += min(entry.getValue(), valueDict.get(d));
            }
        }

        return count;
    }

    static int min(int i, int j) {
        return i <= j ? i : j;
    }

    static long triplets(int[] a, int[] b, int[] c) {
        TreeSet<Integer> setA = new TreeSet<>();
        TreeSet<Integer> setB = new TreeSet<>();
        TreeSet<Integer> setC = new TreeSet<>();

        populate(setA, a);
        populate(setB, b);
        populate(setC, c);

        int[] uniqA = toArray(setA);
        int[] uniqC = toArray(setC);

        long count = 0;
        int ai = 0;
        int ci = 0;

        for (int bv : setB) {
            while (ai < uniqA.length && uniqA[ai] <= bv) {
                ai++;
            }

            while (ci < uniqC.length && uniqC[ci] <= bv) {
                ci++;
            }

            count += ((long) ai) * ((long) ci);
        }

        return count;
    }

    static void populate(Set<Integer> set, int[] a) {
        for (int v : a) {
            set.add(v);
        }
    }

    static int[] toArray(Set<Integer> set) {
        int[] a = new int[set.size()];
        int index = 0;
        for (int v : set) {
            a[index] = v;
            index++;
        }

        return a;
    }

    static long minTime(Long[] machines, long goal) {
        long days = 1;

        Arrays.sort(machines);

        long minPeriod = machines[0];
        int n = machines.length;

        long minDays = 1;
        long maxDays = goal * minPeriod;

        long productNum = 0;

        while (minDays <= maxDays) {
            days = (minDays + maxDays) / 2;

            productNum = 0;

            for (int i = 0; i < n; ++i) {
                productNum += days / machines[i];
            }

            if (productNum == goal) {
                break;
            } else if (productNum > goal) {
                maxDays = days - 1;
            } else {
                minDays = days + 1;
            }
        }

        if (productNum < goal) {// no exact days matching goal
            return days + 1;
        } else { // multiple exact days matching goal
            for (long i = days; i >= 0; --i) {
                long count = 0;

                for (int j = 0; j < n; ++j) {
                    count += i / machines[j];
                }

                if (count >= goal) {
                    days = i;
                } else {
                    break;
                }
            }
        }

        return days;
    }

    static int maxSubsetSum(Integer[] arr) {
        int n = arr.length;

        int[] mem = new int[n];

        mem[n - 1] = max(arr[n - 1], 0);

        if (n == 1) {
            return mem[n - 1];
        }

        mem[n - 2] = max(arr[n - 2], 0);

        if (n == 2) {
            return max(mem[n - 1], mem[n - 2]);
        }

        mem[n - 3] = max(arr[n - 3], 0) + mem[n - 1];

        if (n == 3) {
            return max(mem[n - 2], mem[n - 3]);
        }

        for (int i = n - 4; i >= 0; --i) {
            int v = max(arr[i], 0);
            mem[i] = max(v + mem[i + 2], v + mem[i + 3]);
        }

        return max(mem[0], mem[1]);
    }

    static int maxSubsetSum2(Integer[] arr) {
        int n = arr.length;
        int countPositive = 0;

        int[] dict = new int[n];

        dict[0] = max(arr[0], 0);
        dict[1] = max(dict[0], arr[1]);
        dict[2] = max(dict[1], dict[0] + arr[2]);

        int max = max(max(dict[0], dict[1]), dict[2]);

        for (int i = 3; i < n; ++i) {
            if (arr[i] <= 0) {
                dict[i] = dict[i - 1];
            } else {
                dict[i] = max(max(dict[i - 2] + arr[i], dict[i - 3] + arr[i]), dict[i - 1]);
            }

            if (dict[i] > max) {
                max = dict[i];
            }
        }

        return max;
    }

    static String abbreviation2(String a, String b) {
        return abbreviation2(a.toCharArray(), b.toCharArray())>0 ? "YES" : "NO";
    }

    static int abbreviation2(char[] a, char[] b) {
        int m = a.length;
        int n = b.length;
        int[][] mem = new int[m + 1][n + 1];

        /** -- initialization begin
         * final 1: f([], []) = 1
         * final 2: f(ai, []) = are all ai lower ? 1 :0
         * final 3: f([], bi) = 0 //not match. no code needed
         */

        //final 1
        mem[m][n] = 1;

        //final 2
        for (int ai = m - 1; ai >= 0; --ai) {
            mem[ai][n] = Character.isLowerCase(a[ai]) ? mem[ai + 1][n] : 0;
        }

        // -- initialization end

        for (int ai = m - 1; ai >= 0; --ai) {
            for (int bi = n - 1; bi >= 0; --bi) {
                char ac = a[ai];
                char bc = b[bi];

                if (ac == bc) {
                    mem[ai][bi] = mem[ai + 1][bi + 1];
                } else if (Character.toUpperCase(ac) == bc) {
                    // this causes integer overflow
                    mem[ai][bi] = mem[ai + 1][bi + 1] + mem[ai + 1][bi];
                } else if (Character.isLowerCase(ac)) {
                    mem[ai][bi] = mem[ai + 1][bi];
                }
            }
        }

        return mem[0][0];
    }

    static String abbreviation(String a, String b) {
        return abbreviation(a.toCharArray(), b.toCharArray()) ? "YES" : "NO";
    }

    static boolean abbreviation(char[] a, char[] b) {
        int m = a.length;
        int n = b.length;
        boolean[][] mem = new boolean[m + 1][n + 1];

        /** -- initialization begin
         * final 1: f([], []) = true
         * final 2: f(ai, []) = are all ai lower ? true :false
         * final 3: f([], bi) = false //not match. no code needed
         */

        //final 1
        mem[m][n] = true;

        //final 2
        for (int ai = m - 1; ai >= 0; --ai) {
            mem[ai][n] = Character.isLowerCase(a[ai]) && mem[ai + 1][n];
        }

        // -- initialization end

        for (int ai = m - 1; ai >= 0; --ai) {
            for (int bi = n - 1; bi >= 0; --bi) {
                char ac = a[ai];
                char bc = b[bi];

                if (ac == bc) {
                    mem[ai][bi] = mem[ai + 1][bi + 1];
                } else if (Character.toUpperCase(ac) == bc) {
                    mem[ai][bi] = mem[ai + 1][bi + 1] || mem[ai + 1][bi];
                } else if (Character.isLowerCase(ac)) {
                    mem[ai][bi] = mem[ai + 1][bi];
                }
            }
        }

        return mem[0][0];
    }

    static long candies1(int n, List<Integer> arr) {
        int[] candyRecords = new int[n];
        candyRecords[0] = 1;

        for (int i = 1; i < n; ++i) {
            if (arr.get(i) > arr.get(i - 1)) {
                candyRecords[i] = candyRecords[i - 1] + 1;
            } else if (arr.get(i) <= arr.get(i - 1)) {
                candyRecords[i] = 1;
            }
        }

        for (int i = n - 1; i > 0; --i) {
            if (arr.get(i) < arr.get(i - 1) && candyRecords[i - 1] <= candyRecords[i]) {
                candyRecords[i - 1]++;
            }
        }

        long num = 0;

        for (int i = 0; i < n; ++i) {
            num += candyRecords[i];
        }

        return num;
    }

    static long candies(int n, List<Integer> arr) {
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = 0;
        for (int i = 1; i < n; ++i) {
            if (arr.get(i) > arr.get(i - 1)) {
                left[i] = 1;
            }
        }

        right[n - 1] = 0;

        for (int i = n - 2; i >= 0; --i) {
            if (arr.get(i) > arr.get(i + 1)) {
                right[i] = 1;
            }
        }

        for (int i = 1; i < n; ++i) {
            if (left[i - 1] > 0 && left[i] > 0) {
                left[i] = left[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; --i) {
            if (right[i] > 0 && right[i + 1] > 0) {
                right[i] = right[i + 1] + 1;
            }
        }

        long num = 0;

        for (int i = 0; i < n; ++i) {
            num += max(left[i], right[i]) + 1;
        }

        return num;
    }
}
