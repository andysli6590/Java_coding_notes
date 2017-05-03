/*
487. Max Consecutive Ones II
Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

Example 1:
Input: [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
    After flipping, the maximum number of consecutive 1s is 4.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
Follow up:
What if the input numbers come in one by one as an infinite stream? In other words, 
you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
*/


public class Solution {
    //The idea is to keep a window [l, h] that contains at most k zero
    //Time: O(n) Space: O(1)
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0, zero = 0, k = 1; //flip at most k zero
        for (int left = 0, right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zero++;
            }
            while (zero > k) {
                if (nums[left++] == 0) {
                    zero--;
                }
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
