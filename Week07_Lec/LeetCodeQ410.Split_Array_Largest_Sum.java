/*
410. Split Array Largest Sum
Given an array which consists of non-negative integers and an integer m, 
you can split the array into m non-empty continuous subarrays. Write an algorithm 
to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
*/

public class Solution {
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0;
        long sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        if (m == 1) {
            return (int)sum;
        }
        //二分的两端
        long start = Math.max(sum / m, max); 
        long end = sum - m + 1;
        while (start < end - 1) {
            long mid = (start + end) / 2;
            if (isValid(mid, nums, m)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return isValid(start, nums, m) ? (int)start : (int)end;
    }
    
    //to check how many subarrays/pieces it can divided
    private boolean isValid(long target, int[] nums, int m) {
        int count = 1;
        long total = 0;
        for (int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }
}
