/*
246. Strobogrammatic Number
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/

public class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }
        int left = 0;
        int right = num.length() - 1;
        while (left <= right) { //一定是小于等于，自己也要判断自己
            if (!match(num.charAt(left++), num.charAt(right--))) {
                return false;
            }
        }
        return true;
    }
    
    private boolean match(char a, char b) {
        return (a == '0' && b == '0') || (a == '1' && b == '1') 
                || (a == '6' && b == '9') || (a == '9' && b == '6')
                || (a == '8' && b == '8');
    }
}
