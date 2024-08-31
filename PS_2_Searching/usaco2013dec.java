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
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int lower_bound = 2 * cow_locations[j] - cow_locations[i];
                int higher_bound = 3 * cow_locations[j] - 2 * cow_locations[i];
                
                int left_index = Arrays.binarySearch(cow_locations, lower_bound);
                if (left_index < 0) left_index = Math.abs(left_index + 1);
                int right_index = Arrays.binarySearch(cow_locations, higher_bound);
                if (right_index < 0) right_index = Math.abs(right_index + 2);

                result += right_index - left_index + 1;
            }
        }
        out.println(result);
        out.close();
    }
}
