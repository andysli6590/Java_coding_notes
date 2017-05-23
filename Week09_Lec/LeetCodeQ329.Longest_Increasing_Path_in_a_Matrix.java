/*
329. Longest Increasing Path in a Matrix
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. 
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/

public class Solution {
    private int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] maxPath = new int[row][col];
        
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                max = Math.max(max, helper(i, j, maxPath, matrix, row, col));
            }
        }
        return max;
    }
    
    private int helper(int x, int y, int[][] maxPath, int[][] matrix, int row, int col) {
        if (maxPath[x][y] != 0) {
            return maxPath[x][y];
        }
        int max = 1;
        for (int[] dir : direction) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            if (isValid(nextX, nextY, row, col) && matrix[nextX][nextY] > matrix[x][y]) {
                max = Math.max(max, helper(nextX, nextY, maxPath, matrix, row, col) + 1);
            }
        }
        maxPath[x][y] = max;
        return max;
    }
    
    private boolean isValid(int i, int j, int row, int col) {
        return i >= 0 && i < row && j >= 0 && j < col;
    }
}
