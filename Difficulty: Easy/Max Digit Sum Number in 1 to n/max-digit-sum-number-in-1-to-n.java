class Solution {
    public int findMax(int n) {
        char[] s = String.valueOf(n).toCharArray();
        int len = s.length;

        int bestNum = n;
        int maxDigitSum = getDigitSum(n);

        // Try reducing each digit from left to right and setting all subsequent digits to '9'
        for (int i = 0; i < len; i++) {
            if (s[i] == '0') {
                continue;
            }

            char[] cand = s.clone();
            cand[i]--; // Reduce current digit by 1

            // Fill the rest with 9s
            for (int j = i + 1; j < len; j++) {
                cand[j] = '9';
            }

            int candNum = Integer.parseInt(new String(cand));
            int sum = getDigitSum(candNum);

            // Update if we find a strictly greater sum,
            // or equal sum with a larger number
            if (sum > maxDigitSum || (sum == maxDigitSum && candNum > bestNum)) {
                maxDigitSum = sum;
                bestNum = candNum;
            }
        }

        return bestNum;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}