import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean isPossible(int[] arr, int s, int x) {
        List<Long> seq = new ArrayList<>();
        
        // Add initial value 's' to sequence
        seq.add((long) s);
        long currentSum = s;
        
        // Generate the sequence until nextVal > x or array ends
        for (int a : arr) {
            long nextVal = currentSum + a;
            seq.add(nextVal);
            currentSum += nextVal;
            
            // Stop generating if the term exceeds target x
            if (nextVal > x) {
                break;
            }
        }
        
        // Greedy choice from largest to smallest element
        long target = x;
        for (int i = seq.size() - 1; i >= 0; i--) {
            if (target >= seq.get(i)) {
                target -= seq.get(i);
            }
        }
        
        return target == 0;
    }
}