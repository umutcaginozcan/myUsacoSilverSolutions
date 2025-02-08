#include <bits/stdc++.h>

using namespace std;

using P = pair<long long, long long>;
P operator+(P a, P b) { return {a.first + b.first, a.second + b.second}; }
P operator-(P a, P b) { return {a.first - b.first, a.second - b.second}; }

// Generate all subset-sums of the given directions "dirs".
vector<pair<P, int>> finalLocations(const vector<P> &dirs) {
    vector<pair<P, int>> finalLocations;
    finalLocations.push_back({{0, 0}, 0}); // start with (0,0), 0 moves

    for (const auto &d : dirs) {
        int n = finalLocations.size();
        for (int i = 0; i < n; i++) {
            // Compute new location by adding d
            P newLoc = finalLocations[i].first + d;
            int newCount = finalLocations[i].second + 1;
            finalLocations.push_back({newLoc, newCount});
        }
    }
    // Sort the resulting subset sums by the (x,y) coordinate
    sort(finalLocations.begin(), finalLocations.end());
    return finalLocations;
}

int main() {
    int N;
    cin >> N;               // Number of directions
    P goal;                 // Goal position
    cin >> goal.first >> goal.second;

    // Read the directions
    vector<P> dirs(N);
    for (auto &d : dirs) {
        cin >> d.first >> d.second;
    }

    // 1) Split into two halves
    vector<pair<P, int>> L = finalLocations(vector<P>(begin(dirs), begin(dirs) + N / 2));
    vector<pair<P, int>> R = finalLocations(vector<P>(begin(dirs) + N / 2, end(dirs)));

    // 2) Reverse R for a two-pointer style approach
    reverse(R.begin(), R.end());
    vector<long long> ans(N + 1);
    vector<int> with_num;

    // We'll keep track of the "previous rest" so we only update when needed
    P rest_prev{LLONG_MAX, LLONG_MAX};
    int idx = 0;  // pointer/index to scan R

    for (const auto &[offsetL, usedL] : L) {
        P rest = goal - offsetL;

        if (rest != rest_prev) {
            rest_prev = rest;

            with_num = vector<int>(N - N/2 + 1, 0);

            while (idx < (int)R.size() && R[idx].first > rest) {
                idx++;
            }

            int tempIdx = idx;
            while (tempIdx < (int)R.size() && R[tempIdx].first == rest) {
                with_num[R[tempIdx].second]++;
                tempIdx++;
            }
        }

        for (int usedR = 0; usedR < (int)with_num.size(); usedR++) {
            ans[usedL + usedR] += with_num[usedR];
        }
    }

    for (int i = 1; i <= N; i++) {
        cout << ans[i] << "\n";
    }
}
