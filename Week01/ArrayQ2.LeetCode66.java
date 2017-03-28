/*
66. Plus One
Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.
*/

public class Solution {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) return new int[]{1};
        int carry = 1, sum = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            sum = carry + digits[i];
            digits[i] = sum % 10;
            carry = sum / 10;
        }
        if (carry == 0) return digits;
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        for (int i = 1; i < result.length; i++) {
            result[i] = digits[i - 1];
        }
        return result;
    }
}
