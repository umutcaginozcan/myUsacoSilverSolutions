#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M;
vector<vector<int>> wormholes;
vector<int> visited;
vector<int> cows;
vector<int> wrong;

void dfs(int v, vector<vector<int>>& adjlist, vector<int>& visited) {
    visited[v] = 1;
    for (int u : adjlist[v]) {
        if (!visited[u - 1]) {
            dfs(u - 1, adjlist, visited);
        }
    }
}

bool valid(int testIndex) {
    // construct the graph: use adjacency list
    vector<vector<int>> adjlist(N);
    for (int i = testIndex; i < M; i++) {
        int c1 = wormholes[i][0], c2 = wormholes[i][1];
        adjlist[c1 - 1].push_back(c2);
        adjlist[c2 - 1].push_back(c1);
    }

    // Find the first node with edges
    int start = -1;
    for (int i = 0; i < N; i++) {
        if (!adjlist[i].empty()) {
            start = i;
            break;
        }
    }

    // Flood fill the adjlist
    visited.assign(N, 0);
    if (start != -1) dfs(start, adjlist, visited); // Start DFS only if a valid start exists

    // Check if all nodes marked 'wrong' are visited
    for (int cow : wrong) {
        if (!visited[cow - 1])
            return false;
    }
    return true;
}

int main() {
    ifstream fin("wormsort.in");
    ofstream fout("wormsort.out");

    fin >> N >> M;
    cows.resize(N);
    wormholes.resize(M, vector<int>(3));
    visited.resize(N);

    bool alreadySorted = true;
    for (int i = 0; i < N; i++) {
        fin >> cows[i];
        if (cows[i] != i + 1) {
            alreadySorted = false;
            wrong.push_back(cows[i]);
        }
    }

    if (alreadySorted) {
        fout << -1;
        return 0;
    }

    for (int i = 0; i < M; i++) {
        for (int j = 0; j < 3; j++) {
            fin >> wormholes[i][j];
        }
    }

    sort(wormholes.begin(), wormholes.end(), [](const vector<int> &a, const vector<int> &b) {
        return a[2] < b[2]; 
    });

    int lo = 0, hi = M - 1, res = -1;
    while (lo <= hi) {
        int mid = (lo + hi) / 2;
        if (valid(mid)) {
            lo = mid + 1;
            res = wormholes[mid][2];
        } else {
            hi = mid - 1;
        }
    }

    fout << res;

    fin.close();
    fout.close();
    return 0;
}