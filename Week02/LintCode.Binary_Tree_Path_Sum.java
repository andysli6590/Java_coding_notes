/*
Binary Tree Path Sum
Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.

A valid path is from root node to any of the leaf nodes.
Example
Given a binary tree, and target = 5:

     1
    / \
   2   4
  / \
 2   3
return

[
  [1, 2, 2],
  [1, 4]
]
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
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // Write your code here
        List<List<Integer>> result = new ArrayList<>();
        binaryTreePathSumHelper(root, target, result, new ArrayList<Integer>());
        return result;
    }
    
    private void binaryTreePathSumHelper(TreeNode root, int target, List<List<Integer>> result, List<Integer> path) {
        if (root == null) return;
        target -= root.val;
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (target == 0) {
                result.add(new ArrayList<>(path));
            }
        }
        binaryTreePathSumHelper(root.left, target, result, path);
        binaryTreePathSumHelper(root.right, target, result, path);
        target += root.val;
        path.remove(path.size() - 1);
    }
}
