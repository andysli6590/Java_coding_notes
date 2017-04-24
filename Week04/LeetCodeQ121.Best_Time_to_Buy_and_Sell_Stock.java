/*
121. Best Time to Buy and Sell Stock
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.
*/
public class Solution {
    //暴力枚举，没有优化，在leetcode上超时
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            int buy = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                int sell = prices[j];
                maxProfit= Math.max(maxProfit, sell - buy);
            }
        }
        return maxProfit;
    }
    
    /********************************************************************************/
    //优化过的解法, from left to right scan
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minStockPrice = prices[0]; //记录当前值i之前(0 - i-1)的股票的最小值
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) { //当前值是prices[i]
            if (prices[i] > minStockPrice) { //如果当前值大于之前所记录的最小值，这个差值有可能是maxProfit的候选
                maxProfit = Math.max(maxProfit, prices[i] - minStockPrice);    
            } else if (prices[i] < minStockPrice) { //如果当前值小鱼之前所记录的最小值，这个最小值需要被更新
                minStockPrice = prices[i];
            }
        }
        return maxProfit;
    }
    
    //相似解法，from right to left scan
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int maxStockPrice = prices[prices.length - 1];
        int maxProfit = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            if (prices[i] < maxStockPrice) {
                maxProfit = Math.max(maxProfit, maxStockPrice - prices[i]);
            } else {
                maxStockPrice = prices[i];
            }
        }
        return maxProfit;
    }
    
    /**************************************************************************************/
    /*There is another approach by converting the problem into Maximum Subarray problem: 
    LeetCode Maximum Subarray: 4 methods, DP, Divide and Conquer. 
    That is, from the original array prices, we make a new array called A with A[i] = prices[i] – prices[i-1], 
    then the answer to best time to buy and sell stock is the same answer the find the maximum sum of consecutive subarray in array A. 
    The following code implements this idea.
    */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int sum = 0;
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            sum += prices[i] - prices[i - 1];
            maxProfit = Math.max(maxProfit, sum);
            sum = Math.max(0, sum);
        }
        return maxProfit;
    }
    
}
