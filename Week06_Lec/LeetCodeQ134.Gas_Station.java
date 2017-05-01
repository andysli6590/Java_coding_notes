/*
134. Gas Station
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.

Example:
gas[i] =  [3,4,3,6,7,1,2]
cost[i] = [2,3,5,1,5,1,3]
return 4;
*/


public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0) {
            return -1;
        }
        if (gas.length != cost.length) {
            return -1;
        }
        int start = gas.length - 1;
        int end = 0;
        int sum = gas[start] - cost[start];
        while (end < start) {
            //Case 1: sum < 0 --> a new start needed
            //need more petrol in the start
            if (sum < 0) {
                sum += gas[--start] - cost[start];
            } else { //sum >= 0, we can move forward
                sum += gas[end] - cost[end++];
            }
        }
        return sum >= 0 ? start : -1;
    }
}
