/*
47. Permutations II
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/

public class Solution {
    
    
    
    /*********************************************************************/
    //DFS, back tracking
    public List<List<Integer>> permuteUnique(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if (nums == null) return result;
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        helper(nums, visited, result, new ArrayList<Integer>());
        return result;
    }
    
    private void helper(int[] nums, boolean[] visited, 
                        ArrayList<List<Integer>> result, 
                        List<Integer> answer) {
        if (answer.size() == nums.length) {
            result.add(new ArrayList<>(answer));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || i > 0 && nums[i-1] == nums[i] && !visited[i-1]) continue;
            answer.add(nums[i]);
            visited[i] = true;
            helper(nums, visited, result, answer);
            answer.remove(answer.size() - 1);
            visited[i] = false;
        }
    }
}
