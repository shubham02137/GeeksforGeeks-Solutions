import java.util.Arrays;

class Solution {
    public int maxProduct(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);

        // Case 1: If all elements are negative and k is odd,
        // picking anything will result in a negative product,
        // so we pick the k elements with the smallest absolute values (end of array).
        if (arr[n - 1] < 0 && (k % 2 != 0)) {
            int prod = 1;
            for (int i = n - 1; i >= n - k; i--) {
                prod *= arr[i];
            }
            return prod;
        }

        int left = 0;
        int right = n - 1;
        int prod = 1;

        // If k is odd, include the largest available positive/non-negative element first
        if (k % 2 != 0) {
            prod *= arr[right];
            right--;
            k--;
        }

        // Pick pairs from either the left (two negative numbers) 
        // or the right (two positive numbers) based on which product is larger
        while (k > 0) {
            int leftProduct = arr[left] * arr[left + 1];
            int rightProduct = arr[right] * arr[right - 1];

            if (leftProduct > rightProduct) {
                prod *= leftProduct;
                left += 2;
            } else {
                prod *= rightProduct;
                right -= 2;
            }
            k -= 2;
        }

        return prod;
    }
}