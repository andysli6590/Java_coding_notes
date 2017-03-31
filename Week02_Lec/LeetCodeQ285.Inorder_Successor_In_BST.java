/*
285. Inorder Successor in BST
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
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
    public TreeNode inorderSuccessor_recursion(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (root.val > p.val) {
            TreeNode left = inorderSuccessor(root.left, p);
            return left == null ? root : left;
        } else {
            return inorderSuccessor(root.right, p);
        }
    }
    
    public TreeNode inorderSuccessor_iterative(TreeNode root, TreeNode p) {
        TreeNode inorderSuccessor = null;
        while (root != null) {
            if (root.val > p.val) {
                inorderSuccessor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return inorderSuccessor;
    }
}
