import java.io.*;
import java.util.*;

public class usaco2017feb2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
 
        ArrayList<Integer> chickens = new ArrayList<>();
  
        for (int i = 0; i < C; i++) {
            chickens.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(chickens);
        
        ArrayList<int[]> cows = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            cows.add(new int[]{x1, x2});
        }

        Collections.sort(cows, (a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            return a[1] - b[1];
        });
        
        int count = 0;
        int j = 0; // Index for chickens
        for (int i = 0; i < N && j < C; i++) {
            int x1 = cows.get(i)[0];
            int x2 = cows.get(i)[1];
            
            while (j < C && chickens.get(j) < x1) {
                j++;
            }

            if (j < C && chickens.get(j) <= x2) {
                count++;
                j++;
            }
        }

        pw.println(count);
        pw.close();
    }
}
