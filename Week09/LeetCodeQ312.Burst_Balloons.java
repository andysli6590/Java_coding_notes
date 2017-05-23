/*
312. Burst Balloons
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it 
represented by array nums. You are asked to burst all the balloons. 
If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
*/

public class Solution {
    //memorized search, using dp as cache
    //time: O(n^3), space: O(n^2);
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        //composite new input array
        int[] input = new int[len + 2];
        for (int i = 1; i <= len; i++) {
            input[i] = nums[i - 1];
        }
        input[0] = 1;
        input[len + 1] = 1;
        boolean[][] visited = new boolean[len + 2][len + 2];
        int[][] dp = new int[len + 2][len + 2];
        return searchHelper(input, dp, visited, 1, len);
    }
    
    private int searchHelper(int[] input, int[][] dp, boolean[][] visited, int left, int right) {
        if (left > right) {
            return 0;
        }
        if (visited[left][right] == true) {
            return dp[left][right];
        }
        int result = 0;
        for (int i = left; i <= right; i++) {
            int midValue = input[left - 1] * input[i] * input[right + 1];
            int leftValue = searchHelper(input, dp, visited, left, i - 1);
            int rightValue = searchHelper(input, dp, visited, i + 1, right);
            result = Math.max(result, leftValue + midValue + rightValue);
        }
        visited[left][right] = true;
        dp[left][right] = result;
        return result;
    }
}
