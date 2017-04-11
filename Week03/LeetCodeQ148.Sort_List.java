/*
148. Sort List
Sort a linked list in O(n log n) time using constant space complexity.
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
    
    //merge sort, time complexity: O(nlogn), space complexity: O(1) + O(logn) system stack frame
    //先局部有序，在整体有序
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode mid = findMiddle(head);
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);
        return merge(left, right);
    }
    
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        if (head1 != null) {
            tail.next = head1;
        } else {
            tail.next = head2;
        }
        return dummy.next;
    }
    
    /***********************************************************/
    //Quick Sort: time complexity: O(nlogn), space complexity: O(1) + O(logn) system stack frame
    //先整体有序，在局部有序
        public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode mid = findMiddle(head); 
        ListNode leftDummy = new ListNode(-1);
        ListNode leftTail = leftDummy;
        ListNode rightDummy = new ListNode(-1);
        ListNode rightTail = rightDummy;
        ListNode middleDummy = new ListNode(-1);
        ListNode middleTail = middleDummy;
        while (head != null) {
            if (head.val < mid.val) {
                leftTail.next = head;
                leftTail = head;
            } else if (head.val > mid.val) {
                rightTail.next = head;
                rightTail = head;
            } else {
                middleTail.next = head;
                middleTail = head;
            }
            head = head.next;
        }
        leftTail.next = null;
        rightTail.next = null;
        middleTail.next = null;
        ListNode left = sortList(leftDummy.next);
        ListNode right = sortList(rightDummy.next);
        return concat(left, middleDummy.next, right);
    }
    
    private ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    private ListNode concat(ListNode left, ListNode middle, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        tail.next = left; tail = getTail(tail);
        tail.next = middle; tail = getTail(tail);
        tail.next = right;
        return dummy.next;
    }
    
    private ListNode getTail(ListNode head) {
        if (head == null || head.next == null) return head;
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }
}
