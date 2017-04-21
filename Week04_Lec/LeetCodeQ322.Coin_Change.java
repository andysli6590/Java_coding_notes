/*
322. Coin Change
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
*/

public class Solution {
    //memorized search implementation, idea from drawing recursion/solution tree, and figure out it is a graph
    public int coinChange(int[] coins, int amount) {
        //换取amout为0，需要0张钱
        //so we need to have amount + 1 size
        if (coins == null || coins.length == 0) return -1;
        int[] count = new int[amount + 1];
        Arrays.fill(count, Integer.MAX_VALUE);
        count[0] = 0;
        Arrays.sort(count);
        return helper(coins, amount, count);
    }
    
    //memorized search helper
    private int helper(int[] coins, int remain, int[] count) {
        //if the amount is already calculated and stored, just return it
        if (count[remain] != Integer.MAX_VALUE) {
            return count[remain];
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if(remain >= coins[i]) {
                int temp = helper(coins, remain - coins[i], count);
                //p(n) depends on p(n - nums[i]), divide and conquer
                if (temp != -1) {
                    res = Math.min(res, temp + 1);
                }
            }
        }
        count[remain] = (res == Integer.MAX_VALUE ? -1 : res);
        return count[remain];
    }
}
