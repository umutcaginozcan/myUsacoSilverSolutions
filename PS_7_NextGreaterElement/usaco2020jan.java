import java.util.*;

public class usaco2020jan {
    static long ans = 0;
    static int N;

    // Monotonic Stack Approach Translated from C++
    static void addContributionMonotonicStack(int[] h) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = N - 1; i >= 0; --i) {
            while (!stack.isEmpty() && h[stack.peek()] < h[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                ans += stack.peek() - i + 1;
            }
            stack.push(i);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        int[] h = new int[N];
        for (int i = 0; i < N; i++) {
            h[i] = scanner.nextInt();
        }

        // Apply monotonic stack method for the forward pass
        addContributionMonotonicStack(h);
        // Reverse the array for the backward pass
        reverse(h);
        // Apply monotonic stack method for the reversed array
        addContributionMonotonicStack(h);

        System.out.println(ans);
    }

    // Utility method to reverse an array
    static void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
