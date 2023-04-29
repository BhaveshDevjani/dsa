package org.example.binarytrees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryTreeUseHelper {
    private static Scanner sc = new Scanner(System.in);

    public static BinaryTreeNode<Integer> takeInput() {
        System.out.println("Enter Root Data");
        int rootData = sc.nextInt();

        if (rootData == -1) {
            return null;
        }
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(rootData);
        node.left = takeInput();
        node.right = takeInput();
        return node;
    }

    public static void print(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        String s = root.data + ": ";
        if (root.left != null) {
            s += "L" + root.left.data + ", ";
        }
        if (root.right != null) {
            s += "R" + root.right.data;
        }
        System.out.println(s);
        print(root.left);
        print(root.right);
    }

    public static BinaryTreeNode<Integer> takeInputLevelWise() {
        System.out.println("Enter root data");
        int rootData = sc.nextInt();
        if (rootData == -1) {
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);
        Queue<BinaryTreeNode<Integer>> pendingQueue = new LinkedList<>();
        pendingQueue.add(root);

        while (!pendingQueue.isEmpty()) {
            BinaryTreeNode<Integer> node = pendingQueue.remove();
            System.out.println("Enter left child for node: " + node.data);
            int leftData = sc.nextInt();
            if (leftData != -1) {
                BinaryTreeNode<Integer> leftNode = new BinaryTreeNode<>(leftData);
                node.left = leftNode;
                pendingQueue.add(leftNode);
            }
            System.out.println("Enter right child for node: " + node.data);
            int rightData = sc.nextInt();
            if (rightData != -1) {
                BinaryTreeNode<Integer> rightNode = new BinaryTreeNode<>(rightData);
                node.right = rightNode;
                pendingQueue.add(rightNode);
            }
        }

        return root;
    }

    public static void printLevelWise(BinaryTreeNode<Integer> root) {
        Queue<BinaryTreeNode<Integer>> pendingQueue = new LinkedList<>();
        pendingQueue.add(root);

        while(!pendingQueue.isEmpty()) {
            BinaryTreeNode<Integer> node = pendingQueue.remove();
            String s = node.data + ":";
            if (node.left != null) {
                s += "L:" + node.left.data + ",";
                pendingQueue.add(node.left);
            } else {
                s += "L:-1,";
            }
            if (node.right != null) {
                s += "R:" + node.right.data;
                pendingQueue.add(node.right);
            } else {
                s += "R:-1";
            }
            System.out.println(s);
        }
    }

    public static int size(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        return size(root.right) + size(root.left) + 1;
    }

    public class Pair<T, V> {
        T diameter;
        V height;

        @Override
        public String toString() {
            return "Pair{" +
                    "diameter=" + diameter +
                    ", height=" + height +
                    '}';
        }
    }

    public Pair<Integer, Integer> diameterPair(BinaryTreeNode<Integer> root) {
        if (root == null) {
            Pair<Integer, Integer> output = new Pair<>();
            output.diameter = 0;
            output.height = 0;
            return output;
        }
        Pair<Integer, Integer> leftAns = diameterPair(root.left);
        Pair<Integer, Integer> rightAns = diameterPair(root.right);
        Pair<Integer, Integer> output = new Pair<>();
        output.height = Math.max(leftAns.height, rightAns.height) +1;
        output.diameter = Math.max(leftAns.diameter, Math.max(rightAns.diameter, leftAns.height + rightAns.height));
        return output;
    }

}
