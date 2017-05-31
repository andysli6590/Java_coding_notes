/*
213. House Robber II
Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so 
that he will not get too much attention. This time, all houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses 
remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.
*/

public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(helper(nums, 0, nums.length - 2), helper(nums, 1, nums.length - 1));
    }
    
    private int helper(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int prevNo = 0, prevYes = 0; //prevNo, prevYes, i, i + 1;
        for (int i = start; i <= end; i++) {
            int temp = prevNo; //对于i来说的prev not include i
            prevNo = Math.max(prevNo, prevYes); //更新对于i + 1来说的prev not include i
            prevYes = temp + nums[i]; //对于i + 1 来说的prev include i
        }
        return Math.max(prevNo, prevYes);
    }
    
    
    /*************************************************************************************************/
    //优化简练的版本
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(subRob(nums, 0, nums.length - 2), subRob(nums, 1, nums.length - 1));
    }
    
    private int subRob(int[] nums, int start, int end) {
        int prev = 0;
        int cur = nums[start];
        
        for (int i = start + 1; i <= end; i++) {
            int next = Math.max(cur, prev + nums[i]);
            prev = cur;
            cur = next;
        }
        return cur;
    }
}
