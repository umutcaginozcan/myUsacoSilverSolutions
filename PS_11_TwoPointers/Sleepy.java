// package myUsacoSilverSolutions.PS_11_TwoPointers;

import java.io.*;
import java.util.Arrays;

public class Sleepy {
    static int N;
    static int[] A;

    static int solveMin() {
        // Exceptions
        if (A[N - 2] - A[0] == N - 2 && A[N - 1] - A[N - 2] > 2) return 2;
        if (A[N - 1] - A[1] == N - 2 && A[1] - A[0] > 2) return 2;

        int j = 0, best = 0;
        for (int i = 0; i < N; i++) {
            while (j < N - 1 && A[j + 1] - A[i] <= N - 1) j++; // J counts the number of cows that doesn't need to move.
            best = Math.max(best, j - i + 1);
        }
        return N - best;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader fin = new BufferedReader(new FileReader("herding.in"));
        N = Integer.parseInt(fin.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(fin.readLine());
        }
        Arrays.sort(A);

        BufferedWriter fout = new BufferedWriter(new FileWriter("herding.out"));
        int answerMin = solveMin();
        int answerMax = A[N - 1] - A[0] - (N - 1) - Math.min(A[N - 1] - A[N - 2], A[1] - A[0]) + 1;
        fout.write(answerMin + "\n" + answerMax + "\n");
        fout.close();
    }
}

