#include <iostream>
#include <set>
#include <utility>
#include <bits/stdc++.h>
using namespace std;

// We'll store each square by (left, right, bottom, top, id).
// Then we'll create two "events" for each square:
//  - (x = left,  type = +1, bottom, top, id)
//  - (x = right, type = -1, bottom, top, id)
//
// We'll sort these events by x, then sweep from left to right.
// As we process an event:
//   If it's +1, we insert [bottom, top] into an active structure
//   If it's -1, we remove [bottom, top] from the active structure
// After each insert, we check for overlap with existing intervals.
//
// If more than one distinct pair of squares overlap, we answer -1.
// If exactly one pair overlaps, we keep track of that area; if we
// discover a second overlap pair, answer -1 immediately.
// If none overlap by the end, we output 0.

struct Square {
    int leftX, rightX, bottomY, topY, id;
};

struct Event {
    int x;      // sweep-line position
    int type;   // +1 = enter, -1 = exit
    int bottom, top;
    int id;     // which square?
};

// Utility to compute overlap area of squares i & j, if they do overlap.
// The squares are axis-aligned. We'll store them globally or pass them in.
long long overlapArea(const Square &A, const Square &B) {
    // Overlap in X
    long long overlapX = min((long long)A.rightX, (long long)B.rightX)
                       - max((long long)A.leftX,  (long long)B.leftX);
    // Overlap in Y
    long long overlapY = min((long long)A.topY,    (long long)B.topY)
                       - max((long long)A.bottomY, (long long)B.bottomY);

    if (overlapX <= 0 || overlapY <= 0) return 0; // no positive overlap
    return overlapX * overlapY;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, K;
    cin >> N >> K;

    vector<Square> squares(N);
    // Read input: each square i is centered at (x_i, y_i)
    // side length K => halfK = K/2
    // boundaries = (x_i - halfK, x_i + halfK) x (y_i - halfK, y_i + halfK)
    long long halfK = K / 2LL;
    for(int i = 0; i < N; i++){
        long long cx, cy;
        cin >> cx >> cy;
        squares[i].leftX   = cx - halfK;
        squares[i].rightX  = cx + halfK;
        squares[i].bottomY = cy - halfK;
        squares[i].topY    = cy + halfK;
        squares[i].id      = i;
    }

    // Build events
    vector<Event> events;
    events.reserve(2*N);

    for(int i = 0; i < N; i++){
        // Enter event at squares[i].leftX
        events.push_back({
            squares[i].leftX, +1,
            squares[i].bottomY, squares[i].topY,
            squares[i].id
        });
        // Exit event at squares[i].rightX
        events.push_back({
            squares[i].rightX, -1,
            squares[i].bottomY, squares[i].topY,
            squares[i].id
        });
    }

    // Sort events by x (if tie, exits before enters to avoid zero-width overlap)
    sort(events.begin(), events.end(), [&](auto &a, auto &b){
        if(a.x != b.x) return a.x < b.x;
        return a.type < b.type;  // process exits (-1) before enters (+1)
    });

    // We'll store active intervals in y with a balanced structure.
    // For demonstration, let's keep them in a std::map<bottom, (top, id)>
    // or a multiset. We'll look for overlap by checking neighbors in y.

    // But we only need to quickly find any interval that overlaps in y
    // with the newly added interval. A simpler approach: store them in
    // a sorted container keyed by "bottomY" or "topY". Then we search
    // around that region.
    //
    // We'll pick a std::set keyed by (bottomY, topY, id).  We'll rely on
    // the fact that squares can only overlap if their y-ranges intersect.

    // track how many distinct overlapping pairs we have found
    int overlapCount = 0;
    long long overlapAnswer = 0;  // area for the single overlap pair
    // We'll store the index of the squares in that single overlap,
    // so we know if we find a *new* pair distinct from the first.
    pair<int,int> overlapPair(-1, -1);

    // We also may want fast access by ID if we need to remove an interval
    // by ID. One approach is to keep a map from ID -> (bottom, top).
    unordered_map<int, pair<int,int>> activeIntervalByID;
    // We'll keep an ordered set so we can find squares whose bottom Y
    // or top Y might intersect with the newly inserted one.
    // Key will be (bottom, top, id).

    struct Yinterval {
        int bottom, top, id;
        bool operator<(const Yinterval &o) const {
            // Sort primarily by bottom, then top, then id to break ties
            if(bottom != o.bottom) return bottom < o.bottom;
            if(top != o.top)       return top < o.top;
            return id < o.id;
        }
    };

    set<Yinterval> activeSet;

    auto checkOverlapWithNeighbors = [&](const Yinterval &Y) {
        // In activeSet, find Y, then check the interval(s) around it
        // (predecessor and successor) for potential overlap.
        // If we discover a new overlap, handle it.

        auto it = activeSet.find(Y);
        if(it == activeSet.end()) return;

        // We'll define a small helper that, given two IDs, checks if
        // their squares overlap. If so, record it or detect multiple.
        auto tryOverlap = [&](int idA, int idB) {
            if(idA == idB) return;
            long long area = overlapArea(squares[idA], squares[idB]);
            if(area > 0) {
                // We found a positive overlap area
                // If we've never found an overlap so far:
                if(overlapCount == 0) {
                    overlapCount = 1;
                    overlapAnswer = area;
                    // store the pair in sorted order so we can detect distinct pairs
                    overlapPair = minmax(idA, idB);
                }
                else {
                    // We already have found an overlap
                    // Check if it's a *different* pair from the existing one:
                    auto newPair = minmax(idA, idB);
                    if(newPair != overlapPair) {
                        // different pair => immediately -1
                        overlapCount = 2;
                    }
                }
            }
        };

        // Check predecessor
        if(it != activeSet.begin()) {
            auto pit = prev(it);
            // If pit->top >= Y.bottom, there's a chance of overlap in Y
            // (since Yinterval [pit->bottom, pit->top] intersects [Y.bottom, Y.top])
            // But we’ll do the real check in overlapArea() anyway.
            tryOverlap(pit->id, it->id);
        }
        // Check successor
        auto nit = next(it);
        if(nit != activeSet.end()) {
            tryOverlap(nit->id, it->id);
        }
    };

    // Sweep
    for(auto &e : events) {
        int x  = e.x;
        int tp = e.type;
        int b  = e.bottom;
        int t  = e.top;
        int iD = e.id;

        if(tp == +1) {
            // Enter event: Insert the interval
            Yinterval Y = { b, t, iD };
            activeSet.insert(Y);
            activeIntervalByID[iD] = make_pair(b, t);

            // Check for overlap with neighbors
            checkOverlapWithNeighbors(Y);

        } else {
            // Exit event: remove the interval
            // We must find the exact (bottom, top) we inserted for iD
            auto itPair = activeIntervalByID.find(iD);
            if(itPair != activeIntervalByID.end()) {
                Yinterval Y = { itPair->second.first, itPair->second.second, iD };
                if(activeSet.count(Y)) {
                    // Before removing, we can also check its neighbors for overlap
                    // (though strictly speaking, we only need to do that upon insertion).
                    // We'll just remove it now.
                    activeSet.erase(Y);
                }
                activeIntervalByID.erase(iD);
            }
        }

        // If we've detected 2 distinct overlaps, we can stop
        if(overlapCount >= 2) break;
    }

    // Determine final output
    if(overlapCount == 0) {
        // No overlapping squares
        cout << 0 << "\n";
    } else if(overlapCount == 1) {
        // Exactly one pair overlapping
        cout << overlapAnswer << "\n";
    } else {
        // More than one distinct pair found
        cout << -1 << "\n";
    }

    return 0;
}
