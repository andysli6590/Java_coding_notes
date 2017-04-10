/*
46. Permutations
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/
public class Solution {
  //iteration
  public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      if (nums == null) return result;
      result.add(new ArrayList<Integer>());
      for (int i = 0; i < nums.length; i++) {
          List<List<Integer>> newResult = new ArrayList<>();
          for(int j = 0; j <= i; j++) {
              for (List<Integer> list : result) {
                  List<Integer> newList = new ArrayList<>(list);
                  newList.add(j, nums[i]);
                  newResult.add(newList);
              }
          }
          result = newResult;
      }
      return result;
  }

  /************************************/
  //DFS, back tracking
  public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) return result;
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        boolean[] visited = new boolean[nums.length];
        helper(nums, visited, result, new ArrayList<Integer>());
        return result;
    }

    private void helper(int[] nums, boolean[] visited, List<List<Integer>> result, List<Integer> answer) {
        if (answer.size() == nums.length) {
            result.add(new ArrayList<>(answer));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            answer.add(nums[i]);
            visited[i] = true;
            helper(nums, visited, result, answer);
            answer.remove(answer.size() - 1);
            visited[i] = false;
        }
    }
}
