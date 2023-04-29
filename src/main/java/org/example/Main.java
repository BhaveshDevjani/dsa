package org.example;

import org.example.binarytrees.BinaryTreeNode;
import org.example.binarytrees.BinaryTreeUseHelper;

public class Main {
    public static void main(String[] args) {
//        1 2 3 4 5 6 7 -1 -1 -1 -1 -1 -1 -1 -1
        BinaryTreeNode<Integer> root = BinaryTreeUseHelper.takeInputLevelWise();
        System.out.println(BinaryTreeUseHelper.size(root));
    }
}