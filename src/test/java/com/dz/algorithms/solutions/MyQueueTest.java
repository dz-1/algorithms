package com.dz.algorithms.solutions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyQueueTest {

    @Test
    void test(){
        MyQueue<Integer> queue = new MyQueue<>();

        List<List<Integer>> instructions = new ArrayList<>();

        instructions.add(Arrays.asList(1, 42));
        instructions.add(Arrays.asList(2, 0));
        instructions.add(Arrays.asList(1, 14));
        instructions.add(Arrays.asList(3, 0));
        instructions.add(Arrays.asList(1, 28));
        instructions.add(Arrays.asList(3, 0));
        instructions.add(Arrays.asList(1, 60));
        instructions.add(Arrays.asList(1, 78));
        instructions.add(Arrays.asList(2, 0));
        instructions.add(Arrays.asList(2, 0));


        for (int i = 0; i < instructions.size(); i++) {
            int operation = instructions.get(i).get(0);
            if (operation == 1) { // enqueue
                queue.enqueue(instructions.get(i).get(1));
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
    }
}
