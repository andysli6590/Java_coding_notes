/*
542. 01 Matrix
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1: 
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2: 
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.
*/

public class Solution {
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        //BFS
        if (matrix == null || matrix.size() == 0 || matrix.get(0).size() == 0) return matrix;
        int m = matrix.size();
        int n = matrix.get(0).size();
        // Deque<int[]> queue = new ArrayDeque<>();  用Deque做queue会超时！！！！！
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) == 0) {
                     queue.offer(new int[] {i, j}); 
                } else {
                    matrix.get(i).set(j, Integer.MAX_VALUE);
                }
            }
        }
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int[] coordinate = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = coordinate[0] + dx[i];
                int y = coordinate[1] + dy[i];
                if (x < 0 || x >= m || y < 0 || y >= n ||
                    matrix.get(coordinate[0]).get(coordinate[1]) + 1 >= matrix.get(x).get(y)) continue;
                matrix.get(x).set(y, matrix.get(coordinate[0]).get(coordinate[1]) + 1);
                queue.offer(new int[] {x, y});
            }
        }
        return matrix;
    }
    
    
    /********************************************************************/
    //DFS solution
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        if (matrix == null || matrix.size() == 0 || matrix.get(0).size() == 0) return matrix;
        int m = matrix.size();
        int n = matrix.get(0).size();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) == 1) matrix.get(i).set(j, m + n - 2);
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) != 0) continue;
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    if (matrix.get(x).get(y) != 0) matrix.get(x).set(y, 1); //set the non-zero value next to "0" to 1
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfsHelper(matrix, m, n, i, j);
            }
        }
        return matrix;
    }
    private void dfsHelper(List<List<Integer>> matrix, int m, int n, int i, int j) {
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x < 0 || x >= m || y < 0 || y >= n) continue;
            if (matrix.get(x).get(y) > matrix.get(i).get(j) + 1) {
                matrix.get(x).set(y, matrix.get(i).get(j) + 1);
                dfsHelper(matrix, m, n, x, y);
            }
        }
    }
    
    /************************************************************************/
    //most optimized dfs solution
    private int row;
    private int col;
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        if (matrix == null || matrix.size() == 0 || matrix.get(0).size() == 0) return matrix;
        row = matrix.size();
        col = matrix.get(0).size();
        preSet(matrix);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix.get(i).get(j) == 1) {
                    dfsHelper(matrix, 1, i, j);
                }
            }
        }
        return matrix;
    }
    
    private void preSet(List<List<Integer>> matrix) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix.get(i).get(j) == 0) continue;
                boolean flag = false;
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x < 0 || x >= row || y < 0 || y >= col) continue;
                    flag |= matrix.get(x).get(y) == 0;
                }
                if (!flag) matrix.get(i).set(j, row + col - 2);
            }
        }
    }
    
    private void dfsHelper(List<List<Integer>> matrix, int dist, int x, int y) {
        if (matrix.get(x).get(y) < dist) return; // corner/base case(遇0和已优化路径返回)
        if (matrix.get(x).get(y) == dist && matrix.get(x).get(y) != 1) return;
        
        //current layer set dist
        matrix.get(x).set(y, dist);
        
        for (int i = 0; i < 4; i++) {
            int xNext = x + dx[i];
            int yNext = y + dy[i];
            if (xNext < 0 || xNext >= row || yNext < 0 || yNext >= col) continue;
            dfsHelper(matrix, dist + 1, xNext, yNext);
        }
    }
}
