#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

// This function takes a vector of moves (pairs of x,y) 
// and returns a "frequency map" for all subsets of those moves:
//    freqMap[ (x_sum, y_sum) ][ k ] = number of ways to get offset (x_sum, y_sum)
//                                     using exactly k moves.
map< pair<ll,ll>, map<int,ll> > 
buildFreqMap(const vector< pair<ll,ll> > &moves)
{
    // We'll first collect all (x_sum, y_sum, k) in a temporary vector, 
    // then convert to a map at the end.
    vector< tuple<ll,ll,int> > subsetData;

    // Start with the empty subset: offset (0,0), using 0 moves.
    subsetData.push_back({0LL, 0LL, 0});

    // For each move in 'moves', we extend all existing subsets by including this move.
    for (auto &m : moves) {
        ll dx = m.first, dy = m.second;
        int currentSize = (int)subsetData.size();
        
        // We'll copy the existing subsets and add the new move to form new subsets.
        for (int i = 0; i < currentSize; i++) {
            auto [oldX, oldY, oldK] = subsetData[i];
            // New offset is (oldX + dx, oldY + dy), new subset size = oldK + 1
            subsetData.push_back({oldX + dx, oldY + dy, oldK + 1});
        }
    }

    // Now we aggregate these into a map: (x_sum, y_sum) -> {k -> count}
    map< pair<ll,ll>, map<int,ll> > freq;
    for (auto &item : subsetData) {
        ll xSum = std::get<0>(item);
        ll ySum = std::get<1>(item);
        int k   = std::get<2>(item);
        freq[{xSum, ySum}][k]++;
    }
    return freq;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    // 1) Read input
    int N;
    cin >> N;
    ll x_s, y_s;       // The target offset (goal)
    cin >> x_s >> y_s;

    vector< pair<ll,ll> > moves(N);
    for(int i=0; i<N; i++){
        ll x, y;
        cin >> x >> y;
        moves[i] = {x, y};
    }

    // 2) Split the moves into two halves.
    int mid = N/2;  // floor division
    vector< pair<ll,ll> > leftMoves(moves.begin(), moves.begin() + mid);
    vector< pair<ll,ll> > rightMoves(moves.begin() + mid, moves.end());

    // 3) Build frequency maps for all subsets in each half.
    //    leftFreq[(xL,yL)][kL] = # ways in left half
    //    rightFreq[(xR,yR)][kR] = # ways in right half
    auto leftFreq  = buildFreqMap(leftMoves);
    auto rightFreq = buildFreqMap(rightMoves);

    // We'll store the final answers: ans[K] = how many subsets 
    // sum to (x_s, y_s) using exactly K moves.
    vector<ll> ans(N+1, 0LL);

    // 4) Combine: For each (offsetL, mapOfK) in left half,
    //    we want offsetR = (x_s - xL, y_s - yL) in the right half.
    //    Then, for each subset size kL in leftMap and kR in rightMap 
    //    that produce these offsets, we add to ans[kL + kR].
    for (auto &leftEntry : leftFreq) {
        // leftEntry.first = (xL, yL), leftEntry.second = map<kL, countL>
        ll xL = leftEntry.first.first;
        ll yL = leftEntry.first.second;
        auto &sizeMapL = leftEntry.second; // map<int, ll>

        // The needed offset from the right side:
        ll needX = x_s - xL;
        ll needY = y_s - yL;

        // Check if the right half has (needX, needY):
        auto it = rightFreq.find({needX, needY});
        if (it == rightFreq.end()) {
            // If none in the right half produce this offset, skip
            continue;
        }
        // Otherwise, we have matches in the right half
        auto &sizeMapR = it->second; // map<kR, countR>

        // 5) Combine the sizes
        // For each kL in left side, and kR in right side, 
        // the total subset size is kL + kR.
        // We add (#ways from left) * (#ways from right) to ans[kL + kR].
        for (auto &lPair : sizeMapL) {
            int kL = lPair.first;
            ll countL = lPair.second;
            for (auto &rPair : sizeMapR) {
                int kR = rPair.first;
                ll countR = rPair.second;
                // Add to the total ways for kL + kR
                if (kL + kR <= N) {
                    ans[kL + kR] += (countL * countR);
                }
            }
        }
    }

    // 6) Output the results for K = 1..N
    //    (The problem statement typically wants us to ignore K=0.)
    for (int K = 1; K <= N; K++){
        cout << ans[K] << "\n";
    }

    return 0;
}
