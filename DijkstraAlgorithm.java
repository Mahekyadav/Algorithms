import java.util.*;

class Edge {
    char target;
    int weight;

    Edge(char target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

public class DijkstraAlgorithm {
    public static void findShortestPath(Map<Character, List<Edge>> graph, char source) {
        // Stores the shortest distance from source to each vertex
        Map<Character, Integer> distances = new HashMap<>();
        // To reconstruct the path (Shortest Path Tree)
        Map<Character, Character> parent = new HashMap<>();
        // Priority Queue to pick the vertex with the minimum distance
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));

        // Initialize distances as infinity
        for (char vertex : graph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        
        distances.put(source, 0);
        pq.add(new Node(source, 0));

        System.out.println("Iteration | Selected | Distances");
        System.out.println("---------------------------------");

        int iteration = 1;
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            char u = current.vertex;

            System.out.printf("   %d     |    %c     | %s\n", iteration++, u, formatDistances(distances));

            // Explore neighbors
            for (Edge edge : graph.get(u)) {
                int newDist = distances.get(u) + edge.weight;
                
                // Relaxation step
                if (newDist < distances.get(edge.target)) {
                    distances.put(edge.target, newDist);
                    parent.put(edge.target, u);
                    pq.add(new Node(edge.target, newDist));
                }
            }
        }

        printFinalResult(source, distances, parent);
    }

    // Helper class for Priority Queue
    static class Node {
        char vertex;
        int distance;
        Node(char vertex, int distance) { this.vertex = vertex; this.distance = distance; }
    }

    public static void main(String[] args) {
        Map<Character, List<Edge>> graph = new HashMap<>();
        graph.put('A', Arrays.asList(new Edge('B', 6), new Edge('D', 1)));
        graph.put('B', Arrays.asList(new Edge('E', 2), new Edge('C', 5)));
        graph.put('C', new ArrayList<>());
        graph.put('D', Arrays.asList(new Edge('B', 2), new Edge('E', 1)));
        graph.put('E', Arrays.asList(new Edge('C', 5)));

        findShortestPath(graph, 'A');
    }

    private static String formatDistances(Map<Character, Integer> dist) {
        return dist.toString().replace(String.valueOf(Integer.MAX_VALUE), "INF");
    }

    private static void printFinalResult(char source, Map<Character, Integer> dist, Map<Character, Character> parent) {
        System.out.println("\nShortest Distance from " + source + " to C: " + dist.get('C'));
        System.out.print("Shortest Path Tree Edges: ");
        parent.forEach((child, par) -> System.out.print(par + "->" + child + " "));
        System.out.println();
    }
}