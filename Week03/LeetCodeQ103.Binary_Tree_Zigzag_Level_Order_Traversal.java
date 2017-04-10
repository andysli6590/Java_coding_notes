/*
103. Binary Tree Zigzag Level Order Traversal
Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> queue = new ArrayDeque<>();
        boolean flag = true;
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode current = null;
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                current = queue.pollFirst();
                if (flag) {
                    level.add(current.val);
                } else {
                    level.add(0, current.val);
                }
                if (current.left != null) queue.offerLast(current.left);
                if (current.right != null) queue.offerLast(current.right);
            }
            result.add(level);
            flag = !flag;
        }
        return result;
    }
}
