package myUsacoSilverSolutions.PS_12_Graph;

import java.util.LinkedList;
import java.util.Queue;

public class bfsWithList {
    static LinkedList<Integer>[] adjLists;
    static boolean[] visited;

    public static void main(String[] args) {
        adjLists = new LinkedList[9];
        visited = new boolean[9];
        for (int i = 0; i < 9; i++) {
            adjLists[i] = new LinkedList<>();
        }

        adjLists[0].add(1);
        adjLists[0].add(2);

        adjLists[1].add(3);
        adjLists[1].add(4);

        adjLists[2].add(5);
        adjLists[2].add(6);

        adjLists[3].add(7);

        adjLists[6].add(8);

        Queue<Integer> sıraVarKardeşim = new LinkedList<>();
        sıraVarKardeşim.add(0);
        while (!sıraVarKardeşim.isEmpty()) {
            for (int i = 0; i < adjLists[sıraVarKardeşim.peek()].size(); i++) {
                sıraVarKardeşim.add(adjLists[sıraVarKardeşim.peek()].get(i));
            }

            System.out.println(sıraVarKardeşim.poll() + " is visited!");
        }
    }
}
