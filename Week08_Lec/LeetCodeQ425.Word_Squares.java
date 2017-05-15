/*
425. Word Squares
Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, 
where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the 
same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not 
matter (just the order of words in each word square matters).
Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
*/

public class Solution {
    //DFS using trie tree
    private class TrieNode {
        String word;
        TrieNode[] children;
        public TrieNode() {
            this.children = new TrieNode[26];
            this.word = null;
        }
    }
    
    private TrieNode buildTree(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new TrieNode();
                }
                cur = cur.children[index];
            }
            cur.word = word;
        }
        return root;
    }
    
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        TrieNode root = buildTree(words);
        
        TrieNode[] nodes = new TrieNode[words[0].length()];
        Arrays.fill(nodes, root);
        dfsHelper(0, 0, nodes, res);
        return res;
    }
    
    private void dfsHelper(int idx, int level, TrieNode[] nodes, List<List<String>> res) {
        if (idx == nodes.length) {
            List<String> list = new ArrayList<>();
            for (TrieNode node : nodes) {
                list.add(node.word);
            }
            res.add(list);
            return;
        }
        if (level == nodes.length) {
            dfsHelper(idx + 1, idx + 1, nodes, res);
        } else {
            TrieNode cur = nodes[idx];
            TrieNode diag = nodes[level];
            for (int i = 0; i < 26; i++) {
                if (cur.children[i] != null && diag.children[i] != null) {
                    nodes[idx] = cur.children[i];
                    nodes[level] = diag.children[i];
                    dfsHelper(idx, level + 1, nodes, res);
                }
            }
            nodes[idx] = cur;
            nodes[level] = diag;
        }
    }
}


