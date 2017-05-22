/*
152. Maximum Product Subarray
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

public class Solution {
    //最大值可能在之前的最大值*当前值，之前的最小值*当前值，或者当前值，所以需要从这3个里面选出来最大值
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int min = nums[0];
        int globalMax = max;
        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max* nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp* nums[i], min * nums[i]), nums[i]);
            globalMax = Math.max(globalMax, max);
        }
        return globalMax;
    }
}
