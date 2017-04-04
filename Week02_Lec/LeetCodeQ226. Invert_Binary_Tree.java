/*
226. Invert Binary Tree
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
     
     //iteration - BFS, using queue
     public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();
            TreeNode temp = parent.left;
            parent.left = parent.right;
            parent.right = temp;
            if (parent.left != null) queue.offer(parent.left);
            if (parent.right != null) queue.offer(parent.right);
        }
        return root;
    }
     
     
}
