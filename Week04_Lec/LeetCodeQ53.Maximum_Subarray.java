/*
53. Maximum Subarray
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6
*/

public class Solution {
    //自己写的思路是如果当前sum < 0,就舍弃，把sum重置为0
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
        int sum = 0, max = Integer.MIN_VALUE, min = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
        }
        return max;
    }
    
    /***************************************************************/
    //using the idea from solution tree in the lecture
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
        int lastSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            lastSum = Math.max(nums[i], lastSum + nums[i]);
            maxSum = Math.max(maxSum, lastSum);
        }
        return maxSum;
    }
}
