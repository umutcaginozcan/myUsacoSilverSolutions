import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class usaco2017feb2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        ArrayList<Integer> chickens = new ArrayList<>();
  
        for (int i = 0; i < C; i++) {
            chickens.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(chickens);
        
        ArrayList<ArrayList<Integer>> cows = new ArrayList<ArrayList<Integer> >();
        for (int i = 0; i < N; i++) {
            cows.add(new ArrayList<Integer>());

            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            cows.get(i).add(x1);
            cows.get(i).add(x2);
        }

        Collections.sort(cows, (a,  b) -> {
            if (a.get(1) == b.get(1))
                return a.get(0) - b.get(0);
            return a.get(1) - b.get(1);
        });
        
        int count = 0;
        int j = 0; // Chickens
        for (int i = 0; i < N && j < C; i++) {
            int x1 = cows.get(i).get(0);
            int x2 = cows.get(i).get(1);
            int x = chickens.get(j);
            
            while (j < C && x < x1) {
                j++;
                x = chickens.get(j);
            }

            if (j < C && x <= x2) {
                count++;
                j++;
            }
        }

        pw.println(count);
        pw.close();
    }
}
