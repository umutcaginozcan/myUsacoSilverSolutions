#include <iostream>
#include <vector>
#include <queue>
#include <cmath>
using namespace std;

int main()
{
    int C, N;
    cin >> C >> N;

    vector<int> teams(N, 0);
    for (int i = 0; i < N; i++) {
        int num = 0;
        for (int j = 0; j < C; j++) {
            char ch;
            cin >> ch;
            if (ch == 'H') {
                num = num | (1 << j);
            }
        }
        teams[i] = num;
    }

    vector<int> distances(static_cast<int>(pow(2, C)), -1);
    queue<int> q;
    for (auto team: teams) {
        int complement = ((int) pow(2, C) - 1) ^ team;
        q.push(complement);
        distances[complement] = 0;
    }

    while (!q.empty()) {
        int u = q.front();
        q.pop();

        for (int i = 0; i < C; i++) {
            int v = u ^ (1 << i);
            if (distances[v] < 0) {
                distances[v] = distances[u] + 1;
                q.push(v);
            }
        }
    }

    for (auto team: teams) {
        cout << C - distances[team] << endl;
    }
    return 0;
}
