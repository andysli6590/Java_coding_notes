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
    
    /********************************************************************/
    //iteration implementation
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return s;
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<String> strStack = new ArrayDeque<>();
        int index = 0;
        String result = "";
        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                int count = 0;
                while (Character.isDigit(s.charAt(index))) {
                    count = count * 10 + s.charAt(index++) - '0';
                }
                countStack.offerLast(count);
            } else if (s.charAt(index) == '[') {
                strStack.offerLast(result);
                result = "";
                index++;   //index++不能写在if/else if 的判断语句里
            } else if (s.charAt(index) == ']') {
                StringBuilder temp = new StringBuilder(strStack.pollLast());
                int currentCount = countStack.pollLast();
                for (int i = 0; i < currentCount; i++) {
                    temp.append(result);
                }
                result = temp.toString();
                index++;  //index++不能写在if/else if 的判断语句里
            } else {
                result += s.charAt(index++);
            }
        }
        return result;
    }
}
