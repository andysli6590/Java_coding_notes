/*
439. Ternary Expression Parser
Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. 
You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F 
(T and F represent True and False respectively).

Note:

1. The length of the given string is ≤ 10000.
2. Each number will contain only one digit.
3. The conditional expressions group right-to-left (as usual in most languages).
4. The condition will always be either T or F. That is, the condition will never be a digit.
5. The result of the expression will always evaluate to either a digit 0-9, T or F.

Example 1:

Input: "T?2:3"

Output: "2"

Explanation: If true, then result is 2; otherwise result is 3.
Example 2:

Input: "F?1:T?4:5"

Output: "4"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"
Example 3:

Input: "T?T?F:5:3"

Output: "F"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
          -> "F"                                    -> "F"
*/

public class Solution {
    //iteration
    public String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) {
            return "";
        }
        Deque<Character> stack = new ArrayDeque<>();
        //Deque<Character> stack = new LinkedList<>();
        for (int i = expression.length() - 1; i >= 0; i--) {
            char ch = expression.charAt(i);
            if (!stack.isEmpty() && stack.peek() == '?') {
                stack.pop(); //pop '?'
                char first = stack.pop(); 
                stack.pop(); //pop ':'
                char second = stack.pop();
                
                if (ch == 'T') {
                    stack.push(first);
                } else {
                    stack.push(second);
                }
            } else {
                stack.push(ch);
            }
        }
        return String.valueOf(stack.peekLast());
    }
  
    /*********************************************************************************************************************/
    //recursion
    private int index;
    public String parseTernary(String expression) {
        index = 0;
        if (expression == null || expression.length() == 0) {
            return "";
        }
        return Character.toString(helper(expression));
        //return String.valueOf(helper(expression));
    }
    
    private char helper(String s) {
        char ch = s.charAt(index);
        if (index + 1 == s.length() || s.charAt(index + 1) == ':') {
            index += 2; //pass ':'
            return ch;
        }
        index += 2; //pass '?'
        char first = helper(s);
        char second = helper(s);
        return ch == 'T' ? first : second;
    }
}
