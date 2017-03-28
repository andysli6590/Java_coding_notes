package com.SnakGame.Week01;

/**
 * Created by Yiteng on 3/28/2017.
 */
public class Question2 {
    static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    //find middle in LinkedList
    public static ListNode findMiddleNode(ListNode head) {
        if (head == null || head.next == null) return head;
        //fast-slow two pointer
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
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
        System.out.print("Given Linked List: ");
        ListNode middle = findMiddleNode(node1);
        System.out.println(String.format("The middle point value of this linked list is %d", middle.val));
    }
}
