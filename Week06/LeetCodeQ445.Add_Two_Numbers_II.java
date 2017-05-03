/*
445. Add Two Numbers II
You are given two non-empty linked lists representing two non-negative integers. 
The most significant digit comes first and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
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
       //using two stacks
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 != null && l2 == null) {
            return l1;
        } else if (l1 == null && l2 != null) {
            return l2;
        }
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode current1 = l1, current2 = l2;
        while (current1 != null) {
            stack1.push(current1.val);
            current1 = current1.next;
        }
        
        while (current2 != null) {
            stack2.push(current2.val);
            current2 = current2.next;
        }
        
        int carry = 0, digit, sum;
        ListNode head = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            sum = stack1.pop() + stack2.pop() + carry;
            digit = sum % 10;
            carry = sum / 10;
            ListNode node = new ListNode(digit);
            if (head == null) {
                head = node;
            } else {
                node.next = head;
                head = node;
            }
        }
        while (!stack1.isEmpty()) {
            digit = stack1.pop() + carry;
            carry = digit / 10;
            digit %= 10;
            ListNode node = new ListNode(digit);
            node.next = head;
            head = node;
        }
        while (!stack2.isEmpty()) {
            digit = stack2.pop() + carry;
            carry = digit / 10;
            digit %= 10;
            ListNode node = new ListNode(digit);
            node.next = head;
            head = node;
        }
        if (carry != 0) {
            ListNode node = new ListNode(carry);
            node.next = head;
            head = node;
        }
        return head;
    }
    
    /***************************************************************/
    //optimized solution using two stacks
}
