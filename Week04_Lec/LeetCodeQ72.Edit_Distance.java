/*
72. Edit Distance
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. 
(each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/

public class Solution {
    
    //memorized search
    public int minDistance(String word1, String word2) {
        int[][] count = new int[word1.length() + 1][word2.length() + 1]; //need to open the array count "" as the first
        Arrays.fill(count, -1);
        count[0][0] = 0;
        return helper(word1, 0, word2, 0, count);
    }
    
    private int helper(String word1, int i, String word2, int j, int[][] count) {
        //base case
        if (i == word1.length()) return word2.length() - j;
        if (j == word2.length()) return word1.length() - i;
        if (count[i][j] != -1) return count[i][j]; //use cached result;
        
        int result;
        if (word1.charAt(i) == word2.charAt(j)) {
             result = helper(word1, i + 1, word2, j + 1, count);
        } else {
            //insert
            int insert = helper(word1, i, word2, j + 1, count);
            //delete
            int delete = helper(word1, i + 1, word2, j, count);
            //replace
            int replace = helper(word1, i + 1, word2, j + 1, count);
            result = Math.min(insert, Math.min(delete, replace)) + 1;
        }
        count[i][j] = result;
        return result;
    }
}
