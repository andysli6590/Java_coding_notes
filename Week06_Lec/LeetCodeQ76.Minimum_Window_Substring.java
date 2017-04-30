/*
76. Minimum Window Substring
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/

public class Solution {
    //two pointers, chasing pointers, 追击问题双指针，start, end 互相scanner 和 fixer
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        int count = t.length();
        int start = 0, end = 0, minLen = Integer.MAX_VALUE, startIndex = 0;
        int[] map = new int[256]; //[128] both works
        //put all characters in t into map
        for (char ch : t.toCharArray()) {
            map[ch]++;
        }
        char[] source = s.toCharArray();
        while (end < source.length) {
            //1. move right to find a match
            if (map[source[end++]]-- > 0) {
                count--;
            }
            while (count == 0) {
                //2.move left when a match is found
                if (end - start < minLen) {
                    startIndex = start;
                    minLen = end - start;
                }
                if (map[source[start++]]++ == 0) {
                    count++;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : new String(source, startIndex, minLen);
    }
}
