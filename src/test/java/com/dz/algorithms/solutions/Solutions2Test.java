package com.dz.algorithms.solutions;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static com.dz.algorithms.solutions.Solutions2.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solutions2Test {
    @Test
    void testLargestRectangle() {
        assertEquals(9, largestRectangle(Arrays.asList(1, 2, 3, 4, 5)));
        assertEquals(6, largestRectangle(Arrays.asList(2, 3, 2)));
    }

    @Test
    void testRiddle() throws Exception {
        assertArrayEquals(new long[]{12L, 2L, 1L, 1L}, riddle(new long[]{2L, 6L, 1L, 12L}));
        assertArrayEquals(new long[]{13L, 3L, 2L, 1L, 1L, 1L, 1L}, riddle(new long[]{1L, 2L, 3L, 5L, 1L, 13L, 3L}));

        String riddleInputs = Files.readString(Path.of(SolutionsTest.class.getClassLoader().getResource("riddleInputs.txt").toURI()));
        long[] inputs = new long[305];
        int index = 0;
        for (String s : riddleInputs.trim().split(" ")) {
            inputs[index++] = Long.parseLong(s);
        }

        String riddleOutputs = Files.readString(Path.of(SolutionsTest.class.getClassLoader().getResource("riddleOutputs.txt").toURI()));
        long[] outputs = new long[305];
        index = 0;
        for (String s : riddleOutputs.trim().split(" ")) {
            outputs[index++] = Long.parseLong(s);
        }

        assertArrayEquals(outputs, riddle(inputs));
    }

    @Test
    void testStepPerms(){
        assertEquals(13, stepPerms(5));
    }

    @Test
    void testSuperDigit(){
        assertEquals(1, superDigit("1", 1));
        assertEquals(2, superDigit("1", 2));
        assertEquals(3, superDigit("148", 3));
        assertEquals(8, superDigit("9875", 4));
        assertEquals(9, superDigit("123", 3));
    }

    @Test
    void testFlippingBits(){
        assertEquals(2147483648L, flippingBits(2147483647L));
        assertEquals(4294967294L, flippingBits(1L));
        assertEquals(4294967295L, flippingBits(0L));
    }

    @Test
    void testPrimality(){
        assertEquals("Not prime", primality(12));
        assertEquals("Prime", primality(5));
        assertEquals("Prime", primality(7));

        for (int i: new int[]{1,4,9,16,25,36,49,64,81,100,121,144,169,196,225,256,289,324,361,400,441,484,529,576,625,676,729,784,841}){
            assertEquals("Not prime", primality(i), String.format("%s is Prime.", i));
        }

        assertEquals("Prime", primality(907));

        assertEquals("Not prime", primality(1000000000));
        assertEquals("Not prime", primality(1000000001));
        assertEquals("Not prime", primality(1000000002));
        assertEquals("Not prime", primality(1000000003));
        assertEquals("Not prime", primality(1000000004));
        assertEquals("Not prime", primality(1000000005));
        assertEquals("Not prime", primality(1000000006));
        assertEquals("Prime", primality(1000000007));
        assertEquals("Not prime", primality(1000000008));
        assertEquals("Prime", primality(1000000009));
    }

    @Test
    void testMaxCircle(){
        int[][] queries = new int[2][2];
        queries[0][0]=1;
        queries[0][1]=2;
        queries[1][0]=1;
        queries[1][1]=3;

        assertArrayEquals(new int[]{2, 3}, maxCircle(queries));
    }
}


