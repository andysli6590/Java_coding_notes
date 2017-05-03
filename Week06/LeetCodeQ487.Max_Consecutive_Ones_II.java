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
    /*******************************************************************************************************/
    //follow up
    /*we need to store up to k indexes of zero within the window [l, h] 
    so that we know where to move l next when the window contains more than k zero. If the input stream is infinite, 
    then the output could be extremely large because there could be super long consecutive ones. 
    In that case we can use BigInteger for all indexes. For simplicity, here we will use int
    */
    //Time: O(n) Space: O(k)
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, k = 1; //flip at most k zero
        Deque<Integer> zeroIndex = new LinkedList<>();
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0) {
                zeroIndex.offerLast(h);
            }
            if (zeroIndex.size() > k) {
                l = zeroIndex.pollFirst() + 1; //can not count current  nums[l] = 0
            }
            max = Math.max(max, h - l + 1);
        }
        return max;
    }
    
    /*********************************************************************************************************/
    //in this case, k = 1, flip at most 1 zero
    //the above solution could be simplfied:
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, q = -1; // flip at most 1 zero, store 0 index
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0) {
                l = q + 1; //current 0 index + 1
                q = h; //store the next 0 index
            }
            max = Math.max(max, h - l + 1);
        }
        return max;
    }
}
