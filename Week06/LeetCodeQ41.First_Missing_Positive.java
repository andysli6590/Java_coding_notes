/*
41. First Missing Positive
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

public class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            //Put each number in its right place. E.g. When we find 5, then swap it with nums[4].
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) { //nums[i] - 1 != i also works
                swap(nums, i, nums[i] - 1); //swap within the array, so O(1) space
            }
        }
        //At last, the first place where its number is not right, return the place + 1.
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
    
    private void swap(int[] nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
}
