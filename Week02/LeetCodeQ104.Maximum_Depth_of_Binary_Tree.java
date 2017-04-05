/*
104. Maximum Depth of Binary Tree
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
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
    private int maxDepth = 0;
    public int maxDepth(TreeNode root) {
        int[] counter = new int[1];
        maxDepthHelper(root, counter);
        return maxDepth;
    }
    
    private void maxDepthHelper(TreeNode root, int[] counter) {
        if (root == null) return;
        counter[0]++;
        if (root.left == null && root.right == null) {
            maxDepth = Math.max(maxDepth, counter[0]);
        }
        maxDepthHelper(root.left, counter);
        maxDepthHelper(root.right, counter);
        counter[0]--;
    }
}
