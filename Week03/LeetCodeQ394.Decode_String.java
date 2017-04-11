/*
394. Decode String
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. 
For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
*/

public class Solution {
    private int index = 0; //global variable for the scanning process
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return "";
        StringBuilder result = new StringBuilder();
        int num = 0;
        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                while (Character.isDigit(s.charAt(index))) {
                    num = num * 10 + s.charAt(index++) - '0';
                }
                index++; //s.charAt(index) == '[';
                String nestStr = decodeString(s);
                while (num > 0) {
                    result.append(nestStr);
                    num--;
                }
            } else if (Character.isLetter(s.charAt(index))) {
                result.append(s.charAt(index++));
            } else if (s.charAt(index++) == ']') {
                return result.toString();
            }
        }
        return result.toString();
    }
}
