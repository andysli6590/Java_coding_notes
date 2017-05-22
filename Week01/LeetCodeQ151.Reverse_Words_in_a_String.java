/*
151. Reverse Words in a String
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

"The string may have leading/trailing spaces"
*/

public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int end = s.length();
        StringBuilder result = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                end = i;
            } else if (i == 0 || s.charAt(i - 1) == ' ') {
                if (result.length() != 0) {
                    //existing words, separate with ' '
                    result.append(' ');
                }
                result.append(s.substring(i, end));
            }
        }
        return result.toString();
    }
}
