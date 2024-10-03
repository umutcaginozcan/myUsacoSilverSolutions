import java.util.Scanner;

public class usaco2012jan {

    private static int[][] A = new int[5][5]; // The field
    private static int K; // Number of squares we stepped on.

    public static int count(int i, int j) {
        if (i < 0 || i > 4 || j < 0 || j > 4 || A[i][j] != 0) 
            return 0;
        if (i == 4 && j == 4 && K != 25)
            return 0;
        A[i][j] = 1;
        K++;

        print();

        int c;
        if (K == 25 && i == 4 && j == 4) 
            c = 1;
        else
            c = count(i - 1, j) + count(i + 1, j) + count(i, j - 1) + count(i, j + 1);
        A[i][j] = 0;
        K--;
        return c;
    }

    public static void print() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(A[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        for (int idx = 0; idx < k; idx++) {
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            A[i - 1][j - 1] = 1; // Marking barren squares
        }

        K += k;
        System.out.println(count(0, 0));
        scanner.close();
    }
}
