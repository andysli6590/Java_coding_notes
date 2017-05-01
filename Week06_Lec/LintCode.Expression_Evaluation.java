/*
Expression Evaluatio
Given an expression string array, return the final result of this expression
The expression contains only integer, +, -, *, /, (, ).

Example
For the expression 2*6-(23+7)/(1+2),
input is

[
  "2", "*", "6", "-", "(",
  "23", "+", "7", ")", "/",
  (", "1", "+", "2", ")"
],
return 2

*/


public class Solution {
    /**
     * @param expression: an array of strings;
     * @return: an integer
     */
    public int evaluateExpression(String[] expression) {
        // write your code here
        if (expression == null || expression.length == 0) {
            return 0;
        }
        
        Deque<Integer> num = new ArrayDeque<>();
        Deque<String> operator = new ArrayDeque<>(); 
        
        for (String token : expression) { //case 1
            if (token.equals("(")) {
                operator.offerLast(token);
            } else if (token.equals(")")) { //case 2
                while (!operator.peekLast().equals("(")) {
                    //poll and calculate with 1 operator and 2 numbers
                    num.offerLast(calculate(operator.pollLast(), num.pollLast(), num.pollLast()));
                }
                operator.pollLast(); //poll out the "("
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) { 
                //operators other than parentheses
                while (!operator.isEmpty() && isLowerThan(token, operator.peekLast())) {
                    num.offerLast(calculate(operator.pollLast(), num.pollLast(), num.pollLast()));
                }
                operator.offerLast(token); //offer current operator
            } else { //case 4 number
                num.offer(Integer.parseInt(token));
            }
        }
        //calculate all numbers left in stack
        while (!operator.isEmpty()) {
            num.offerLast(calculate(operator.pollLast(), num.pollLast(), num.pollLast()));
        }
        return num.isEmpty() ? 0 : num.pollLast();
    }
    
    private int calculate(String operator, int num2, int num1) {
        if (operator.equals("+")) {
            return num1 + num2;
        } else if (operator.equals("-")) {
            return num1 - num2;
        } else if (operator.equals("*")) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }
    
    private boolean isLowerThan(String current, String peek) {
        return ((current.equals("+") || current.equals("-")) && (peek.equals("*") || peek.equals("/")))
                || ((current.equals("*") || current.equals("/")) && (peek.equals("*") || peek.equals("/")))
                || ((current.equals("+") || current.equals("-")) && (peek.equals("+") || peek.equals("-")));
    }
    
};
