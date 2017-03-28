/*
228. Summary Ranges
Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/
public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int start = 0, end = 0;
        while (end < nums.length && start < nums.length) {
            StringBuilder sb = new StringBuilder();
            sb.append(nums[start]);
            end = start;
            while(end + 1 < nums.length && nums[end] + 1 == nums[end + 1]) end++;
            if (end != start) {
                sb.append("->").append(nums[end]);
            }
            start = end + 1;
            result.add(sb.toString());
        }
        return result;
    }
}
