/*
110. Balanced Binary Tree
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of 
the two subtrees of every node never differ by more than 1.
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
   
   class ResultType {
        int height;
        boolean isBalanced;
        ResultType(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }
    
    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root).isBalanced;
    }
    
    private ResultType isBalancedHelper(TreeNode root) {
        if (root == null) return new ResultType(0, true);
        ResultType left = isBalancedHelper(root.left);
        ResultType right = isBalancedHelper(root.right);
        if (!left.isBalanced || !right.isBalanced) return new ResultType(Integer.MAX_VALUE, false);
        if (Math.abs(left.height - right.height) > 1) return new ResultType(Integer.MAX_VALUE, false);
        return new ResultType(Math.max(left.height, right.height) + 1, true);
    }
   
   
   //optimaized recursion solution, using -1 to indicate the tree is not balanced, and other value to indicate the tree height    
   public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root) != -1;
    }
    
    private int isBalancedHelper(TreeNode root) {
        if (root == null) return 0;
        int left = isBalancedHelper(root.left);
        int right = isBalancedHelper(root.right);
        if (left == -1 || right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }
}
