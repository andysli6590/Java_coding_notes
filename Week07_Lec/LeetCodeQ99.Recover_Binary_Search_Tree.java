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
    
    /*******************************************************************************************/
    //morris inorder traverse
    private TreeNode lastMin = new TreeNode(Integer.MIN_VALUE); //last min value
    private TreeNode first = null; //first elem to swap
    private TreeNode second = null; //second elem to swap
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null) {
            if (cur.left == null) { //case 1: no left child -> print current
                checkTarget(cur);
                cur = cur.right; //go right
            } else { //case 2: has left child
                //step 1: find predecessor int left subtree
                prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                //step 2: connect predecessor and current
                if (prev.right == null) { //case 2.1: not connected -> connect
                    prev.right = cur;
                    cur = cur.left;
                } else { //case 2.2: connected -> print current and disconnect
                    prev.right = null;
                    checkTarget(cur);
                    cur = cur.right;
                }
            }
        }
        System.out.println(first.val);
        System.out.println(second.val);
        swap(first, second);
    }
    
    private void checkTarget(TreeNode cur) {
        if (cur.val < lastMin.val) {
            if (first == null) {
                first = lastMin;
            }
            second = cur;
        }
        lastMin = cur;
    }
    
    private void swap(TreeNode frist, TreeNode second) {
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
