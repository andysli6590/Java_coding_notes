/*
567. Permutation in String
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
In other words, one of the first string's permutations is the substring of the second string.

Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False
Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
*/

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        //corner case
        if (s1 == null || s1.length() == 0) {
            return true;
        }
        if (s2 == null || s2.length() == 0) {
            return false;
        }
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] count = new int[26];
        //creating sliding window, length is s1.length()
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++; //count the letters
            count[s2.charAt(i) - 'a']--; //offset
        }
        
        //check current window content see if it match
        if (isMatch(count)) {
            return true;
        }
        
        for (int i = s1.length(); i < s2.length(); i++) {
            count[s2.charAt(i) - 'a']--; //add right side elem to window set to offset
            count[s2.charAt(i - s1.length()) - 'a']++; //remove the offset of the left side elem
            if (isMatch(count)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isMatch(int[] count) {
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
