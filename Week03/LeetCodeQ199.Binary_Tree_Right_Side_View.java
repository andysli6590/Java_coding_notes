/*
199. Binary Tree Right Side View
Given a binary tree, imagine yourself standing on the right side of it, 
return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
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
    //BFS, 
    //level order traverse from right to left, each time just collect the start node value of that level
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) { 
            int size = queue.size();
            TreeNode current = null;
            for (int i = 0; i < size; i++) {
                current = queue.pollFirst();
                if (i == 0) result.add(current.val);
                if (current.right != null) queue.offerLast(current.right); 
                if (current.left != null) queue.offerLast(current.left);
            }
        }
        return result;
    }
