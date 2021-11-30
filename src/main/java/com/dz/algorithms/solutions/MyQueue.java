package com.dz.algorithms.solutions;

import java.util.Stack;

public class MyQueue<T> {
    private final Stack<T> leftStack = new Stack<>();
    private final Stack<T> rightStack = new Stack<>();

    public void enqueue(T e) {
        leftStack.push(e);
    }

    public void dequeue() {
        flip();

        rightStack.pop();
    }

    private void flip() {
        if (!rightStack.isEmpty()){
            return;
        }

        while (!leftStack.isEmpty()) {
            rightStack.push(leftStack.pop());
        }
    }

    public T peek() {
        flip();

        return rightStack.peek();
    }
}
