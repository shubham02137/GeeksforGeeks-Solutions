class Solution {
    public int maxSubsetXOR(int[] arr) {

        int n = arr.length;
        int index = 0;

        // Process bits from MSB to LSB
        for (int bit = 31; bit >= 0; bit--) {

            int maxIndex = -1;

            // Find element with current bit set
            for (int i = index; i < n; i++) {
                if (((arr[i] >> bit) & 1) == 1) {
                    maxIndex = i;
                    break;
                }
            }

            if (maxIndex == -1) {
                continue;
            }

            // Bring pivot to current position
            int temp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;

            // Eliminate current bit from all other numbers
            for (int i = 0; i < n; i++) {
                if (i != index && ((arr[i] >> bit) & 1) == 1) {
                    arr[i] ^= arr[index];
                }
            }

            index++;
        }

        int ans = 0;

        // Build maximum XOR
        for (int num : arr) {
            ans = Math.max(ans, ans ^ num);
        }

        return ans;
    }
}