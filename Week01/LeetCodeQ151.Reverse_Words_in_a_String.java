/*
151. Reverse Words in a String
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

"The string may have leading/trailing spaces"
*/

public class Solution {
    //kinda of brutal force
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        StringBuilder result = new StringBuilder();
        reverse(chars, 0, chars.length - 1);
        int left = -1, right = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                left = (i == 0 || chars[i - 1] == ' ') ? i : left;
                right = (i == chars.length - 1 || chars[i + 1] == ' ')? i : right;
            }
            if (left != -1 && right != -1) {
                reverse(chars, left, right);
                if (result.length() != 0) {
                    result.append(' ');
                }
                result.append(chars, left, right - left + 1);
                left = -1;
                right = -1;
            }
        }
        return result.toString();
    }
    
    private void reverse(char[] chars, int i, int j) {
        while (i <= j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
    }
    
    /******************************************************************************************/
    //optimized solution
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
