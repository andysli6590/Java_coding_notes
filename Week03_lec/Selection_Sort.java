public class Solution {
    /**
     * @param A an integer array
     * @return void
     */
    public void sortIntegers(int[] A) {
        //selection sort
        if (A == null || A.length <= 1) return;
        int n = A.length;
        int minValueIndex = 0;
        
        for (int i = 0; i < n; i++) {
            minValueIndex = i;
            
            for (int j = i+1; j < n; j ++) {
                if (A[j] < A[minValueIndex]) minValueIndex = j;
            }
            
            //swap A[j] and A[minValueIndex]
            int temp = A[i];
            A[i] = A[minValueIndex];
            A[minValueIndex] = temp;
        }
        
    }
}
