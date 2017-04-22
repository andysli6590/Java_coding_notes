/*
79. Word Search
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

public class Solution {
    //dfs search, 这个题目不能用dp解法因为搜索顺序是没有规则的，所以在iteration填表的时候无法实现！
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        char[] str = word.toCharArray();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (helper(board, str, i, j, 0)) { //if true, return true directly
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean helper(char[][] board, char[] str, int i, int j, int pos) {
        //corner/base case
        if (pos == str.length) {
            return true;
        }
        
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != str[pos]) {
            return false;
        }
        
        board[i][j] ^= 256; //mark visited, set to special char
        //char current = board[i][j];
        //board[i][j] = '$';
        boolean res = helper(board, str, i - 1, j, pos + 1)
                   || helper(board, str, i + 1, j, pos + 1)
                   || helper(board, str, i, j + 1, pos + 1)
                   || helper(board, str, i, j - 1, pos + 1);
        board[i][j] ^= 256;
        //board[i][j] = current;
        return res;
    }
}
