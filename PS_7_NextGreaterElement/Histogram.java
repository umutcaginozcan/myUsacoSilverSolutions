import java.util.*;

public class Histogram {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] rectangles = new int[N];
        for (int i = 0; i < N; i++) {
            rectangles[i] = in.nextInt();
        }

        Stack<Integer> indexStack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i < N; i++) {
            while (!indexStack.isEmpty() && rectangles[indexStack.peek()] > rectangles[i]) {
                int height = rectangles[indexStack.pop()];
                int width = indexStack.isEmpty() ? i : i - indexStack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            indexStack.push(i);
        }

        // Clean up remaining bars in stack
        while (!indexStack.isEmpty()) {
            int height = rectangles[indexStack.pop()];
            int width = indexStack.isEmpty() ? N : N - indexStack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }

        System.out.println(maxArea);
        in.close();
    }
}

/* 
    Stack:
    0 (val: 2)
    
 */