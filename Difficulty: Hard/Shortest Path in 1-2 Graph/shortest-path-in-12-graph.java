class Solution {
    public int shortestPath(int V, int src, int dest, int[][] edges) {

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Build undirected graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // {distance, node}
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        dist[src] = 0;
        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            int d = curr[0];
            int u = curr[1];

            if (d != dist[u]) {
                continue;
            }

            if (u == dest) {
                return d;
            }

            for (int[] next : adj.get(u)) {
                int v = next[0];
                int weight = next[1];

                if (dist[v] > d + weight) {
                    dist[v] = d + weight;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        return -1;
    }
}