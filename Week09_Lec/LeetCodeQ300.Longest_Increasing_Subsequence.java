/*
300. Longest Increasing Subsequence
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
*/

public class Solution {
    //Dp : fill the Dynamic Array
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        int increLeft[] = new int[nums.length];
        //initialization
        for (int i = 0; i < nums.length; i++) {
            increLeft[i] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (increLeft[j] + 1 > increLeft[i]) {
                        increLeft[i] = increLeft[j] + 1;
                    }
                    //increLeft[i] = Math.max(increLeft[i], increLeft[j] + 1);
                }
            }
        }
        int longest = 0;
        for (int i = 0; i < increLeft.length; i++) {
            longest = Math.max(longest, increLeft[i]);
        }
        return longest;
    }
}
