package org.example.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TreeUseHelper {
    public static final Scanner sc = new Scanner(System.in);

// Sample input  1 3 2 3 4 0 2 5 6 1 7 0 0 0
    public static TreeNode<Integer> takeInput() {
        System.out.println("Enter the node data");
        int n = sc.nextInt();
        TreeNode<Integer> root = new TreeNode<>(n);
        System.out.println("Enter the number of children for node: " + n);
        int children = sc.nextInt();

        for (int i = 0; i < children; i++) {
            TreeNode<Integer> child = takeInput();
            root.children.add(child);
        }
        return root;
    }

    public static void print(TreeNode<Integer> root) {
        String s = root.data + ": ";
        for (int i = 0; i < root.children.size(); i++) {
            s = s + root.children.get(i).data + ", ";
        }
        System.out.println(s);

        for (int i = 0; i < root.children.size(); i++) {
            print(root.children.get(i));
        }
    }

    public static TreeNode<Integer> takeInputLevelWise() {
        Queue<TreeNode<Integer>> pendingQueue = new LinkedList<>();

        System.out.println("Enter root data");
        int rootData = sc.nextInt();
        TreeNode<Integer> root = new TreeNode<>(rootData);
        pendingQueue.add(root);

        while (!pendingQueue.isEmpty()) {
            TreeNode<Integer> front = pendingQueue.remove();
            System.out.println("Enter number of children for node: " + front.data);
            int n = sc.nextInt();
            for (int i = 1; i <= n; i++) {
                System.out.println("Enter child " + i + " for parent: " + front.data);
                int childData = sc.nextInt();
                TreeNode<Integer> child = new TreeNode<>(childData);
                front.children.add(child);
                pendingQueue.add(child);
            }
        }

        return root;

    }

    public static void printLevelWise(TreeNode<Integer> root) {
        Queue<TreeNode<Integer>> pendingQueue = new LinkedList<>();
        pendingQueue.add(root);

        while (!pendingQueue.isEmpty()) {
            int levelSize = pendingQueue.size();
            String s = "";
            for (int i = 0; i < levelSize; i++) {
                TreeNode<Integer> node = pendingQueue.remove();
                s = s + node.data + " ";
                for (int j = 0; j < node.children.size(); j++) {
                    pendingQueue.add(node.children.get(j));
                }
            }
            System.out.println(s);
        }
    }

    public static int size(TreeNode<Integer> root) {
        int totalCount = 0;
        for (int i = 0; i < root.children.size(); i++) {
            totalCount = totalCount + size(root.children.get(i));
        }
        return totalCount + 1;
    }

    public static int sumOfAllNode(TreeNode<Integer> root) {
        int sum = 0;
        for (int i = 0; i < root.children.size(); i++) {
            sum = sum + sumOfAllNode(root.children.get(i));
        }
        return sum + root.data;
    }

    public static TreeNode<Integer> largestDataNode(TreeNode<Integer> root) {
        ArrayList<TreeNode<Integer>> subAns = new ArrayList<>();
        subAns.add(root);

        for (int i = 0; i < root.children.size(); i++) {
            subAns.add(largestDataNode(root.children.get(i)));
        }

        int currentLargest = Integer.MIN_VALUE;
        TreeNode<Integer> currentLargestNode = null;
        for (TreeNode<Integer> node : subAns) {
            if (currentLargest < node.data) {
                currentLargest = node.data;
                currentLargestNode = node;
            }
        }
        return currentLargestNode;
    }

    public static int numNodeGreater(TreeNode<Integer> root, int x) {
        int totalCounter = 0;
        for (int i = 0; i < root.children.size(); i++) {
            totalCounter = totalCounter + numNodeGreater(root.children.get(i), x);
        }
        return root.data > x ? totalCounter + 1 : totalCounter;

    }

    public static int getHeight(TreeNode<Integer> root) {
        Queue<TreeNode<Integer>> pendingQueue = new LinkedList<>();
        pendingQueue.add(root);
        int height = 0;

        while (!pendingQueue.isEmpty()) {
            int levelSize = pendingQueue.size();
            height++;
            for (int i = 0; i < levelSize; i++) {
                TreeNode<Integer> node = pendingQueue.remove();
                for (int j = 0; j < node.children.size(); j++) {
                    pendingQueue.add(node.children.get(j));
                }
            }
        }
        return height;
    }

    public static int depthOfNode(TreeNode<Integer> root, TreeNode<Integer> x) {
        Queue<TreeNode<Integer>> pendingQueue = new LinkedList<>();
        pendingQueue.add(root);
        int height = 0;

        while (!pendingQueue.isEmpty()) {
            int levelSize = pendingQueue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode<Integer> node = pendingQueue.remove();
                if (node == x) {
                    return height;
                } else {
                    for (int j = 0; j < node.children.size(); j++) {
                        pendingQueue.add(node.children.get(j));
                    }
                }
            }
            height++;
        }

        return -1;
    }

    public static void preOrderTraversal(TreeNode<Integer> root) {
        System.out.print(root.data + " ");
        for (int i = 0; i < root.children.size(); i++) {
            preOrderTraversal(root.children.get(i));
        }
    }

    public static void postOrderTraversal(TreeNode<Integer> root) {
        for (int i = 0; i < root.children.size(); i++) {
                postOrderTraversal(root.children.get(i));
            }
            System.out.print(root.data + " ");

        }

        public static int countLeafNodes(TreeNode<Integer> root) {
            int counter = 0;
            if (root.children.size() == 0) {
                counter ++;
            }
            for (TreeNode<Integer> child : root.children)  {
                counter += countLeafNodes(child);
        }
        return counter;

    }


}
