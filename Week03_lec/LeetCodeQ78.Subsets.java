/*
78. Subsets
Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/


public class Solution {
    
    //DFS, back tracking
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) return result;
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        helper(nums, 0, result, new ArrayList<>());
        return result;
    }
    
    private void helper(int[] nums, int index, List<List<Integer>> result, List<Integer> path) {
        result.add(new ArrayList<>(path));
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            helper(nums, i + 1, result, path);
            path.remove(path.size() - 1);
        }
    }
  
    /*********************************************************************/
    //iteration, normal implementation
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) return result;
        result.add(new ArrayList<Integer>());
        //Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int prevNum = result.size();
            for (int j = 0; j < prevNum; j++) {
                List<Integer> subset = new ArrayList<>(result.get(j));
                subset.add(nums[i]);
                result.add(new ArrayList<>(subset));
            }
        }
        return result;
    }
  
    /*
    // 1 << n is 2^n
    // each subset equals to an binary integer between 0 .. 2^n - 1
    // 0 -> 000 -> []
    // 1 -> 001 -> [1]
    // 2 -> 010 -> [2]
    // ..
    // 7 -> 111 -> [1,2,3]
    */
    public List<List<Integer>> subsets(int[] nums) {
          List<List<Integer>> result = new ArrayList<>();
          int n = nums.length;
          Arrays.sort(nums);
          for (int i = 0; i < (1 << n); i++) {
              List<Integer> subset = new ArrayList<>();
              for (int j = 0; j < n; j++) {
                  if ((i & (1 << j)) != 0) { // check whether the jth digit in i's binary representation is 1
                      subset.add(nums[j]);
                  }
              }
              result.add(subset);
          }
          return result;
      }
}
