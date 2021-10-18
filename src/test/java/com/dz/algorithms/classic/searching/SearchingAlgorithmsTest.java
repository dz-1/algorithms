package com.dz.algorithms.classic.searching;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchingAlgorithmsTest {
    @Test
    void testBinarySearch(){
        assertEquals(-1, BinarySearch.search(new int[]{}, 1));
        assertEquals(0, BinarySearch.search(new int[]{1}, 1));
        assertEquals(5, BinarySearch.search(new int[]{11, 12, 22, 25, 34, 64, 90}, 64));
    }
}
