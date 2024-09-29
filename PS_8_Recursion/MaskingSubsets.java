package myUsacoSilverSolutions.PS_8_Recursion;

public class MaskingSubsets {
    
    final static int[] set = {1, 2, 3};
    final static int N = set.length;
    final static int[] mask = new int[N];
    static int nrOnes = 0;

    public static void main(String[] args) {
        go(0);
    }

    public static void go(int i) {
        if (i >= N) {
            if (nrOnes > 0)
                printOneSubset(); 
        } else {
            mask[i] = 0;
            go(i + 1);

            mask[i] = 1;
            nrOnes++;
            go(i + 1);
            nrOnes--;
        }
    }

    public static void printOneSubset() {

        for (int i = 0; i < N; i++) {
            if (mask[i] != 0) {
                System.out.print(set[i] + " ");
            }
        }
        System.out.println();
    }
}
