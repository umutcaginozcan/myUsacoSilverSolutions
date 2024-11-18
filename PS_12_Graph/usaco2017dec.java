import java.io.*;
import java.util.*;

public class usaco2017dec {

    static int[] nextState;
    static int[] countNode;
    static int res;

    public static void dfs(int cur, int cnt) {
        
        if (countNode[cur] != 0) 
            return;
        
        countNode[cur] = cnt;

        int next = nextState[cur];
        // There is already an accumulation where cur points at. (To save time)
        if (countNode[next] != 0 && countNode[cur] <= countNode[next] && next != cur)
            return;

        if (next == cur) {
            res++;
            return;
        }
        // Cycle detected, increment res.
        if (countNode[next] < countNode[cur] && countNode[next] != 0) {
            res += countNode[cur] - countNode[next] + 1;
            return;
        }
        dfs(next, cnt + 1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        nextState = new int[N + 1];
        countNode = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nextState[i + 1] = Integer.parseInt(st.nextToken());
        }

        // For each node, implement dfs.
        for (int i = 1; i <= N; i++) {
            dfs(i, 1);
        }

        pw.println(res);
        pw.close();
    }
}