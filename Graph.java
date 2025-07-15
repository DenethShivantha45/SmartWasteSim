import java.util.*;

public class Graph {
    private Map<String, Map<String, Integer>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addEdge(String from, String to, int distance) {
        adjList.computeIfAbsent(from, k -> new HashMap<>()).put(to, distance);
        adjList.computeIfAbsent(to, k -> new HashMap<>()).put(from, distance); // Undirected graph
    }

    public List<String> findShortestPath(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String[]> pq = new PriorityQueue<>((a, b) -> Integer.parseInt(a[1]) - Integer.parseInt(b[1]));
        Set<String> visited = new HashSet<>();

        for (String vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.offer(new String[]{start, "0"});

        while (!pq.isEmpty()) {
            String[] current = pq.poll();
            String currentVertex = current[0];
            if (visited.contains(currentVertex)) continue;
            visited.add(currentVertex);

            if (currentVertex.equals(end)) break;

            Map<String, Integer> neighbors = adjList.getOrDefault(currentVertex, new HashMap<>());
            for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                String nextVertex = neighbor.getKey();
                int weight = neighbor.getValue();
                int newDist = distances.get(currentVertex) + weight;

                if (newDist < distances.get(nextVertex)) {
                    distances.put(nextVertex, newDist);
                    previous.put(nextVertex, currentVertex);
                    pq.offer(new String[]{nextVertex, String.valueOf(newDist)});
                }
            }
        }

        List<String> path = new ArrayList<>();
        String current = end;
        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}