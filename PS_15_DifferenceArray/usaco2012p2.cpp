#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int N, K;
    cin >> N >> K;
    vector<int> diffArray(N + 1, 0);

    while (K--) {
        int A, B;
        cin >> A >> B;
        diffArray[A]++;
        if (B + 1 <= N) {
            diffArray[B + 1]--;
        }
    }

    for (int i = 1; i <= N; i++) {
        diffArray[i] += diffArray[i - 1];
    }

    diffArray.erase(diffArray.begin());

    // Find the median using nth_element
    nth_element(diffArray.begin(), diffArray.begin() + N / 2, diffArray.end());

    cout << diffArray[N / 2] << endl;
    return 0;
}