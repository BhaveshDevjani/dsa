package org.example.binarysearchtrees;

import org.example.binarytrees.BinaryTreeNode;

public class BinarySearchTree {
    private BinaryTreeNode<Integer> root;

    /**
     * Time Complexity: O(h)
     */
    public void insertData(int data) {
        this.root = insertData(data, root);
    }

    private BinaryTreeNode<Integer> insertData(int data, BinaryTreeNode<Integer> root) {
        if (root == null) {
            BinaryTreeNode<Integer> node = new BinaryTreeNode<>(data);
            this.root = node;
            return node;
        }
        if (root.data.compareTo(data) < 0) {
            root.right = insertData(data, root.right);
        } else {
            root.left = insertData(data, root.left);
        }

        return root;
    }

    /**
     * Time Complexity: O(h)
     * Note: getMinimum will only be called once when the data has been found.
     */
    public void deleteData(int data) {
        this.root = deleteData(data, root);
    }

    private BinaryTreeNode<Integer> deleteData(int data, BinaryTreeNode<Integer> root) {
        if (root == null || (root.left == null && root.right == null && root.data.equals(data))) {
            return null;
        }
        if (root.data.compareTo(data) < 0) {
            root.right = deleteData(data, root.right);
            return root;
        } else if (root.data.compareTo(data) > 0) {
            root.left = deleteData(data, root.left);
            return root;
        } else {
            if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else {
                int minimum = getMinimum(root.right);
                BinaryTreeNode<Integer> node = new BinaryTreeNode<>(minimum);
                node.left = root.left;
                node.right = deleteData(minimum, root.right);
                return node;

            }
        }
    }

    private int getMinimum(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null) {
            return root.data;
        } else {
            return getMinimum(root.left);
        }
    }

    /**
     * Time Complexity: O(h)
     */
    public boolean hasData(int data) {
        return hasData(data, root);
    }

    private boolean hasData(int data, BinaryTreeNode<Integer> root) {
        if (root == null) return false;

        if (root.data.equals(data)) return true;

        if (root.data.compareTo(data) < 0) {
            return hasData(data, root.right);
        } else {
            return hasData(data, root.left);
        }

    }

    public void printTree() {
        printTree(root);
    }

    /**
     * Time Complexity: O(n)
     */
    private void printTree(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        String s = root.data + ":";
        if (root.left != null) {
            s += "L:" + root.left.data + ",";
        }
        if (root.right != null) {
            s += "R:" + root.right.data;
        }
        System.out.println(s);
        printTree(root.left);
        printTree(root.right);
    }
}
