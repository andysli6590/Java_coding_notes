/*
377. Combination Sum IV
Given an integer array with all positive numbers and no duplicates, 
find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
*/

public class Solution {
    
    //dfs没有优化用memoried search, TLE
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        if (target == 0) return 1; //base case
        
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                result += combinationSum4(nums, target - nums[i]);
            }
        }
        return result;
    }
    
    //memorized search优化解法
    /*******************************************************************************************/
    //dfs没有优化用memoried search, TLE
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int[] count = new int[target + 1]; 
        //need to have target + 1 result array size because 0 is 1 way, no number to pick
        Arrays.fill(count, Integer.MIN_VALUE);
        count[0] = 1;
        return helper(nums, target, count);
    }
    
    private int helper(int[] nums, int remain, int[] count) {
        if (count[remain] != Integer.MIN_VALUE) return count[remain]; 
        //if it is already calculated
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (remain >= nums[i]) {
                result += helper(nums, remain - nums[i], count);
            }
        }
        count[remain] = result;
        return result;
    }
    
    /**************************************************************************************/
    
}
