package com.SnakGame.Week01;
import java.util.*;
/**
 * Created by Yiteng on 3/28/2017.
 */

public class Solution {

    private static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    //insert in a sorted linkedlist
    public static ListNode insertNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.val <= val) {
            head = head.next;
        }
        ListNode node = new ListNode(val);
        node.next = head.next;
        head.next = node;
        return dummy.next;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ", ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        System.out.print("Given Sorted List: ");
        printList(node1);
        int val1 = 3;
        System.out.print("Insert node with value 3: ");
        printList(insertNode(node1, val1));
        int val2 = 6;
        System.out.print("Insert node with value 6: ");
        printList(insertNode(node1, val2));
    }
}


/*
Run solution test result:
    Given Sorted List: 1, 2, 5,
    Insert node with value 3: 1, 2, 3, 5,
    Insert node with value 6: 1, 2, 3, 5, 6,
 */