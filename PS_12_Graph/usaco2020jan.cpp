#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

static vector<int> wrongEdges;
static vector<bool> visited;
static vector<vector<int>> adj;
static int wrongCnt;
static int N, M;
static int visitedcnt;

void dfs(int curnode) {
    visited[curnode] = true;
    visitedcnt++;
    for (int nextnode : adj[curnode]) {
        if (!visited[nextnode]) {
            dfs(nextnode);
        }
    }
}

bool valid(vector<vector<int>>& wormholes, int curwormhole) {
    // Initialize adjacency list and visited array
    adj.assign(N, vector<int>());
    visited.assign(N, false);
    visitedcnt = 0;

    // Build the graph from the current wormhole index
    for (int i = curwormhole; i < wormholes.size(); i++) {
        int u = wormholes[i][0];
        int v = wormholes[i][1];
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    // Perform DFS from each edge if not visited
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < adj[i].size(); j++) {
            if (!visited[adj[i][j]])
                dfs(adj[i][j]);
        }
    }

    // Check if all wrong nodes were visited
    for (int i = 0; i < wrongCnt; i++) {
        if (!visited[wrongEdges[i]])
            return false;
    }
    return true;
}

int main() {
    cin >> N >> M;

    vector<int> cows(N, 0);
    for (int i = 0; i < N; i++)
        cin >> cows[i];

    wrongEdges.clear();
    wrongCnt = 0;
    for (int i = 0; i < N; i++) {
        if (cows[i] != i) {
            wrongCnt++;
            wrongEdges.push_back(i);
        }
    }

    vector<vector<int>> wormholes(M, vector<int>(3));
    for (int i = 0; i < M; i++) {
        cin >> wormholes[i][0];
        cin >> wormholes[i][1];
        cin >> wormholes[i][2];
    }

    sort(wormholes.begin(), wormholes.end(),
        [](const vector<int>& w1, const vector<int>& w2) {
            return w1[2] < w2[2];
        });

    int lo = 0, hi = M - 1, res = -1;
    while (lo <= hi) {
        int mid = (lo + hi) / 2;
        if (valid(wormholes, mid)) {
            res = wormholes[mid][2];
            hi = mid - 1;
        } else {
            lo = mid + 1;
        }
    }

    cout << "Result: " << res << endl;
    return 0;
}