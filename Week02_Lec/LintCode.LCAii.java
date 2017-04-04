/*
Lowest Common Ancestor II 
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
The node has an extra attribute parent which point to the father of itself. The root's parent is null.
*/

/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */
public class Solution {
    /**
     * @param root: The root of the tree
     * @param A, B: Two node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
                                                 ParentTreeNode A,
                                                 ParentTreeNode B) {
        // Write your code here  
        List<ParentTreeNode> pathA = findPathToRoot(A);
        List<ParentTreeNode> pathB = findPathToRoot(B);
        int indexA = pathA.size() - 1;
        int indexB = pathB.size() - 1;
        ParentTreeNode result = null;
        while (indexA >= 0 && indexB >= 0) {
            if (pathA.get(indexA) != pathB.get(indexB)) break;
            result = pathA.get(indexA);
            indexA--;
            indexB--;
        }
        return result;
    }
    
    private List<ParentTreeNode> findPathToRoot(ParentTreeNode node) {
        List<ParentTreeNode> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        return path;
    }
}
