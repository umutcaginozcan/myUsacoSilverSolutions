#include <iostream>
#include <vector>
#include <algorithm>

#define MAXN 10000

using namespace std;

int main() {
    int N;
    cin >> N;
    vector<int> A(N);

    vector<int> whereInA(MAXN, -1);
    for (int i = 0; i < N; i++) {
        cin >> A[i];
        whereInA[A[i]] = i;
    }

    vector<int> B = A;
    sort(B.begin(), B.end());

    // Construct disjoint cycles
    vector<vector<int>> disCycles;
    vector<bool> visited(N, false);

    for (int i = 0; i < N; i++) {
        if (visited[i]) continue;

        vector<int> cycle;
        int j = i;
        while (!visited[j]) {
            cycle.push_back(B[j]);
            visited[j] = true;
            j = whereInA[B[j]];
        }

        if (!cycle.empty())
            disCycles.push_back(cycle);
    }

    // Traverse disjoint cycles
    int res = 0;
    for (int i = 0; i < (int)disCycles.size(); i++) {
        int cycleMin = MAXN;
        int sum = 0;

        for (int j = 0; j < (int)disCycles[i].size(); j++) {
            sum += disCycles[i][j];
            if (disCycles[i][j] < cycleMin) cycleMin = disCycles[i][j];
        }

        sum += (disCycles[i].size() - 2) * cycleMin;
        res += sum;
    }

    cout << res << "\n";
    return 0;
}