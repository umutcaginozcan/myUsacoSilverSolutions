import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class usaco2013dec {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("baseball.in")));
		PrintWriter out = new PrintWriter("baseball.out");
		
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] cow_locations = new int[N];
        for (int i = 0; i < N; i++) {
            cow_locations[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(cow_locations);

        int result = 0;
        for (int i = 0; i < N - 2; i++) {
            outerloop:
            for (int j = i + 1; j < N - 1; j++) {
                for (int k = j + 1 ; k < N; k++) {
                    int dist1 = cow_locations[j] - cow_locations[i];
                    int dist2 = cow_locations[k] - cow_locations[j];
                    result += (dist2 >= dist1 && dist2 <= 2 * dist1) ? 1 : 0;
                    if (dist2 >= dist1) continue outerloop;
                }
            }
        }
        out.println(result);
        out.close();
    }
}
