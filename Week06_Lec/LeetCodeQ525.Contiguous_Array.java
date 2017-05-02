/*
525. Contiguous Array
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.
*/

public class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //set 0 to -1
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] == 0 ? -1 : nums[i];
        }
        //key: sum, value: index
        Map<Integer, Integer> idxMap = new HashMap<>();
        idxMap.put(0, -1);
        int sum = 0;
        int max = 0;
        //find subarray sum is equal to 0, using hashmap
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            Integer firstIdx = idxMap.get(sum);
            if (firstIdx == null) {
                idxMap.put(sum, i);
            } else {
                max = Math.max(max, i - firstIdx);
            }
            
//             if (idxMap.containsKey(sum)) {
//                 max = Math.max(max, i - idxMap.get(sum));
//             } else {
//                 idxMap.put(sum, i);
//             }
        }
        
        //set the signature input array back
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] == -1 ? 0 : nums[i];
        }
        return max;
    }
}
