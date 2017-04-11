/*
Merge Sort 
Given an integer array, sort it in ascending order. Use quick sort, merge sort, heap sort or any O(nlogn) algorithm.
Given [3, 2, 1, 4, 5], return [1, 2, 3, 4, 5].
*/


public class Solution {
    /**
     * @param A an integer array
     * @return void
     */
     
    time complexity: O(nlogn), space complexity: O(n) + O(logn) stack frame 
    public void sortIntegers2(int[] A) {
        //merge sort
        int[] temp = new int[A.length]; //extra space created
        sortIntegers2Helper(A, temp, 0, A.length - 1);
    }
    
    private void sortIntegers2Helper(int[] A, int[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sortIntegers2Helper(A, temp, left, mid);
        sortIntegers2Helper(A, temp, mid + 1, right);
        merge(A, temp, left, right);
    }
    
    private void merge(int[] A, int[] temp, int left, int right) {
        int mid = left + (right - left) / 2;
        int i = left;
        int j = mid + 1;
        
        for (int k = 0; k < right - left + 1; k++) {
            if (i <= mid && (j > right || A[i] <= A[j])) {
                temp[k] = A[i++];
            } else {
                temp[k] = A[j++];
            }
        }
        for (int k = 0; k < right - left + 1; k ++) {
            A[left + k] = temp[k];
        }
    }
}
