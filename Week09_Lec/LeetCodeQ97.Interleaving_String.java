/*
97. Interleaving String
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
*/

public class Solution {
    //memorized search + dfs solution
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        int[][] valid = new int[s1.length() + 1][s2.length() + 1]; //初始值都是0
        return dfsHelper(c1, c2, c3, 0, 0, 0, valid);
    }
    //valid = 0: not visited, valid = 1: visited true, valid = -1, visited false
    //can not use boolean[] directly to distinguish default false or checked false.
    private boolean dfsHelper(char[] c1, char[] c2, char[] c3, int i, int j, int k, int[][] valid) {
        if (valid[i][j] != 0) {
            return valid[i][j] == 1;
        }
        if (k == c3.length) {
            valid[i][j] = 1;
            return true;
        }
        
        valid[i][j] = (i < c1.length && c1[i] == c3[k] && dfsHelper(c1, c2, c3, i + 1, j, k + 1, valid) 
                       || j < c2.length && c2[j] == c3[k] && dfsHelper(c1, c2, c3, i, j + 1, k + 1, valid)) ? 1 : -1;
        return valid[i][j] == 1;
    }
    
    /****************************************************************************************************************************/
    //dp implementation
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null) {
            return false;
        } 
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[][] matrix = new boolean[s1.length() + 1][s2.length() + 1];
        matrix[0][0] = true;
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] = matrix[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int i = 1; i < matrix[0].length; i++) {
            matrix[0][i] = matrix[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }
        
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] = (matrix[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1))) 
                    || (matrix[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i + j - 1)));
            }
        }
        return matrix[s1.length()][s2.length()];
    }
}
