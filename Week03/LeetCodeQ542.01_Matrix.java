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
}
