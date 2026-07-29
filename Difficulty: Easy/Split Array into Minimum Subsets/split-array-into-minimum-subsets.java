class Solution {
    public int minSubsets(int[] arr) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : arr) {
            set.add(num);
        }

        int count = 0;

        for (int num : arr) {
            // If previous consecutive number doesn't exist,
            // num starts a new subset
            if (!set.contains(num - 1)) {
                count++;
            }
        }

        return count;
    }
}