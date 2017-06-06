/*
576. Out of Boundary Paths
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, 
you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). 
However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. 
The answer may be very large, return it after mod 109 + 7.

Note:
. Once you move the ball out of boundary, you cannot move it back.
. The length and height of the grid is in range [1,50].
. N is in range [0,50].
*/

public class Solution {
    //brutal force, TLE, time complexity: O(4^n), space complexity: O(n)
    public int findPaths(int m, int n, int N, int i, int j) {
        if (i >= m || i < 0 || j >= n || j < 0) {
            return 1;
        } //make sure the order is correct!!!!!
        if (N <= 0 || m <= 0 || n <= 0) {
            return 0;
        }
        return findPaths(m, n, N - 1, i - 1, j) + findPaths(m, n, N - 1, i + 1, j) 
             + findPaths(m, n, N - 1, i, j - 1) + findPaths(m, n, N - 1, i, j + 1);
    }
    
    /******************************************************************************************/
    //brutal force, DFS implementation, TLE, time complexity: O(4^n), space complexity: O(n)
    private int count;
    public int findPaths(int m, int n, int N, int i, int j) {
        if (N <= 0 || m <= 0 || n <= 0) {
            return 0;
        }
        count = 0;
        helper(m, n, N, i, j);
        return count;
    }
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    private void helper(int m, int n, int N, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            count++;
            return;
        }
        if (N <= 0) {
            return;
        } 
        
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            helper(m, n, N - 1, x, y);
        }
    }
    
    /**********************************************************************************************/
    //memorized search
    private final int mode = 1000000007;
    public int findPaths(int m, int n, int N, int i, int j) {
        if (N <= 0 || m <= 0 || n <= 0) {
            return 0;
        }
        int[][][] cache = new int[m][n][N + 1];
        for (int[][] elem : cache) {
            for (int[] e : elem) {
                Arrays.fill(e, -1);
            }
        }
        return helper(m, n, N, i, j, cache);
    }
    
    private int helper(int m, int n, int N, int i, int j, int[][][] cache) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 1;
        }
        if (N <= 0) {
            return 0;
        } 
        if (cache[i][j][N] >= 0) {
            return cache[i][j][N];
        }
        cache[i][j][N] = ((helper(m, n, N - 1, i + 1, j, cache) + helper(m, n, N - 1, i - 1, j, cache)) % mode + (helper(m, n, N - 1, i, j + 1, cache) + helper(m, n, N - 1, i, j - 1, cache)) % mode) % mode;
        
        return cache[i][j][N];
    }
}
