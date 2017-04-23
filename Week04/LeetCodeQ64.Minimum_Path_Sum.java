/*
64. Minimum Path Sum
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes 
the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
*/

public class Solution {
    
    //dfs implementation, TLE in leetcode，在leetcode上面超时
    public int minPathSum(int[][] grid) {
        return dfsHelper(grid, 0, 0);
        //是从(0, 0) -> (grid.length - 1, grid[0].length - 1)来搜索，只能向右或者向下
    }
    
    private int dfsHelper(int[][] grid, int i, int j) {
        //corner case
        if (i == grid.length - 1 && j == grid[i].length - 1) {
            return grid[i][j];
        }
        
        //如果满足能向右走和能下下走，需要比较得到最小路径
        if (i < grid.length - 1 && j < grid[i].length - 1) {
            int down = grid[i][j] + dfsHelper(grid, i + 1, j);
            int right = grid[i][j] + dfsHelper(grid, i, j + 1);
            return Math.min(right, down);
        }
        
        //如果只能向下走
        if (i < grid.length - 1) {
            return grid[i][j] + dfsHelper(grid, i + 1, j);
        }
        //如果只能向右走
        if (j < grid[i].length - 1) {
            return grid[i][j] + dfsHelper(grid, i, j + 1);
        }
        return 0;
    }
    
    /******************************************************************************/
    //dp solution, standard induction rule to fill dp table iteratively
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        //定义数组
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        
        //初始化
        dp[0][0] = grid[0][0];
        //initialization left side
        for (int j = 1; j < m; j++) {
            dp[j][0] = grid[j][0] + dp[j - 1][0];
        }
        
        //initialization top side
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        
        //fill up the dp table iteratively
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //induction rule
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
