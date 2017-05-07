/*
99. Recover Binary Search Tree
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
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
    //in order traverse, recursion
    private TreeNode lastMin = new TreeNode(Integer.MIN_VALUE); //last min value
    private TreeNode first = null; //first elem to swap
    private TreeNode second = null; //second elem to swap
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        
        inOrderTraverse(root);
        
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    private void inOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.left);
        if (root.val < lastMin.val) {
            if (first == null) {
                first = lastMin;
            }
            second = root;
        }
        lastMin = root;
        inOrderTraverse(root.right);
    }
}
