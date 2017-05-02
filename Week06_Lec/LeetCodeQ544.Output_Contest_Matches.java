/*
544. Output Contest Matches
The n teams are given in the form of positive integers from 1 to n, which represents their initial rank. 
(Rank 1 is the strongest team and Rank n is the weakest team.) We'll use parentheses('(', ')') and commas(',') 
to represent the contest team pairing - parentheses('(' , ')') for pairing and commas(',') for partition. 
During the pairing process in each round, you always need to follow the strategy of making the rather strong 
one pair with the rather weak one.

Input: 8
Output: (((1,8),(4,5)),((2,7),(3,6)))
Explanation: 
First round: (1,8),(2,7),(3,6),(4,5)
Second round: ((1,8),(4,5)),((2,7),(3,6))
Third round: (((1,8),(4,5)),((2,7),(3,6)))
Since the third round will generate the final winner, 
you need to output the answer (((1,8),(4,5)),((2,7),(3,6))).
Note:
1. The n is in range [2, 2^12].
2. We ensure that the input n can be converted into the form 2k, where k is a positive integer.
*/

public class Solution {
    public String findContestMatch(int n) {
        if (n < 2) {
            return "1";    
        }
        String[] match = new String[n];
        for (int i = 1; i <= n; i++) {
            match[i - 1] = Integer.toString(i); //i + ""
        }
        
        while (n != 1) {
            //each time to deal with half of the result string array
            for (int i = 0; i < n / 2; i++) {
                match[i] = "(" + match[i] + "," + match[n - 1 - i] + ")";
            }
            n /= 2;
        }
        return match[0];
    }
}
