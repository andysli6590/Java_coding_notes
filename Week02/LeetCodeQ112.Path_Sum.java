/*
112. Path Sum
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path 
equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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
    //recursion
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) 
            return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);    
    }
  
    //iteration, approach 1, post order traverse
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        Deque<TreeNode> stack = new ArrayDeque<>();
        int result = 0;
        stack.offerLast(root);
        result += root.val;
        TreeNode prev = root, current = null;
        while (!stack.isEmpty()) {
            current = stack.peekLast();
            if (current.left != null && prev != current.left && prev != current.right) {
                stack.offerLast(current.left);
                result += current.left.val;
            } else if (current.right != null && prev != current.right) {
                stack.offerLast(current.right);
                result += current.right.val;
            } else {
                prev = current;
                if (current.left == null && current.right == null && result == sum) return true;
                result -= stack.pollLast().val;
            }
        }
        return false;
    }
  
    
    
}
