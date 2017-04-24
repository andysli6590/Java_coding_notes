/*
70. Climbing Stairs
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.
*/

public class Solution {
    //最暴力dfs的解法，没有用memorized cache优化，在leetcode会超时
    public int climbStairs(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
    
    /********************************************************************************/
    //memorized search优化过后在leetcode是AC的
    public int climbStairs(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] count = new int[n + 1];
        Arrays.fill(count, Integer.MIN_VALUE);
        count[0] = 1;
        count[1] = 1;
        count[2] = 2;
        return helper(n - 1, count) + helper(n - 2, count);
    }
    
    private int helper(int n, int[] count) {
        if (count[n] != Integer.MIN_VALUE) {
            return count[n];
        }
        count[n] = helper(n - 1, count) + helper(n - 2, count);
        return count[n];
    }
    
    /*******************************************************************************/
    //标准的dp array 解法，iteratively fill dp array
    public int climbStairs(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    
    /*********************************************************************************/
    //rolling array优化，节省空间
    public int climbStairs(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[3];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i % 3] = dp[(i - 1) % 3] + dp[(i - 2) % 3];
        }
        return dp[n % 3];
    }
    
    /**********************************************************************************/
    //两个指针版本的roll array dp
    public int climbStairs(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int current = 0;
        int prevOneStep = 2;  //i - 1;
        int prevTwoStep = 1;  //i - 2;
        for (int i = 3; i <= n; i++) {
            current =  prevOneStep + prevTwoStep;
            prevTwoStep = prevOneStep; //update i - 2;
            prevOneStep = current; //update i - 1
        }
        return current;
    }
} 
