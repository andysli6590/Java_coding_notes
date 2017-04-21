/*
256. Paint House
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, 
and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
*/

public class Solution {
    //不是连通图，不能用memorized search的方法
    public int minCost(int[][] costs) {
        int[] last = new int[3];
        int[] cur = new int[3];
        
        for (int[] cost : costs) {
            for (int i = 0; i < 3; i++) {
                cur[i] = cost[i] + Math.min(last[(i + 1) % 3], last[(i + 2) % 3]);
                //select the min from the other two calculated value in last
            }
            int[] temp = cur;
            cur = last;
            last = temp;
            //把last指向cur所指的内存内容，把cur指向last的内存内容用来存计算后新的值；
            //可以把cur[0] = cur[1] = cur[2] = reset成任何值不会影响结果，所需要的就是不同于last的内存空间
            //这里不能直接用last = cur,这样cur和last会指向同一个内存所存的内容，改变cur的时候会影响到last的值!!!!
        }
        return Math.min(last[0], Math.min(last[1], last[2]));
    }
}
