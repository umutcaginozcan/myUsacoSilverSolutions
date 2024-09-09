// package myUsacoSilverSolutions.PS_5_DataStructures;

import java.io.*;
import java.util.*;
public class usaco2017jan {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		
        StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int maxT = Integer.parseInt(st.nextToken());
		int[] times = new int[n];
		for (int i = 0; i < n; i++) {
			times[i] = Integer.parseInt(br.readLine());
		}

        /* 
         * Binary Search the answer.
         */
		int min = 1;
		int max = n;
		while (min != max) {
			int mid = (min+max)/2;
			if (possible(times, mid, maxT)) {
				max = mid;
			}
			else {
				min = mid + 1;
			}
		}
		pw.println(min);
		pw.close();
	}
	
    /* 
     * Simulate the dance using a priority queue.
     */
	public static boolean possible(int[] times, int k, int t) {
		int lastTime = 0;
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for (int i = 0; i < times.length; i++) {
			if (q.size() == k) {
				lastTime = q.poll();
			}
			if (lastTime + times[i] > t) {
				return false;
			}
			q.add(lastTime + times[i]);
		}
		return true;
	}
}	