/*
"Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?
Would this affect the run-time complexity? How and why?"
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Write a function to determine if a given target is in the array.

The array may contain duplicates.
*/

public class Solution {
    public boolean search(int[] nums, int target) {
        //worst case O(n);
        //like this scenario: 1111110, find 0
        //we can not still use the binary search !!!!!
        //but you can directly iterate the array and find the index
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return true;
        }
        return false;
    }
}
