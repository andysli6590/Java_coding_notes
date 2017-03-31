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

//inorder successor
public class Solution {
    public TreeNode inorderSuccessor_recursion(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (root.val > p.val) {
            TreeNode left = inorderSuccessor_recursion(root.left, p);
            return left == null ? root : left;
        } else {
            return inorderSuccessor_recursion(root.right, p);
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

/*
explaination:
for successor, 
    left: is the right subtee or the root
    root: is the right subtree
    right: is the right subtee
    
 for predecessor:
    left: is the left subtree
    root: is the left subtree
    right: is the left subtree or the root
*/


//inorder predecessor
public class Solution {
    public TreeNode inorderPredecessor_recursion(TreeNode root, TreeNode p) {
        if (root == null) return root;
        if (root.val < p.val) {
            TreeNode right = inorderPredecessor_recursion(root.right, p);
            return right == null ? root : right;
        } else {
            return inorderPredecessor_recursion(root.left, p);
        }
    }
    
    public TreeNode inorderPredecessor_iterative(TreeNode root, TreeNode p) {
        if (root == null) return null;
        TreeNode inorderPredecessor = null;
        while (root != null) {
            if (root.val < p.val) {
                inorderPredecessor = root;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return inorderPredecessor;
    }
}


