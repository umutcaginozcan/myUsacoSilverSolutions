import java.util.*;

public class CostumeParty {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int L = in.nextInt();

        int[] cowSizes = new int[N];
        for (int i = 0; i < N; i++)
            cowSizes[i] = in.nextInt();

        Arrays.sort(cowSizes);

        int res = 0, j = N - 1, i = j - 1;
        while (i >= 0) {
            if (cowSizes[i] + cowSizes[j] <= L) {
                res += i + 1;
                j--;
                i = j - 1;
            } else {
                i--;
            }
        }

        System.out.println(res);
    }
}
