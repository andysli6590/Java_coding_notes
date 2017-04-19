/*
216. Combination Sum III
Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
*/

public class Solution {
    //dfs
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (k < 1 || n < 1) return result;
        dfsHelper(k, n, 1, result, new ArrayList<Integer>());
        return result;
    }
    
    private void dfsHelper(int k, int remain, int start, List<List<Integer>> result, List<Integer> path) {
        if (path.size() == k && remain == 0) { //base case
            result.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = start; i <= 9; i++) {
            if (remain < 2 * i && remain != i) continue; //去重优化和pruning
            path.add(i);
            dfsHelper(k, remain - i, i + 1, result, path);
            path.remove(path.size() - 1);
        }
    }
}
