package DisasterSystem;
import java.util.*;

public class Graph {
    private Map<String, List<Map.Entry<String, Integer>>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addEdge(String u, String v, int weight) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(new AbstractMap.SimpleEntry<>(v, weight));
        adjList.get(v).add(new AbstractMap.SimpleEntry<>(u, weight)); // For undirected graph
    }

    public void BFS(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        visited.add(start);
        q.offer(start);

        while (!q.isEmpty()) {
            String node = q.poll();
            System.out.print(node + " ");
            for (Map.Entry<String, Integer> neighbor : adjList.get(node)) {
                if (!visited.contains(neighbor.getKey())) {
                    visited.add(neighbor.getKey());
                    q.offer(neighbor.getKey());
                }
            }
        }
        System.out.println();
    }

    public void DFS(String start) {
        Set<String> visited = new HashSet<>();
        Stack<String> s = new Stack<>();
        s.push(start);

        while (!s.isEmpty()) {
            String node = s.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                System.out.print(node + " ");
                for (Map.Entry<String, Integer> neighbor : adjList.get(node)) {
                    if (!visited.contains(neighbor.getKey())) {
                        s.push(neighbor.getKey());
                    }
                }
            }
        }
        System.out.println();
    }

    public List<String> dijkstra(String source, String target) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue));

        for (String node : adjList.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(source, 0);
        pq.offer(new AbstractMap.SimpleEntry<>(source, 0));

        while (!pq.isEmpty()) {
            String node = pq.poll().getKey();
            if (visited.contains(node))
                continue;
            visited.add(node);

            for (Map.Entry<String, Integer> neighbor : adjList.get(node)) {
                String neighborNode = neighbor.getKey();
                int weight = neighbor.getValue();

                if (dist.get(node) + weight < dist.get(neighborNode)) {
                    dist.put(neighborNode, dist.get(node) + weight);
                    parent.put(neighborNode, node);
                    pq.offer(new AbstractMap.SimpleEntry<>(neighborNode, dist.get(neighborNode)));
                }
            }
        }

        List<String> path = new ArrayList<>();
        String node = target;
        while (parent.containsKey(node)) {
            path.add(node);
            node = parent.get(node);
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }

    public List<String> primMST() {
        Set<String> visited = new HashSet<>();
        PriorityQueue<Map.Entry<Integer, Map.Entry<String, String>>> pq = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getKey));
        List<String> mst = new ArrayList<>();

        for (String node : adjList.keySet()) {
            if (!visited.contains(node)) {
                visited.add(node);
                for (Map.Entry<String, Integer> neighbor : adjList.get(node)) {
                    pq.offer(new AbstractMap.SimpleEntry<>(neighbor.getValue(),
                            new AbstractMap.SimpleEntry<>(node, neighbor.getKey())));
                }

                while (!pq.isEmpty()) {
                    Map.Entry<Integer, Map.Entry<String, String>> edge = pq.poll();
                    String u = edge.getValue().getKey();
                    String v = edge.getValue().getValue();
                    int weight = edge.getKey();

                    if (!visited.contains(v)) {
                        visited.add(v);
                        mst.add(u + "-" + v);
                        for (Map.Entry<String, Integer> neighbor : adjList.get(v)) {
                            if (!visited.contains(neighbor.getKey())) {
                                pq.offer(new AbstractMap.SimpleEntry<>(neighbor.getValue(),
                                        new AbstractMap.SimpleEntry<>(v, neighbor.getKey())));
                            }
                        }
                    }
                }
            }
        }
        return mst;
    }
}
