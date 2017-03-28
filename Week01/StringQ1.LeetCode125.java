/*
125. Valid Palindrome
Given a string, determine if it is a palindrome, 
considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.
*/
public class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        s = s.toLowerCase();
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (i < j && !(Character.isDigit(s.charAt(i)) || Character.isLetter(s.charAt(i)))) i++;
            while (i < j && !(Character.isDigit(s.charAt(j)) || Character.isLetter(s.charAt(j)))) j--;
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}