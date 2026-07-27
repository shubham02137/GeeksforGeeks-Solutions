import java.util.*;

class Solution {
    public ArrayList<ArrayList<Integer>> levelSort(int[] arr) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        int n = arr.length;
        int index = 0;
        int levelSize = 1;

        while (index < n) {

            ArrayList<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize && index < n; i++) {
                level.add(arr[index++]);
            }

            Collections.sort(level);
            result.add(level);

            levelSize *= 2;
        }

        return result;
    }
}