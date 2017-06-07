/*
124. Binary Tree Maximum Path Sum
Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to 
any node in the tree along the parent-child connections. 
The path must contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
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
    //Divide and Conquer
    //singlePath: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
    //maxPath: 从树中任意点的最大路径，这条路径至少包含一个点
    class ResultType {
        //singlePath: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
        //maxPath: 从树中任意点的最大路径，这条路径至少包含一个点
        int singlePath;
        int maxPath;
        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }
    
    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxPath;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        //divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        //conquer
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);
        
        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);
        
        return new ResultType(singlePath, maxPath);
    }
       
    /**************************************************************************************************/
    //Divide and Conquer
    //singlePath也可以定义为只包含一个点   
    class ResultType {
        //singlePath也可以定义为只包含一个点
        int singlePath;
        int maxPath;
        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }
    
    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxPath;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        //Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        //Conquer
        int singlePath = Math.max(0, Math.max(left.singlePath, right.singlePath)) + root.val;
        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, Math.max(left.singlePath, 0) + Math.max(right.singlePath, 0) + root.val);
        
        return new ResultType(singlePath, maxPath);
    }
}
