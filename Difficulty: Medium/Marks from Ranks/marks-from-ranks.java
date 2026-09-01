import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> getMarks(int[] l, int[] r, int[] rank) {
        int n = l.length;

        // prefixCounts[i] stores the cumulative count of marks up to the i-th interval
        long[] prefixCounts = new long[n];
        long total = 0;
        for (int i = 0; i < n; i++) {
            total += (long) r[i] - l[i] + 1;
            prefixCounts[i] = total;
        }

        ArrayList<Integer> result = new ArrayList<>(rank.length);

        for (int targetRank : rank) {
            // Binary search to find the interval containing targetRank
            int low = 0, high = n - 1;
            int intervalIdx = n - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixCounts[mid] >= targetRank) {
                    intervalIdx = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            // Calculate marks before this interval
            long prevCount = (intervalIdx > 0) ? prefixCounts[intervalIdx - 1] : 0;

            // The position within the interval (0-indexed)
            long offset = targetRank - prevCount - 1;

            result.add((int) (l[intervalIdx] + offset));
        }

        return result;
    }
}