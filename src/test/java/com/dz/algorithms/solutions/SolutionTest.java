package com.dz.algorithms.solutions;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.dz.algorithms.solutions.Solution.*;
import static com.dz.algorithms.solutions.Solution.countTriplets;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    void testSockMerchant(){
        assertEquals(3, sockMerchant(9, Arrays.asList(10, 20, 20, 10, 10, 30, 50, 10, 20)));
    }

    @Test
    void testCountingValleys(){
        assertEquals(1, countingValleys(8, "UDDDUDUU"));
    }

    @Test
    void testJumpingOnClouds(){
        assertEquals(4, jumpingOnClouds(Arrays.asList(0, 0, 1, 0, 0, 1, 0)));
    }

    @Test
    void testRepeatedString(){
        assertEquals(7, repeatedString("aba", 10L));
    }

    @Test
    void testRotLeft(){
        assertArrayEquals(new Integer[]{5, 1, 2, 3, 4}, rotLeft(Arrays.asList(1, 2, 3, 4, 5), 4).toArray(new Integer[0]));
    }

    @Test
    void testMinimumBribes(){
        assertEquals(3, minimumBribes(Arrays.asList(2, 1, 5, 3, 4)));
        assertEquals(-1, minimumBribes(Arrays.asList(2, 5, 1, 3, 4)));
        assertEquals(7, minimumBribes(Arrays.asList(1, 2, 5, 3, 7, 8, 6, 4)));
    }

    @Test
    void testMinimumSwaps(){
        assertEquals(5, minimumSwaps(new int[]{7, 1, 3, 2, 4, 5, 6}));
        assertEquals(3, minimumSwaps(new int[]{4, 3, 1, 2}));
        assertEquals(3, minimumSwaps(new int[]{2, 3, 4, 1, 5}));
        assertEquals(3, minimumSwaps(new int[]{1, 3, 5, 2, 4, 6, 7}));
    }

    @Test
    void testTwoStrings(){
        String yes = "YES";
        String no = "NO";
        assertEquals(yes, twoStrings("hello", "world"));
        assertEquals(yes, twoStrings("kid", "world"));
        assertEquals(no, twoStrings("hi", "world"));
    }

    @Test
    void testSherlockAndAnagrams(){
        assertEquals(2, sherlockAndAnagrams("mom"));
        assertEquals(10, sherlockAndAnagrams("kkkk"));
        assertEquals(3, sherlockAndAnagrams("ifailuhkqq"));
    }

    @Test
    void testCountTriplets() throws URISyntaxException, IOException {
        assertEquals(2, countTriplets(Arrays.asList(1L, 2L, 2L, 4L), 2L));
        assertEquals(6, countTriplets(Arrays.asList(1L, 3L, 9L, 9L, 27L, 81L), 3L));
        assertEquals(4, countTriplets(Arrays.asList(1L, 5L, 5L, 25L, 125L), 5L));

        assertEquals(161700, countTriplets(Arrays.asList(1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,
                1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,
                1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,
                1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L), 1L));

        assertEquals(161700, countTriplets(Arrays.asList(5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L,
                5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L,
                5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L,
                5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L), 1L));

        String numbers = Files.readString( Path.of(SolutionTest.class.getClassLoader().getResource("numbers.txt").toURI()));
        List<Long> numberList =  Arrays.stream(numbers.trim().split(" ")).map(Long::parseLong).collect(Collectors.toList());

        assertEquals(2325652489L, countTriplets(numberList, 3L));
    }
}
