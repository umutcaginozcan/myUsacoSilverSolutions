import java.io.*;
import java.util.*;

public class deneme {

    static int[] nextState; 
    static int[] countNode;
    static int res;

    public static void dfs(int cur, int cnt) {
        if (countNode[cur] != 0)
            return;
        
        countNode[cur] = cnt;
        int next = nextState[cur];

        if (countNode[next] != 0 && countNode[cur] <= countNode[next] && next != cur)
            return;

        if (next == cur) {
            res++;
            return;
        }

        if (countNode[next] < countNode[cur] && countNode[next] != 0) {
            res += countNode[cur] - countNode[next] + 1;
            return;
        }
        dfs(next, cnt + 1);
    }

    public static void main(String[] args) throws IOException {
        // Use System.in and System.out for input and output
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        nextState = new int[N + 1];
        countNode = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nextState[i + 1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            dfs(i, 1);
        }

        pw.println(res);
        pw.flush();  // Make sure to flush PrintWriter to output everything
    }
}
