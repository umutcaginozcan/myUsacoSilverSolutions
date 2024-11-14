package myUsacoSilverSolutions.PS_12_Graph;

import java.util.LinkedList;

public class dfsWithList {
    static LinkedList<Integer>[] graph;
    static boolean[] visited;

    public static void dfs(int node) {
        // Mark the current node as visited and print it
        visited[node] = true;
        System.out.println(node + " is visited.");

        // Recur for all possibilities
        for (int adjacent : graph[node]) {
            if (!visited[adjacent]) {
                dfs(adjacent);
            }
        }
    }

    public static void main(String[] args) {
        graph = new LinkedList[9];
        visited = new boolean[9];
        for (int i = 0; i < 9; i++) {
            graph[i] = new LinkedList<>();
        }

        graph[0].add(1);
        graph[0].add(2);

        graph[1].add(3);
        graph[1].add(4);

        graph[2].add(5);
        graph[2].add(6);

        graph[3].add(7);

        graph[6].add(8);

        dfs(0);
    }
}
