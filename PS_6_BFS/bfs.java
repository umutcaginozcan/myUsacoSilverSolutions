package myUsacoSilverSolutions.PS_6_BFS;

import java.util.*;

public class bfs {

    // Define the Graph class
    static class Graph {
        private int vertices; // Number of vertices
        private LinkedList<Integer>[] adjacencyList; // Adjacency List representation

        // Constructor
        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        // Add an edge to the graph
        public void addEdge(int source, int destination) {
            adjacencyList[source].add(destination);
        }

        // BFS with distance calculation
        public void BFS(int startVertex) {
            // Mark all vertices as not visited and set distances to -1 (unreachable)
            boolean[] visited = new boolean[vertices];
            int[] distance = new int[vertices];
            Arrays.fill(distance, -1); // Initialize distances to -1

            // Create a queue for BFS
            Queue<Integer> queue = new LinkedList<>();

            // Initialize starting vertex
            visited[startVertex] = true;
            distance[startVertex] = 0;
            queue.add(startVertex);

            while (!queue.isEmpty()) {
                int vertex = queue.poll();

                // Process the node (in this case, we're just printing it)
                System.out.println("Vertex: " + vertex + " Distance from start: " + distance[vertex]);

                // Explore adjacent vertices
                for (int adj : adjacencyList[vertex]) {
                    if (!visited[adj]) {
                        visited[adj] = true;
                        distance[adj] = distance[vertex] + 1; // Increment distance
                        queue.add(adj);
                    }
                }
            }

            // Optionally, print the distances from the start vertex to all other vertices
            System.out.println("Distances from vertex " + startVertex + ": " + Arrays.toString(distance));
        }
    }

    // Main method to run the BFS
    public static void main(String[] args) {
        // Create a graph with 5 vertices
        Graph graph = new Graph(5);

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);

        // Perform BFS traversal from vertex 0
        System.out.println("BFS starting from vertex 0:");
        graph.BFS(0);
    }
}
