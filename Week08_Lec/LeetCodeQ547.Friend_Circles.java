/*
547. Friend Circles
There are N students in a class. Some of them are friends, while some are not. 
Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, 
then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, 
then the ith and jth students are direct friends with each other, otherwise not. 
And you have to output the total number of friend circles among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
Note:
1. N is in range [1,200].
2. M[i][i] = 1 for all students.
3. If M[i][j] = 1, then M[j][i] = 1.
*/

public class Solution {
    //静态连通图，用dfs算联通性，这个时候的时间复杂度是和union find的方法差不多。如果是动态连通图，union find要比dfs优化很多。
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }
        boolean[] visited = new boolean[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                dfsHelper(M, visited, i);
                count++;
            }
        }
        return count;
    }
    
    private void dfsHelper(int[][] M, boolean[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfsHelper(M, visited, j);
            }
        }
    }
 
    /***************************************************************************************************************/
    //union find solution
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }
        
        int count = 0;
        int[] root = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            root[i] = i;
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M.length; j++) {
                if (M[i][j] == 1) {
                    union(root, i, j);
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            if (root[i] == i) {
                count++;
            }
        }
        return count;
    }
    
    private void union(int[] root, int nodeOne, int nodeTwo) {
        int rootOne = find(root, nodeOne);
        int rootTwo = find(root, nodeTwo);
        root[rootTwo] = rootOne;
        return;
    }
    
    private int find(int[] root, int current) {
        while (current != root[current]) { //compressed union find
            root[current] = root[root[current]];
            current = root[current];
            //equavalent to 
            //int father = root[current];
            //root[current] = root[father];
            //current = father;
        }
        return current;
    }
}
