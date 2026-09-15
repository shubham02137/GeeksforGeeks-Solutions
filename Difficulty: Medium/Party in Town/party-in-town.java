import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int partyHouse(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        if (n <= 1) return 0;

        // Step 1: Find the farthest node from arbitrary node 1
        int[] first = bfs(1, n, adj);

        // Step 2: Find the farthest node from the first farthest node
        int[] second = bfs(first[0], n, adj);

        int diameter = second[1];

        // The minimum of the maximum distances is the radius: ceil(diameter / 2)
        return (diameter + 1) / 2;
    }

    // Returns int[]{farthestNode, maxDistance}
    private int[] bfs(int start, int n, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; i++) dist[i] = -1;

        queue.offer(start);
        dist[start] = 0;

        int farthestNode = start;
        int maxDist = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (dist[curr] > maxDist) {
                maxDist = dist[curr];
                farthestNode = curr;
            }

            // adj is 0-indexed where index i corresponds to house i + 1
            for (int neighbor : adj.get(curr - 1)) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[curr] + 1;
                    queue.offer(neighbor);
                }
            }
        }

        return new int[]{farthestNode, maxDist};
    }
}