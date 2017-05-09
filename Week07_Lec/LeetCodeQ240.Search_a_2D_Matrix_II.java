/*
240. Search a 2D Matrix II
Write an efficient algorithm that searches for a value in an m x n matrix. 
This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
*/


public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) return false;
        return helper(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1, target);    
    }
    
    private boolean helper(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd, int target) {
        if (rowStart > rowEnd || colStart > colEnd) return false;
        int rowMid = rowStart + (rowEnd - rowStart);
        int colMid = colStart + (colEnd - colStart);
        if (matrix[rowMid][colMid] == target) return true;
        else if (matrix[rowMid][colMid] > target) 
            return helper(matrix, rowStart, rowMid - 1, colStart, colMid - 1, target) ||
                    helper(matrix, rowMid, rowEnd, colStart, colMid - 1, target) ||
                    helper(matrix, rowStart, rowMid - 1, colMid, colEnd, target);
        else return helper(matrix, rowStart, rowMid, colMid + 1, colEnd, target) ||
                        helper(matrix, rowMid + 1, rowEnd, colStart, colMid, target) ||
                        helper(matrix, rowMid + 1, rowEnd, colMid + 1, colEnd, target);
    }
  
    /*****************************************************************************************************************/
    public boolean searchMatrix(int[][] matrix, int target) {
        //corner case checked
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        return binarySearch(matrix, target, 0, 0, m - 1, n - 1);
    }
    
    private boolean binarySearch(int[][] matrix, int target, int startX, int startY, int endX, int endY) {
        if (startX > endX || startY > endY) {
            return false;
        }
        int midX = startX + (endX - startX) / 2;
        int midY = startY + (endY - startY) / 2;
        if (matrix[midX][midY] == target) { //case 1: found
            return true;
        } else if (matrix[midX][midY] > target) { //case 2: larger than target, go into left or up submatrix
            return binarySearch(matrix, target, startX, startY, endX, midY - 1)
                || binarySearch(matrix, target, startX, midY, midX - 1, endY);
        } else { //case 3 : smaller than target, go into right or down submatrix
            return binarySearch(matrix, target, startX, midY + 1, endX, endY) 
                || binarySearch(matrix, target, midX + 1, startY, endX, midY);
        }
    }
}
