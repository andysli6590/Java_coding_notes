/*
324. Wiggle Sort II
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
(3) Given nums = [2, 1], answer is [1, 2].
Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
*/

public class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int median = findKthLargest(nums, (nums.length + 1) >> 1);
        int n = nums.length;
        int left = 0, cur = 0, right = n - 1;
        while (cur <= right) { //like sort colors, < median, median, > median
            if (nums[transIndex(cur, n)] > median) { 
                // left is even index: 0,2,4,... should have value > median
                swap(nums, transIndex(left++, n), transIndex(cur++, n));
            } else if (nums[transIndex(cur, n)] < median) { 
                // right is odd index: 1,3,5,... should have value < median
                swap(nums, transIndex(right--, n), transIndex(cur, n));
            } else {
                cur++;
            }
        }
    }
    
    private int transIndex(int i, int len) {
        if (i < (len >> 1)) { 
            return 1 + (i << 1); //create odd index, means i * 2 + 1
        } else {
            //create even index
            return (i - (len >> 1)) << 1; //means i * 2
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
    
    private int findKthLargest(int[] nums, int k) {
        helper(nums, 0, nums.length - 1, k);
        return nums[k - 1];
    }
    
    private void helper(int[] nums, int left, int right, int k) {
        //corner case
        if (left > k - 1 || left >= right) {
            return;
        }
        int pivot = nums[(left + right) >>> 1];
        int i = left, j = right;
        while (i <= j) {
            while (i <= j && nums[i] > pivot) {
                i++;
            }
            while (i <= j && nums[j] < pivot) {
                j--;
            }
            if (i <= j) {
                swap(nums, i++, j--);
            }
        }
        helper(nums, left, j, k);
        helper(nums, i, right, k);
    }
}

