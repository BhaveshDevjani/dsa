package org.example.binarytrees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

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

        while (!pendingQueue.isEmpty()) {
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
        output.height = Math.max(leftAns.height, rightAns.height) + 1;
        output.diameter = Math.max(leftAns.diameter, Math.max(rightAns.diameter, leftAns.height + rightAns.height));
        return output;
    }

    public static void preOrder(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    public static void postOrder(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    public static BinaryTreeNode<Integer> buildTreePreOrderAndInorder(int[] preOrder, int[] inOrder) {
        return buildTreePreOrderAndInorder(preOrder, inOrder, 0, preOrder.length - 1, 0, inOrder.length - 1);
    }

    public static BinaryTreeNode<Integer> buildTreePreOrderAndInorder(int[] preOrder, int[] inOrder, int preOrderSi, int preOrderEi, int inOrderSi, int inOrderEi) {
        if (preOrderSi > preOrderEi || inOrderSi > inOrderEi) {
            return null;
        }
        int rootData = preOrder[preOrderSi];
        int indexOfRootDataInInorder = getInorderIndex(inOrder, rootData);
        int lengthOfLeftSubtree = indexOfRootDataInInorder - inOrderSi;
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(rootData);
        node.left = buildTreePreOrderAndInorder(preOrder, inOrder, preOrderSi + 1, preOrderSi + lengthOfLeftSubtree, inOrderSi, indexOfRootDataInInorder - 1);
        node.right = buildTreePreOrderAndInorder(preOrder, inOrder, preOrderSi + lengthOfLeftSubtree +1, preOrderEi, indexOfRootDataInInorder + 1, inOrderEi);

        return node;


    }

    private static int getInorderIndex(int[] inOrder, int rootData) {
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootData) {
                return i;
            }
        }
        return -1;
    }

    public static BinaryTreeNode<Integer> buildTreePostOrderAndInorder(int[] postOrder, int[] inOrder) {
        return buildTreePostOrderAndInorder(postOrder, inOrder, 0, postOrder.length - 1, 0, inOrder.length - 1);
    }

    public static BinaryTreeNode<Integer> buildTreePostOrderAndInorder(int[] postOrder, int[] inOrder, int postOrderSi, int postOrderEi, int inOrderSi, int inOrderEi) {
        if (postOrderSi > postOrderEi || inOrderSi > inOrderEi) {
            return null;
        }
        int rootData = postOrder[postOrderEi];
        int indexOfRootDataInInorder = getInorderIndex(inOrder, rootData);
        int lengthOfLeftSubtree = indexOfRootDataInInorder - inOrderSi;
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(rootData);
        node.left = buildTreePostOrderAndInorder(postOrder, inOrder, postOrderSi, postOrderSi + lengthOfLeftSubtree -1, inOrderSi, indexOfRootDataInInorder - 1);
        node.right = buildTreePostOrderAndInorder(postOrder, inOrder, postOrderSi + lengthOfLeftSubtree, postOrderEi -1, indexOfRootDataInInorder + 1, inOrderEi);

        return node;


    }

    public static void printZigZag(BinaryTreeNode<Integer> root){

        Queue<BinaryTreeNode<Integer>> pendingQueue = new LinkedList<>();
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        boolean isLeftToRight = true;
        pendingQueue.add(root);

        while (!pendingQueue.isEmpty()) {
            int levelSize = pendingQueue.size();
            String s ="";
            if (isLeftToRight) {
                for (int i=0; i<levelSize; i++) {
                    BinaryTreeNode<Integer> node = pendingQueue.remove();
                    s += node.data + " ";
                    if (node.left != null) {
                        pendingQueue.add(node.left);
                        stack.push(node.left);
                    }
                    if (node.right != null) {
                        pendingQueue.add(node.right);
                        stack.push(node.right);
                    }
                }
                isLeftToRight = false;
            } else {
                while(!stack.isEmpty()) {
                    s += stack.pop().data + " ";
                    BinaryTreeNode<Integer> node = pendingQueue.remove();
                    if (node.left != null) {
                        pendingQueue.add(node.left);
                    }
                    if (node.right != null) {
                        pendingQueue.add(node.right);
                    }
                }
                isLeftToRight = true;
            }
            System.out.println(s);
        }
    }
}
