/*
两种insertion的写法，一种是用swap是unstable的，一种是整体在移动位置的方法是stable的。
*/


public class Solution {
    /**
     * @param A an integer array
     * @return void
     */
    public void sortIntegers(int[] nums) {
        //unstable implementation
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (nums[j-1] > nums[j]) {
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
  
  /**********************************/
  //stable implementation
  public void sortIntegers(int[] nums) {
        int x, j;
        for (int i = 1; i < nums.length; i++) {
            x = nums[i];
            j = i - 1;
            while (j >= 0 && nums[j] > x) {
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1] = x;
        }
    }
}
