import java.util.Arrays;

class Solution {
    public int countTriplets(int[] arr, int l, int r) {
        Arrays.sort(arr);
        return countTripletsLessThanOrEqual(arr, r) - countTripletsLessThanOrEqual(arr, l - 1);
    }

    private int countTripletsLessThanOrEqual(int[] arr, int target) {
        int count = 0;
        int n = arr.length;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum <= target) {
                    // All elements from left+1 to right will also satisfy the condition
                    count += (right - left);
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }
}