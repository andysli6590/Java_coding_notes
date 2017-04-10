public class Solution {
    /**
     * @param A an integer array
     * @return void
     */
    public void sortIntegers(int[] nums) {
        //selection sort, this implementation is stable
        if (nums == null || nums.length <= 1) return;
        int n = nums.length;
        int minValueIndex = 0;
        
        for (int i = 0; i < n; i++) {
            minValueIndex = i;
            
            for (int j = i+1; j < n; j ++) {
                if (nums[j] < nums[minValueIndex]) minValueIndex = j;
            }
            
//             //swap A[j] and A[minValueIndex]
//             int temp = A[i];
//             A[i] = A[minValueIndex];
//             A[minValueIndex] = temp;
            swap(nums, j, minValueIndex);
        }
    }
    
    private void swap(nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
}
