import java.util.Arrays;

class Solution {
    int countPairs(int[] arr, int k) {
        Arrays.sort(arr);

        int left = 0;
        int count = 0;

        for (int right = 0; right < arr.length; right++) {
            while (arr[right] - arr[left] >= k) {
                left++;
            }

            count += (right - left);
        }

        return count;
    }
}