/*
39. Combination Sum
Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]
*/

public class Solution {
    //DFS, idea from recursion tree/solution tree
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); //necessary, to help remove duplications
        dfsHelper(candidates, 0, target, result, new ArrayList<Integer>());
        return result;
    }
    
    private void dfsHelper(int[] candidates, int index, int remain, List<List<Integer>> result, List<Integer> path) {
        if (remain == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            if (remain >= candidates[i]) {
                dfsHelper(candidates, i, remain - candidates[i], result, path);
            }
            path.remove(path.size() - 1);
        }
    }
}
