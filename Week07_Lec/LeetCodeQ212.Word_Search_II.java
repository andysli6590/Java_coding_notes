/*
212. Word Search II
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

*/

public class Solution {
    class TrieNode {
        String word;
        TrieNode[] children = new TrieNode[26];
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
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0) {
            return result;
        }
        TrieNode root = buildTree(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                helper(board, i, j, root, result);
            }
        }
        return result;
    }
    
    private void helper(char[][] board, int i, int j, TrieNode node, List<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        char cur = board[i][j];
        if (cur > 256 || node.children[cur - 'a'] == null) {
            return;
        }
        node = node.children[cur - 'a'];
        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }
        
        board[i][j] ^= 256;
        helper(board, i + 1, j, node, result);
        helper(board, i, j + 1, node, result);
        helper(board, i - 1, j, node, result);
        helper(board, i, j - 1, node, result);
        board[i][j] ^= 256;
    }
}
