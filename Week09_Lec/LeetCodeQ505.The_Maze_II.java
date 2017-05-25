/*
505. The Maze II
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, 
down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. 
The distance is defined by the number of empty spaces traveled by the ball from the start 
position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. 
You may assume that the borders of the maze are all walls. 
The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1
Explanation: There is no way for the ball to stop at the destination.

Note:
. There is only one ball and one destination in the maze.
. Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
. The given maze does not contain border (like the red rectangle in the example pictures), 
    but you could assume the border of the maze are all walls.
. The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
*/

public class Solution {
    class Point {
        int x;
        int y;
        int len;
        public Point(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }
    private int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return -1;
        }
        int m = maze.length;
        int n = maze[0].length;
        int[][] dis = new int[m][n];
        Deque<Point> queue = new ArrayDeque<>();
        queue.offerLast(new Point(start[0], start[1], 0));
        
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        } 
        
        while (!queue.isEmpty()) {
            Point cur = queue.pollFirst();
            for (int[] d : dirs) {
                int nextX = cur.x;
                int nextY = cur.y;
                int len = cur.len;
                while (isValid(nextX, nextY, m, n, maze)) {
                    //Move until boundary
                    nextX += d[0];
                    nextY += d[1];
                    len++;
                }
                //calculate the position attached with wall/block
                nextX -= d[0];
                nextY -= d[1];
                len--;
                
                if (len < dis[nextX][nextY]) {
                    dis[nextX][nextY] = len;
                    queue.offerLast(new Point(nextX, nextY, len));
                }
            }
        }
        return dis[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dis[destination[0]][destination[1]];
    }
    
    private boolean isValid(int x, int y, int m, int n, int[][] maze) {
        return x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0;
    }
}


