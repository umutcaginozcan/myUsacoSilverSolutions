package myUsacoSilverSolutions.PS_6_BFS;

import java.util.*;

class Graph {
    private int vertices; 
    private LinkedList<Integer>[] adjacencyList; 

    // Constructor
    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices]; 
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
        /* 
         * Ver-1: 0 -> 1 -> 2 -> (Neighbours)
         * Ver-2: 5 ->
         * ...
         */
    }

    public void addEdge(int source, int destination) {
        adjacencyList[source].add(destination);
    }

    // DFS starting from a given vertex
    public void DFS(int startVertex) {
        // Mark all vertices as not visited
        boolean[] visited = new boolean[vertices];

        // Call recursive helper function to print DFS traversal
        DFSUtil(startVertex, visited);
    }

    // Recursive function for DFS traversal
    private void DFSUtil(int vertex, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[vertex] = true;
        System.out.println(vertex + " is visited.");

        // Recur for all vertices adjacent to this vertex
        for (int adj : adjacencyList[vertex]) {
            if (!visited[adj]) {
                DFSUtil(adj, visited);
            }
        }
    }

    public static void main(String[] args) {
        // Create a graph with 5 vertices
        Graph graph = new Graph(5);

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(3, 4);

        // Perform DFS traversal from vertex 0
        System.out.println("DFS starting from vertex 0:");
        graph.DFS(0);
    }
}

/*
Graph:
    0 . 1  .  3
    .    .  .
    2     4 

Adjacency Linked-List:
0: -> 1 -> 2 -> 
1: -> 3 -> 4 ->
2: ->
3: -> 4 ->
4: ->

 */