package org.example.linkedlist;

import java.util.Scanner;

public class LinkedList {

    /**
     Time Complexity: O(n)
     Space Complexity: O(1)
     */
    public static LinkedListNode<Integer> takeInput() {
        LinkedListNode<Integer> head = null;
        LinkedListNode<Integer> tail = null;
        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();

        while(data != -1) {
            LinkedListNode<Integer> linkedListNode = new LinkedListNode<>(data);
            if (head == null) {
                head= linkedListNode;
                tail= linkedListNode;
            } else {
                tail.next = linkedListNode;
                tail = linkedListNode;
            }
            data = sc.nextInt();
        }
        return head;
    }

    /**
     Time Complexity: O(n)
     Space Complexity: O(1)
     */
    public static <T> LinkedListNode<T> insertNode(LinkedListNode<T> head, T data, int position) {
        LinkedListNode<T> newLinkedListNode = new LinkedListNode<>(data);
        int i=0;
        LinkedListNode<T> temp = head;
        if (position == 0){
            newLinkedListNode.next = head;
            return newLinkedListNode;
        }
        while (i<position-1) {
            temp = temp.next;
            i++;
        }
        newLinkedListNode.next = temp.next;
        temp.next = newLinkedListNode;
        return head;
    }

    /**
     Time Complexity: O(n)
     Space Complexity: O(n)
     */
    public static <T> LinkedListNode<T> insertNodeRecursively(LinkedListNode<T> head, T data, int position) {
        return insertNodeRecursivelyIndexed(head, data, position, 0);
    }

    private static <T> LinkedListNode<T> insertNodeRecursivelyIndexed(LinkedListNode<T> head, T data, int position, int startIndex) {
        if (head == null) {
            return head;
        }
        if(position == startIndex){
            LinkedListNode<T> newNode = new LinkedListNode<>(data);
            newNode.next = head;
            return newNode;
        }
        LinkedListNode<T> head2 = insertNodeRecursivelyIndexed(head.next, data, position, startIndex+1);
        head.next = head2;
        return  head;

    }


    /**
     Time Complexity: O(n)
     Space Complexity: O(1)
     */
    public static <T> void printLL(LinkedListNode<T> head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static LinkedListNode<Integer> skipMdeleteN(LinkedListNode<Integer> head, int M, int N) {
        if (head == null) {
            return head;
        }
        LinkedListNode<Integer> retainingTail = head, deletingTail = head, temp=head;
        int retainCounter =0;
        int deleteCounter =0;
        while(temp != null) {
            if (retainCounter < M-1) {
                temp = temp.next;
                retainCounter++;
                retainingTail = retainingTail.next;
                deletingTail = retainingTail;
            }
            else if (deleteCounter < N) {
                temp = temp.next;
                deleteCounter++;
                deletingTail = deletingTail.next;
            }
            // if (deleteCounter > N) {
            else {
                deletingTail = deletingTail.next;
                retainingTail.next = deletingTail;
                retainingTail = retainingTail.next;
                temp = temp.next;
                retainCounter = 0;
                deleteCounter = 0;
            }
        }
        if (retainingTail != null && deletingTail != null && retainingTail.data.equals(deletingTail.data)) {
            retainingTail.next = null;
        } else if (retainingTail != null) {
            retainingTail.next = deletingTail;
        }
        return head;
    }
}
