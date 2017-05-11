/*
54. Spiral Matrix
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
*/

public class Solution {
    //time: O(n^2), space: O(1), recursion stack space: O(n/2)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return result;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        helper(result, matrix, row, col, 0);
        return result;
    }
    
    private void helper(List<Integer> result, int[][] matrix, int row, int col, int offset) {
        //base cases
        if (row == 0 || col == 0) { //nothing left
            return;
        }
        if (row == 1) { //one row left
            for (int i = offset; i < col + offset; i++) {
                result.add(matrix[offset][i]);
            }
            return;
        }
        if (col == 1) { //one column left
            for (int i = offset; i < row + offset; i++) {
                result.add(matrix[i][offset]);
            }
            return;
        }
        
        //current layer
        //1.up row
        for (int i = offset; i < col - 1 + offset; i++) {
            result.add(matrix[offset][i]);
        }
        //2. right col
        for (int i = offset; i < row - 1 + offset; i++) {
            result.add(matrix[i][col - 1 + offset]);
        }
        //3. down row
        for (int i = col - 1 + offset; i > offset; i--) {
            result.add(matrix[row - 1 + offset][i]);
        }
        //4. left column
        for (int i = row - 1 + offset; i > offset; i--) {
            result.add(matrix[i][offset]);
        }
        
        //next layer
        helper(result, matrix, row - 2, col - 2, offset + 1);
    }
}
