#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

#define MAXN 100000

int N, M, K;
int leftBounds[100], rightBounds[100];
int permutation[MAXN]; // After applying the swapping operations once
int cycleID[MAXN];     // To record which cycle each element belongs to
int positionInCycle[MAXN]; // Position of each element in its cycle
vector<int> cycles[MAXN + 1]; // Store the elements of each cycle
int answer[MAXN];

int main() {
    freopen("swap.in", "r", stdin);
    freopen("swap.out", "w", stdout);

    cin >> N >> M >> K;
    for (int i = 0; i < M; i++) {
        cin >> leftBounds[i] >> rightBounds[i];
        // Convert to 0-based indexing
        leftBounds[i]--;
        rightBounds[i]--;
    }

    // Compute the permutation after applying the M "reverse-segment" operations once
    for (int i = 0; i < N; i++) {
        permutation[i] = i;
        // For each of the M specified segments, if the current position falls inside that segment,
        // reflect it about the segment's midpoint to find its new position.
        for (int j = 0; j < M; j++) {
            if (permutation[i] >= leftBounds[j] && permutation[i] <= rightBounds[j]) {
                permutation[i] = leftBounds[j] + rightBounds[j] - permutation[i];
            }
        }
    }

    // Identify cycles in the resulting permutation. If we repeatedly apply the permutation to an element i,
    // eventually we return to i. That sequence forms a cycle. We will store each cycle and the position of each
    // element within its cycle.
    int cycleCount = 1;
    for (int i = 0; i < N; i++) {
        if (!cycleID[i]) {
            cycleID[i] = cycleCount;
            cycles[cycleCount].push_back(i);
            int next = permutation[i];
            if (next != i) positionInCycle[next] = 1;

            // Follow the cycle until we return to the starting element
            while (next != i) {
                cycles[cycleCount].push_back(next);
                cycleID[next] = cycleCount;
                if (permutation[next] != i) {
                    positionInCycle[permutation[next]] = positionInCycle[next] + 1;
                }
                next = permutation[next];
            }
            cycleCount++;
        }
    }

    // After K applications of the permutation, an element's new position in its cycle will be shifted by (K % cycle_size).
    // We use this to directly compute the final answer without simulating K times.
    for (int i = 0; i < N; i++) {
        int cID = cycleID[i];
        int cSize = (int)cycles[cID].size();
        // Move (K steps ahead) in the cycle from element i's initial position
        int newPos = (positionInCycle[i] + K) % cSize;
        answer[cycles[cID][newPos]] = i;
    }

    // Output the final configuration (convert back to 1-based indexing)
    for (int i = 0; i < N; i++) {
        cout << answer[i] + 1 << '\n';
    }

    return 0;
}