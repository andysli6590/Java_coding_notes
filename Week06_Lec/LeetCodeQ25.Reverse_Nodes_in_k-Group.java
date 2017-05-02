/*
25. Reverse Nodes in k-Group
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. 
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
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
    //iteration implementation
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        int count = k;
        while (head != null) {
            //reverse each k 
            count--;
            if (count == 0) {
                pre = reverse(pre, head.next);
                head = pre.next;
                count = k;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }
    
    private ListNode reverse(ListNode preHead, ListNode nextHead) {
        ListNode tail = preHead.next;
        ListNode current = tail.next;
        while (current != nextHead) {
            ListNode nextNode = current.next;
            current.next = preHead.next;
            preHead.next = current;
            current = nextNode;
        }
        tail.next = nextHead;
        return tail;
    }
    
    /************************************************************************************/
    //recursive implementation
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) return head;
        ListNode last = head;
        for (int i = 0; i < k - 1; i++) {
            last = last.next;
            if (last == null) return head;
        }
        
        ListNode nextHead = last.next;
        last.next = null; //break down
        reverseList(head); //reverse the part with length k
        head.next = reverseKGroup(nextHead, k);
        return last;
    }
    
    private void reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }
    }
}
