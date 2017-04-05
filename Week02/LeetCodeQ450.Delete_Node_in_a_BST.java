/*
450. Delete Node in a BST
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).
Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
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
    
    //recursuon
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            //need to find the replace node from the right subtree
            TreeNode newNode = findReplaceNode(root.right);
            root.val = newNode.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }
    
    private TreeNode findReplaceNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    
    /***************************************************************************/
    //iteration - using the replacement of the most left node of right subtree
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        TreeNode parent = findParentNode(dummy, key);
        TreeNode node = null;
        if (parent == null) return dummy.left;
        else if (parent.left != null && parent.left.val == key) {
            node = parent.left;
        } else if (parent.right != null && parent.right.val == key) {
            node = parent.right;
        } else {
            return dummy.left;
        }
        deleteNode(parent, node);
        return dummy.left;
    }
    
    private TreeNode findParentNode(TreeNode parent, int key) {
        TreeNode current = parent.left;
        while (current != null && current.val != key) {
            parent = current;
            if (current.val < key) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        if (current != null && current.val == key) return parent;
        else return null;
    }
    
    private void deleteNode(TreeNode parent, TreeNode node) {
        if (node.right == null) {
            if (parent.right == node) {
                parent.right = node.left;
            } else {
                parent.left = node.left;
            }
        } else {
            TreeNode current = node.right;
            TreeNode father = node;
            while (current.left != null) {
                father = current;
                current = current.left;
            }
            
            if (father.left == current) {
                father.left = current.right;
            } else {
                father.right = current.right;
            }
            
        
            if (parent.left == node) {
                parent.left = current;
            } else {
                parent.right = current;
            }
            current.left = node.left;
            current.right = node.right;
        }
    }
    
    
    /**********************************************************/
    //iteration - using the replacement of the most right node of left subtree
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        TreeNode dummy = new TreeNode(-1);
        dummy.right = root;
        TreeNode parent = findParent(dummy, key);
        TreeNode node = null;
        if (parent == null) return dummy.right;
        if (parent.left != null && parent.left.val == key) {
            node = parent.left;
        } else if (parent.right != null && parent.right.val == key) {
            node = parent.right;
        } else {
            return dummy.right;
        }
        deleteNode(parent, node);
        return dummy.right;
    }
    
    private TreeNode findParent(TreeNode parent, int key) {
        TreeNode current = parent.right;
        while (current != null && current.val != key) {
            parent = current;
            if (current.val < key) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        if (current != null && current.val == key) return parent;
        else return null;
    }
    
    private void deleteNode(TreeNode parent, TreeNode node) {
        if (node.left == null) {
            if (parent.left == node) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
        } else {
            TreeNode father = node;
            TreeNode newNode = node.left;
            while (newNode.right != null) {
                father = newNode;
                newNode = newNode.right;
            }
            
            if (father.left == newNode) {
                father.left = newNode.left;
            } else {
                father.right = newNode.left;
            }
            
            if (parent.left == node) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            newNode.left = node.left;
            newNode.right = node.right;
        }
    }
}

