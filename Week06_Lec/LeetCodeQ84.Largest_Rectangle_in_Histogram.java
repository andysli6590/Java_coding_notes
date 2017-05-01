/*
84. Largest Rectangle in Histogram
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
find the area of largest rectangle in the histogram.

For example,
Given heights = [2,1,5,6,2,3],
return 10.
*/

public class Solution {
    //用最俗栈结构去找左边界和右边界
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>(); //store the index
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            //set curVal to the minimum value to guarantee the last element to be put into stack
            int curVal = i == heights.length ? 0 : heights[i];
            //1. check whether curVal should be put into stack by comparing to peek elem of stack
            while (!stack.isEmpty() && heights[stack.peekLast()] >= curVal) {
                int height = heights[stack.pollLast()];
                //find left and right boundary from current rectangle
                int leftBound = stack.isEmpty() ? 0 : stack.peekLast() + 1;
                int rightBound = i;
                max = Math.max(max, height * (rightBound - leftBound));
            }
            //2. if curVal > stack.peekLast() push the element into stack
            stack.offerLast(i);
        }
        return max;
    }
}
