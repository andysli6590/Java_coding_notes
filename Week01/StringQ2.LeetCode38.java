/*
38. Count and Say
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.
*/
public class Solution {
    public String countAndSay(int n) {
        //iterative way
        if (n < 1) return "";
        if (n == 1) return "1";
        StringBuilder current = new StringBuilder("1");
        StringBuilder prev;
        int count = 0;
        char say;
        for (int i = 1; i < n; i++) {
            prev = current;
            current = new StringBuilder();
            count = 1;
            say = prev.charAt(0);
            for (int j = 1; j < prev.length(); j++) {
                if (prev.charAt(j) != say) {
                    current.append(count).append(say);
                    count = 1;
                    say = prev.charAt(j);
                } else count++;
            }
            current.append(count).append(say);
        }
        return current.toString();
    }
}