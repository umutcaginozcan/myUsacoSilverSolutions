package myUsacoSilverSolutions.Sorting;

public class CountingSort {
    public static void main(String[] args) {
        final int N = 10, MAX = 20;
        int numbers[] = {7, 1, 8, 1, 9, 7, 5, 1, 18, 1};

        int cnt[] = new int[MAX + 1]; //initialized to zeros

        for (Integer num: numbers) {
            cnt[num]++;
        }

        int idx = 0;
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                numbers[idx] = i;
                idx++;
            }  
        } 

        for (Integer num: numbers) System.out.println(num);
    }
}