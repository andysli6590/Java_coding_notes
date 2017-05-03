/*
503. Next Greater Element II
Given a circular array (the next element of the last element is the first element of the array), 
print the Next Greater Number for every element. 
The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, 
which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.
*/

public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int[] result = new int[nums.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < nums.length * 2; i++) {
            while (!stack.isEmpty() && nums[stack.getLast()] < nums[i % nums.length]) {
                result[stack.pollLast()] = nums[i % nums.length];
            }
            if (i < nums.length) {
                stack.offerLast(i);
            }
        }
        while(!stack.isEmpty()) {
            result[stack.pollLast()] = -1;
        }
        return result;
    }
}
