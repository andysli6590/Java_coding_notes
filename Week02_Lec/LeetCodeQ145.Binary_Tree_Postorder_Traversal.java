/*
145. Binary Tree Postorder Traversal
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].
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
    
    //iteration, using one stack
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        TreeNode prev = root;
        TreeNode current = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            current = stack.peekLast();
            if (current.left != null && prev != current.left && prev != current.right) {
                stack.offerLast(current.left);
            } else if (current.right != null && prev != current.right) {
                stack.offerLast(current.right);
            } else {
                result.add(stack.pollLast().val);
                prev = current;
            }
        }
        return result;
    }
}
