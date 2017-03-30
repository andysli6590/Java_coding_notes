/*
206. Reverse Linked List
	Reverse a singly linked list.
*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        //if (head == null || head.next == null) return head;
        return reverseList_tail_recursion(head, null);
    }
    // tail-recursion
    private ListNode reverseList_tail_recursion(ListNode head, ListNode newHead) {
        if (head == null) return newHead;
        ListNode temp = head.next;
        head.next = newHead;
        return reverseList_tail_recursion(temp, head);
    }
	
	
    //non-tail-recursion
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
    
    //iterative
    public ListNode reverse(ListNode head) {
        ListNode prevNode = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = prevNode;
            prevNode = head;
            head = nextNode;
        }
        return prevNode;
    }
}
