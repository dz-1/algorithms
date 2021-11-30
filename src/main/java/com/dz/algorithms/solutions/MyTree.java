package com.dz.algorithms.solutions;

import java.util.HashSet;
import java.util.Set;

public class MyTree {
    class Node {
        int data;
        Node left;
        Node right;
    }

    boolean checkBST(Node root) {
        Set<Integer> valueSet = new HashSet<>();

        return checkBST(root, valueSet);
    }

    boolean checkBST(Node root, Set<Integer> valueSet) {
        if (root==null){
            return true;
        }

        if (valueSet.contains(root.data)){
            return false;
        }

        if (root.left!=null && root.left.data>=root.data ){
            return false;
        }

        if (root.right!=null && root.right.data<=root.data){
            return false;
        }

        valueSet.add(root.data);

        return checkBST(root.left, valueSet) && checkBST(root.right, valueSet);
    }
}
