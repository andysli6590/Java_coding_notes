/*
135. Candy
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

 .Each child must have at least one candy.
 .Children with a higher rating get more candies than their neighbors.
 
What is the minimum candies you must give?
*/

public class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int len = ratings.length;
        int[] left = new int[len];
        int[] right = new int[len];
        //scan from left to right
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        //scan from right to left
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        //sum up for result
        int total = 0;
        for (int i = 0; i < len; i++) {
            total += Math.max(left[i], right[i]) + 1;
            //+1 is because each child at least has 1 candy
        }
        return total;
    }
}
