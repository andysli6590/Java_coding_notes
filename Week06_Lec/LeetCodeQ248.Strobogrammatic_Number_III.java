/*
248. Strobogrammatic Number III
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/


public class Solution {
    
    private static final char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'9', '6'}, {'8', '8'}};
    
    public int strobogrammaticInRange(String low, String high) {
        int[] count = {0};
        for (int i = low.length(); i <= high.length(); i++) {
            char[] cur = new char[i];
            helper(low, high, 0, i - 1, count, cur);
        }
        return count[0];
    }
    
    private void helper(String low, String high, int left, int right, int[] count, char[] cur) {
        if (left > right) {
            if (isValid(low, high, cur)) {
                count[0]++;
            }
            return;
        }
        //composite the result
        for (char[] p : pairs) {
            cur[left] = p[0];
            cur[right] = p[1];
            if (cur.length != 1 && cur[0] == '0') {
                continue;
            }
            if (left == right && p[0] != p[1]) {
                continue;
            }
            helper(low, high, left + 1, right - 1, count, cur);
        }
    }
    
    private boolean isValid(String low, String high, char[] chars) {
        String cur = new String(chars);
        if ((cur.length() == low.length() && cur.compareTo(low) < 0) || (cur.length() == high.length() && cur.compareTo(high) > 0)) {
            return false;
        } else {
            return true;
        }
    }
}
