/*
395. Longest Substring with At Least K Repeating Characters
Find the length of the longest substring T of a given string (consists of lowercase letters only) such 
that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
*/

public class Solution {
    //divide and conquer solution
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        return helper(chars, 0, s.length(), k);
    }
    
    private int helper(char[] chars, int start, int end, int k) {
        //the substring length shorter than k
        if (end - start < k) { //substring [start, end)
            return 0;
        }
        int[] count = new int[26]; //count the frequence in the current substring domain
        for (int i = start; i < end; i++) {
            int index = chars[i] - 'a';
            count[index]++;
        }
        
        for (int i = 0; i < 26; i++) {
            //scan and find the characters that has the frequence less than k as the cut line
            if (count[i] < k && count[i] > 0) {
                //the frequence is less than k or the letter is not existing in the string
                for (int j = start; j < end; j++) {
                    if (chars[j] == i + 'a') { //find the character letter that frequence is less than k and split two parts
                        int left = helper(chars, start, j, k);
                        int right = helper(chars, j + 1, end, k);
                        return Math.max(left, right);
                    }
                }
            }
        }
        return end - start;
    }
    
    /*************************************************************************************************************************/
    //optimized solution using two pointers scan, 
    //time complexity  O(M*N), M is the unique characters of the input, in this particular case M is bounded to 26.
    //N is the input string length
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] count = new int[26];
        int h, i, j, index;
        int max = 0;
        int unique, noLessThanK;
        
        for (h = 1; h <= 26; h++) {
            //how many letters we are targeting
            //reset two pointers and registers
            Arrays.fill(count, 0);
            i = 0;
            j = 0;
            unique = 0;
            noLessThanK = 0;
            while (j < chars.length) {
                if (unique <= h) {
                    index = chars[j] - 'a';
                    if (count[index] == 0) {
                        unique++;
                    }
                    count[index]++;
                    if (count[index] == k) {
                        noLessThanK++;
                    }
                    j++;
                } else {
                    index = chars[i] - 'a';
                    if (count[index] == k) {
                        noLessThanK--;
                    }
                    count[index]--;
                    if (count[index] == 0) {
                        unique--;
                    }
                    i++;
                }
                //only when the numbers of unique letters equals to the total letters we scanned 
                //and equals the numbers of frequence that no less than K
                if (unique == h && unique == noLessThanK) {
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }
}
