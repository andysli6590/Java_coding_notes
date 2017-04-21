/*
40. Combination Sum II
Given a collection of candidate numbers (C) and a target number (T), 
find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
*/

public class Solution {
    //dfs
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfsHelper(candidates, 0, target, result, new ArrayList<Integer>());
        return result;
    }
    
    private void dfsHelper(int[] candidates, int index, int remain, List<List<Integer>> result, List<Integer> path) {
        if (remain == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i - 1] == candidates[i]) continue; //去重
            //i == index的时候都不会skip所以1，1，1，1 = 4这种case是包含的，去掉的情况是每一次都是新的1开始的情况
            path.add(candidates[i]);
            if (remain >= candidates[i]) { //pruning
                dfsHelper(candidates, i + 1, remain - candidates[i], result, path);
            }
            path.remove(path.size() - 1);
        }
    }
}
