/*
Quick sort
Given an integer array, sort it in ascending order. Use quick sort, merge sort, heap sort or any O(nlogn) algorithm.
Given [3, 2, 1, 4, 5], return [1, 2, 3, 4, 5].
*/

public class Solution {
    /**
     * @param A an integer array
     * @return void
     */
  
    //time complexity: O(nlogn), space complexity: O(1) + O(logn) stack frame
    public void sortIntegers2(int[] nums) {
        // Write your code here
        sortIntegers2Helper(nums, 0, nums.length - 1);
    }
    
    private void sortIntegers2Helper(int[] nums, int left, int right) {
        if (left >= right) return;
        int pivot = nums[(left + right) >>> 1];
        int i = left;
        int j = right;
        
        while (i <= j) {
            while (i <= j && nums[i] < pivot) {
                i++;
            }
            while (i <= j && nums[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(nums, i++, j--);
            }
        }
        
        sortIntegers2Helper(nums, left, j);
        sortIntegers2Helper(nums, i, right);
    }
    
    private void swap(int[] nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
}
