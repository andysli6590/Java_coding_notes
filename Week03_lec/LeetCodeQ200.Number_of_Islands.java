/*
200. Number of Islands
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1
*/

public class Solution {
    //dfs 染色 implementation
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {  //遍历二维矩阵
                if (grid[i][j] == '0') continue;  //控制条件
                helper(grid, i, j); //染色并计数
                count++;  //只有进出helper function的时候才increment,说明是独立的岛，没有联通了
            }
        }
        return count;
    }
    
    private void helper(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0') return;
        grid[x][y] = '0';     //染色
        helper(grid, x + 1, y);
        helper(grid, x - 1, y);
        helper(grid, x, y + 1);
        helper(grid, x, y - 1);
    }
    
    /*****************************************************************/
        //dfs，不用染色的话需要一个额外数组记录访路径
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0' || visited[i][j]) continue;
                helper(grid, i, j, visited);
                count++;
            }
        }
        return count;
    }
    
    private void helper(char[][] grid, int x, int y, boolean[][] visited) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0' || visited[x][y]) return;
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) { //four direction search
            helper(grid, x + dx[i], y + dy[i], visited);
        }
    }
}
