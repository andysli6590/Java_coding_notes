/*
75. Sort Colors
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, 
with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.
*/

public class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int left = 0; //represents the right boundary of processed '0' 
        int right = nums.length -1; //represents the left boundary of processed '2'
        int index = 0; //scanner for unprocessed data 
        while (index <= right) {
            if (nums[index] == 0) { //0 - start inclusive is 0
                swap(nums, left++, index++);
            } else if (nums[index] == 2) { // end - nums.length inclusive is 2
                swap(nums, index, right--);
            } else { //1 rane
                index++;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
}
