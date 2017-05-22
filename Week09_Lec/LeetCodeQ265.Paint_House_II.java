/*
265. Paint House II
There are a row of n houses, each house can be painted with one of the k colors. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color 0; 
costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
*/

public class Solution {
    //time: O(kn), space: O(k)
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        if (costs[0] == null || costs[0].length == 0) {
            return 0;
        }
        
        int colors = costs[0].length;
        int[] last = new int[colors];
        int[] cur = new int[colors];
        
        for (int[] curCosts : costs) {
            for (int i = 0; i < colors; i++) {
                cur[i] = curCosts[i] + findMin(last, i);
            }
            //swap cur and last or copy the cur elements value into last both works, swap save more time
            int[] temp = cur;
            cur = last;
            last = temp;
            // for (int i = 0; i < colors; i++) {
            //     last[i] = cur[i];
            // }
        }
        return findMin(last, last.length);
    }
    
    private int findMin(int[] arr, int except) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (i != except) {
                min = Math.min(min, arr[i]);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
