public class CountingSort {
    private static int[] countingSort(int[] nums) {
        //find min & max
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) max = nums[i];
            if (nums[i] < min) min = nums[i];
        }

        //intialize count
        int range = max - min + 1;
        int[] count = new int[range];
        for (int i = 0; i < range; i++) {
            count[nums[i] - min]++;
        }

        //modify count[] represents ending points
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        //scan from right for results
        int[] result = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            result[--count[nums[i] - min]] = nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {5,4,3,2,1};
        nums = countingSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
