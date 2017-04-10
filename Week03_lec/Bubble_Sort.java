public class Solution {
    /**
     * @param A an integer array
     * @return void
     */
    //Time: O(n^2), space: O(1), stable
    public void sortIntegers(int[] nums) {
        //bubble sort
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[j - 1] > nums[j]) {
                    swap(nums, j - 1, j);
                }
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        int nums[i] = nums[j];
        int nums[j] = temp;
    }
    
    /******************************************************************/
    //optimized bubble sort
    public void sortIntegers(int[] nums) {
        //bubble sort
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) { 
                //the optimized is because each loop 1 number will be settle to his finally location
                //so we dont need to check every time
                if (nums[j - 1] > nums[j]) {
                    swap(nums, j - 1, j);
                }
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
