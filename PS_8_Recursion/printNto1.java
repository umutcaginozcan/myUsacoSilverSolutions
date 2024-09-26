package myUsacoSilverSolutions.PS_8_Recursion;

public class printNto1 {
    public static void main(String[] args) {
        int N = 5;
        go(N);
    }

    public static void go(int n) {
        if (n > 0) {
            go(n - 1);
            System.out.println(n);
        }
    }
}
