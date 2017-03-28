/*
LeetCode 19. Remove Nth Node From End of List
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.
	After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy;
        head = dummy;
        
        //create the moving window
        for (int i = 0; i < n; i++) {
            node = node.next;
            if (node == null) return dummy.next; //n is not valid index
        }
        
        while (node.next != null) { //find the pre-nth node from the end
            head = head.next;
            node = node.next;
        }
        head.next = head.next.next;
        return dummy.next;
    }
}