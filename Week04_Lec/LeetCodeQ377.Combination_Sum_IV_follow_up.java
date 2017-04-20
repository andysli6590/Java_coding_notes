/*
377.Combination Sum IV follow up
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?
*/

/*
In order to handle negative integers in the input, the length of the combination sum needs to be restricted, or the search will not stop. 
We need to add the allowed max length of the output into the signature.
*/


public class Solution {
    private Map<Integer, Map<Integer, Integer>> cache;
    public int combinationSum4(int[] nums, int target, int maxLen) {
        if (nums == null || nums.length == 0) return 0;
        cache = new HashMap<>();
        return helper(nums, target, 0, maxLen);
    }
    
    private int helper(int[] nums, int remain, int len, int maxLen) {
        if (len > maxLen) return 0;
        if (cache.containsKey(remain) && cache.get(remain).containsKey(len)) {
            return cache.containsKey(remain).get(len);
        } //if search at current remain and len, the count is calculated, then return 
        int count = 0;
        if (remain == 0) count++;
        for (int num : nums) {
            count += helper(nums, remain - num, len + 1, maxLen);
        }
        //cache the count, at the sum at remain and len
        if (!cache.containsKey(remain)) {
            cache.put(remain, new HashMap<Integer, Integer>());
        }
        cache.get(remain).put(len, count);
        
        return count;
    }
}
