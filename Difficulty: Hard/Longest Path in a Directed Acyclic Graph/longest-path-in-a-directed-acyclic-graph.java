import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class Solution {
    // Pair class to store adjacent node and weight
    static class Pair {
        int v, w;
        Pair(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public int[] maxDistance(int V, int src, ArrayList<ArrayList<Integer>> edges) {
        // Step 1: Build the adjacency list
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);
            adj.get(u).add(new Pair(v, w));
        }

        // Step 2: Get Topological Sort of the DAG
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topoSort(i, adj, visited, stack);
            }
        }

        // Step 3: Initialize distances with Integer.MIN_VALUE
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[src] = 0;

        // Step 4: Process vertices in Topological Order
        while (!stack.isEmpty()) {
            int u = stack.pop();

            // Relax edges only if the current vertex is reachable
            if (dist[u] != Integer.MIN_VALUE) {
                for (Pair neighbor : adj.get(u)) {
                    int v = neighbor.v;
                    int w = neighbor.w;

                    if (dist[u] + w > dist[v]) {
                        dist[v] = dist[u] + w;
                    }
                }
            }
        }

        return dist;
    }

    private void topoSort(int u, ArrayList<ArrayList<Pair>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        for (Pair neighbor : adj.get(u)) {
            if (!visited[neighbor.v]) {
                topoSort(neighbor.v, adj, visited, stack);
            }
        }
        stack.push(u);
    }
}