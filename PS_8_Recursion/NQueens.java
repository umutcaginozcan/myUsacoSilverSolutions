package myUsacoSilverSolutions.PS_8_Recursion;

public class NQueens {
    final static int N = 14;
    static int[] X = new int[N];
    static int count = 0;

    public static void main(String[] args) {
        go(0);
        System.out.println(count);
    }

    static void go(int row) {
        if (row >= N) {
            // printSolution();
            count++;
        } else {
            for (int col = 0; col < N; col++) {
                X[row] = col;
                if (isValid(row))
                    go(row + 1);
            }
            
        }
    }

    static boolean isValid(int i) {
        
        for (int j = 0; j < i; j++) {
            if (X[i] == X[j])
                return false;
            if (Math.abs(X[i] - X[j]) == Math.abs(i - j))
                return false;
        }
        
        return true;
    }

    static void printSolution() {

        // char[][] grid = new char[N][N];
        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < N; j++) {
        //         grid[i][j] = '.';
        //     }
        // }
        
        // for (int i = 0; i < N; i++) {
        //     grid[i][X[i]] = 'Q';
        // }

        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < N; j++) {
        //         System.out.print(grid[i][j]);
        //     }
        //     System.out.println();
        // }
        // System.out.println();
    }
}
