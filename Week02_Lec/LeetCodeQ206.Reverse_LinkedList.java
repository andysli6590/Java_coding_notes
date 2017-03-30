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
    
    private ListNode reverseList_tail_recursion(ListNode head, ListNode newHead) {
        if (head == null) return newHead;
        ListNode temp = head.next;
        head.next = newHead;
        return reverseList_tail_recursion(temp, head);
    }
}
