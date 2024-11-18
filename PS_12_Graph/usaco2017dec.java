import java.io.*;
import java.util.*;

public class usaco2017dec {

    static int[] nextState;
    static int[] countNode;
    static int res;
    static int cnt;

    public static void dfs(int cur, int flag) {
        
        if (flag == 1) {
            if (countNode[cur] > 0) {
                countNode[cur] = -1;
                dfs(nextState[cur], 1);
            }
        }
        else {
            countNode[cur] = cnt;
            cnt++;
    
            int next = nextState[cur];
            if (countNode[next] == 0)
                dfs(next, 0);
    
            else if (countNode[next] > 0) 
                res += countNode[cur] - countNode[next] + 1;
            
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        nextState = new int[N + 1];
        countNode = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nextState[i] = Integer.parseInt(st.nextToken());
        }

        // For each node, implement dfs.
        for (int i = 1; i <= N; i++) {
            if (countNode[i] == 0) {
                cnt = 1;
                dfs(i, 0);
                dfs(i, 1);
            }
        }

        pw.println(res);
        pw.close();
    }
}