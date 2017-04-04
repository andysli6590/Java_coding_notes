/*
230. Kth Smallest Element in a BST
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
How would you optimize the kthSmallest routine?
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
    public int kthSmallest(TreeNode root, int k) {
        int[] answer = new int[2];
        answer[0] = k;
        answer[1] = -1;
        helper(root, answer);
        return answer[1];
    }
    
    private void helper(TreeNode root, int[] answer) {
        if (root == null) return;
        helper(root.left, answer);
        answer[0]--;
        if (answer[0] == 0) {
            answer[1] = root.val;
        }
        helper(root.right, answer);
    }
    
    //==============================================================================================
    //if we can modify the tree structure, and add  count attribute
    public class TreeNodeWithCount {
        int val;
        int lCount;
        TreeNodeWithCount left;
        TreeNodeWithCount right;
        TreeNodeWithCount(int val){
            this.val = val;
            this.left = this.right = null;
        }
    }
    
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return -1;
        TreeNodeWithCount rootWithCount = createBSTWithCount(root);
        return kthSmallestWithCount(rootWithCount, k);
    }
    
    private TreeNodeWithCount createBSTWithCount(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNodeWithCount rootWithCount = null;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNodeWithCount nodeWithCount = new TreeNodeWithCount(node.val);
            rootWithCount = insertBSTWithCount(rootWithCount, nodeWithCount);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return rootWithCount;
    }
    
    private TreeNodeWithCount insertBSTWithCount(TreeNodeWithCount rootWithCount, TreeNodeWithCount nodeWithCount) {
        TreeNodeWithCount cur = rootWithCount, parent = rootWithCount;
        while (cur != null) {
            parent = cur;
            if (nodeWithCount.val < cur.val) {
                cur.lCount++;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (rootWithCount == null) {
            rootWithCount = nodeWithCount;
        } else if (parent.val < nodeWithCount.val) {
            parent.right = nodeWithCount;
        } else {
            parent.left = nodeWithCount;
        }
        return rootWithCount;
    }
    
    private int kthSmallestWithCount(TreeNodeWithCount rootWithCount, int k) {
        while (rootWithCount != null) {
            if (k == rootWithCount.lCount + 1) return rootWithCount.val;
            else if (k <= rootWithCount.lCount) {
                rootWithCount = rootWithCount.left;
            } else {
                k = k - rootWithCount.lCount - 1;
                rootWithCount = rootWithCount.right;
            }
        }
        return -1;
    }
}
