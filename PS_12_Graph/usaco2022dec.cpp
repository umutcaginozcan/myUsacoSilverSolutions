#include <iostream>
#include <vector>
using namespace std;

int N;
vector<int> barns;          // Barns' values
vector<int> subtreeSum;     // Subtree sum values
vector<vector<int>> adjList; // Adjacency list for the tree
vector<vector<int>> res;    // Result operations

void dfs1(int cur, int parent) {
    subtreeSum[cur] = barns[cur];
    for (int neighbor : adjList[cur]) {
        if (neighbor != parent) {
            dfs1(neighbor, cur);
            subtreeSum[cur] += subtreeSum[neighbor];
        }
    }
}

void dfs2(int cur, int parent) {
    // First handle children with positive subtree sums
    for (int neighbor : adjList[cur]) {
        if (neighbor != parent && subtreeSum[neighbor] >= 0) {
            dfs2(neighbor, cur);
        }
    }

    // Then handle children with negative subtree sums
    for (int neighbor : adjList[cur]) {
        if (neighbor != parent && subtreeSum[neighbor] < 0) {
            res.push_back({cur, neighbor, subtreeSum[neighbor]});
            subtreeSum[neighbor] += subtreeSum[cur];
            dfs2(neighbor, cur);
        }
    }

    // Handle positive remainder at the current node
    if (cur != parent && subtreeSum[cur] > 0) {
        res.push_back({cur, parent, subtreeSum[cur]});
        subtreeSum[cur] = 0;
    }
}

int main() {
    cin >> N;

    barns.resize(N + 1);
    subtreeSum.resize(N + 1);
    adjList.resize(N + 1);

    int totalSum = 0;
    for (int i = 1; i <= N; i++) {
        cin >> barns[i];
        totalSum += barns[i];
    }

    int avg = totalSum / N;
    for (int i = 1; i <= N; i++) {
        barns[i] -= avg;
    }

    for (int i = 1; i <= N - 1; i++) {
        int n1, n2;
        cin >> n1 >> n2;
        adjList[n1].push_back(n2);
        adjList[n2].push_back(n1);
    }

    dfs1(1, 1); // Compute subtree sums
    dfs2(1, 1); // Distribute excess/deficit

    cout << res.size() << endl;
    for (size_t i = 0; i < res.size(); ++i) {
        const vector<int> &op = res[i];
        cout << op[0] << " " << op[1] << " " << op[2];
        if (i != res.size() - 1) {
            cout << endl; // Append a newline except for the last line
        }
    }
}