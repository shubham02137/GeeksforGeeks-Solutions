class Solution {
    public String compress(String s) {
        int n = s.length();
        int[] z = calculateZ(s);

        StringBuilder sb = new StringBuilder();
        int i = n;

        while (i > 0) {
            // Check if the current prefix of length i can be halved
            if (i % 2 == 0 && z[i / 2] >= i / 2) {
                sb.append('*');
                i /= 2;
            } else {
                sb.append(s.charAt(i - 1));
                i--;
            }
        }

        return sb.reverse().toString();
    }

    // Standard Z-algorithm to find longest common prefixes in O(N)
    private int[] calculateZ(String s) {
        int n = s.length();
        int[] z = new int[n];
        int l = 0, r = 0;

        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }
}