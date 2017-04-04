/*
Insert Node in a Binary Search Tree 
Given a binary search tree and a new tree node, insert the node into the tree. 
You should keep the tree still be a valid binary search tree.
You can assume there is no duplicate values in this tree + node.
*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // iteration
        if (root == null) return node;
        TreeNode prev = null;
        TreeNode current = root;
        while (current != null) {
            prev = current;
            if (current.val < node.val) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        
        if (prev.val < node.val) {
            prev.right = node;
        } else {
            prev.left = node;
        }
        return root;
    }
    
    
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // recursion
        if (root == null) return node;
        if (root.val > node.val) {
            root.left = insertNode(root.left, node);
        } else {
            root.right = insertNode(root.right, node);
        }
        return root;
    }
}
