/*
230. Kth Smallest Element in a BST
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
How would you optimize the kthSmallest routine?
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int[] answer = new int[2];
        answer[0] = k;
        answer[1] = -1;
        helper(root, answer);
        return answer[1];
    }
    
    private void helper(TreeNode root, int[] answer) {
        if (root == null) return;
        helper(root.left, answer);
        answer[0]--;
        if (answer[0] == 0) {
            answer[1] = root.val;
        }
        helper(root.right, answer);
    }
}
