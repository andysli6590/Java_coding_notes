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
    
}
