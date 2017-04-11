/*
Heapify
Given an integer array, heapify it into a min-heap array.

For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] 
and A[i * 2 + 2] is the right child of A[i].
*/

public class Solution {
    /**
     * @param A: Given an integer array
     * @return: void
     */
    public void heapify(int[] nums) {
        for (int i = nums.length / 2; i >= 0; i--) {
            siftDown(nums, i);
        }
    }
    
    private void siftDown(int[] nums, int k) {
        while (k < nums.length) {
            int smallest = k;
            if (k * 2 + 1 < nums.length && nums[k * 2 + 1] < nums[smallest]) {
                smallest = k * 2 + 1;
            }
            
            if (k * 2 + 2 < nums.length && nums[k * 2 + 2] < nums[smallest]) {
                smallest = k * 2 + 2;
            }
            
            if (smallest == k) break;
            swap(nums, smallest, k);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        if (nums[i] != nums[j]) { //nums[i] = a, nums[j] = b
            nums[i] ^= nums[j]; //nums[i] a不b，有a没b
            nums[j] ^= nums[i]; //nums[j] 变成a
            nums[i] ^= nums[j]; //nums[i] 现在是b
        }
        // a^(a^b) = b
        //b^(a^b) = a
    }
}
