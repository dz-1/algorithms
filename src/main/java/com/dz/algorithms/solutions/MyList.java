package com.dz.algorithms.solutions;

public class MyList {
    class Node {
        int data;
        Node next;
    }

    boolean hasCycle(Node head) {
        Node r = head;
        Node t = head;

        int round = 0;

        while (r!=null && t!=null){
            r=r.next;

            if (round%2==0){
                t=t.next;
            }else {
                if (r==t){
                    return true;
                }
            }

            round++;

        }

        return false;
    }
}
