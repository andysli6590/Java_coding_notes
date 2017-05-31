/*
337. House Robber III
The thief has found himself a new place for his thievery again. There is only one entrance to this area, 
called the "root." Besides the root, each house has one and only one parent house. 
After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
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
 
//house robber follow up on the binary tree
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
    private class Sum {
        int inRoot;
        int notRoot;
        public Sum(int inRoot, int notRoot) {
            this.inRoot = inRoot;
            this.notRoot = notRoot;
        }
    }
    
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Sum res = helper(root);
        return Math.max(res.inRoot, res.notRoot);
    }
    
    private Sum helper(TreeNode root) {
        if (root == null) {
            return new Sum(0, 0);
        }
        if (root.left == null && root.right == null) {
            return new Sum(root.val, 0);
        }
        Sum left = helper(root.left);
        Sum right = helper(root.right);
        
        int inRoot = Math.max(root.val + left.notRoot + right.notRoot, left.inRoot + right.inRoot);
        int notRoot = left.inRoot + right.inRoot;
        return new Sum(inRoot, notRoot);
    }
}
