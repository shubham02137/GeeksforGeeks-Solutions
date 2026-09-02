class Solution {
    public int solve(int n, String s) {
        // Status tracking for 'A' - 'Z':
        // 0: Not seen yet
        // 1: Currently using a computer
        // 2: Turned away (no computer was available)
        int[] status = new int[26];

        int occupied = 0;
        int rejectedCount = 0;

        for (int i = 0; i < s.length(); i++) {
            int customer = s.charAt(i) - 'A';

            if (status[customer] == 0) {
                // First occurrence: Arrival
                if (occupied < n) {
                    occupied++;
                    status[customer] = 1; // Assigned a computer
                } else {
                    status[customer] = 2; // Rejected
                    rejectedCount++;
                }
            } else if (status[customer] == 1) {
                // Second occurrence of a customer using a computer: Departure
                occupied--;
                status[customer] = 0;
            } 
            // If status is 2, the rejected customer is simply leaving
        }

        return rejectedCount;
    }
}