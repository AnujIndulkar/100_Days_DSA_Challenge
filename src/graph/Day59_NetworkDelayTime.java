package graph;

import java.util.*;

public class Day59_NetworkDelayTime {

    // 1. BRUTE FORCE (BELLMAN FORD)
    // Time Complexity: O(V * E)
    // Space Complexity: O(V)

    public int networkDelayTimeBrute(int[][] times, int n, int k) {

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[k] = 0;

        // Relax all edges (n - 1) times
        for (int i = 1; i < n; i++) {

            for (int[] edge : times) {

                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] != Integer.MAX_VALUE &&
                        dist[u] + w < dist[v]) {

                    dist[v] = dist[u] + w;
                }
            }
        }

        int maxTime = 0;

        for (int i = 1; i <= n; i++) {

            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }

            maxTime = Math.max(maxTime, dist[i]);
        }

        return maxTime;
    }

    // 2. OPTIMAL (DIJKSTRA + MIN HEAP)
    // Time Complexity: O(E log V)
    // Space Complexity: O(V + E)

    public int networkDelayTimeOptimal(int[][] times, int n, int k) {

        Map<Integer, List<int[]>> graph = new HashMap<>();

        // Build graph
        for (int[] edge : times) {

            graph.putIfAbsent(edge[0], new ArrayList<>());

            graph.get(edge[0]).add(
                    new int[]{edge[1], edge[2]}
            );
        }

        // Min Heap -> {time, node}
        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>((a, b) -> a[0] - b[0]);

        minHeap.offer(new int[]{0, k});

        boolean[] visited = new boolean[n + 1];

        int delayTime = 0;
        int visitedCount = 0;

        while (!minHeap.isEmpty()) {

            int[] current = minHeap.poll();

            int time = current[0];
            int node = current[1];

            if (visited[node]) {
                continue;
            }

            visited[node] = true;
            visitedCount++;

            delayTime = Math.max(delayTime, time);

            if (graph.containsKey(node)) {

                for (int[] neighbor : graph.get(node)) {

                    int nextNode = neighbor[0];
                    int nextTime = neighbor[1];

                    if (!visited[nextNode]) {

                        minHeap.offer(
                                new int[]{time + nextTime, nextNode}
                        );
                    }
                }
            }
        }

        return visitedCount == n ? delayTime : -1;
    }

    public static void main(String[] args) {

        Day59_NetworkDelayTime obj =
                new Day59_NetworkDelayTime();

        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };

        int n = 4;
        int k = 2;

        System.out.println("Brute Force: " +
                obj.networkDelayTimeBrute(times, n, k));

        System.out.println("Optimal: " +
                obj.networkDelayTimeOptimal(times, n, k));
    }
}
