/*
198. House Robber
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that adjacent houses have security system connected 
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.
*/

public class Solution {
    //DFS solution
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return helper(nums, nums.length - 1);
    }
    
    private int helper(int[] nums, int pos) {
        //corner/base case
        if (pos == 0) {
            return nums[0];
        }
        if (pos == 1) {
            return Math.max(nums[0], nums[1]);
        }
        
        //next layer
        return Math.max(helper(nums, pos - 1), helper(nums, pos - 2) + nums[pos]);
    }
}
