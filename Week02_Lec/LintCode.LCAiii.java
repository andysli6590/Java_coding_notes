/*
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
 The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
 Return null if LCA does not exist.
Notice: node A or node B may not exist in tree.

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
     * @param root The root of the binary tree.
     * @param A and B two nodes
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        int[] counter = {0};
        TreeNode LCA = lowestCommonAncestor3Helper(root, A, B, counter);
        if (A == B) return counter[0] == 1 ? LCA : null; 
        return counter[0] == 2 ? LCA : null;
    }
    
    private TreeNode lowestCommonAncestor3Helper(TreeNode root, TreeNode A, TreeNode B, int[] counter) {
        if (root == null) return root;
        TreeNode left = lowestCommonAncestor3Helper(root.left, A, B, counter);
        TreeNode right = lowestCommonAncestor3Helper(root.right, A, B, counter);
        if (root == A || root == B) {
            counter[0]++;
            return root;
        }
        
        if (left != null && right != null) return root;
        return left == null ? right : left;
    }
}
