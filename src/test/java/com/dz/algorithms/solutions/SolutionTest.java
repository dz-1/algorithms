package com.dz.algorithms.solutions;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.dz.algorithms.solutions.Solution.*;
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
}
