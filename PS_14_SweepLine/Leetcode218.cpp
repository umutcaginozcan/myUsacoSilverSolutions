#include <bits/stdc++.h>
using namespace std;

struct Event {
    int x;     // The x-coordinate
    int h;     // The building height
    int type;  // 0 = left edge, 1 = right edge
};

// Custom comparator for sorting the events
// 1) Sort by x ascending
// 2) If tie in x, left edges come before right edges
// 3) If tie in x and both are left, sort by height descending
// 4) If tie in x and both are right, sort by height ascending
bool eventCompare(const Event &a, const Event &b) {
    if (a.x != b.x) {
        return a.x < b.x;
    } else {
        if (a.type != b.type) {
            return a.type < b.type; 
            // ensures left (0) edges are processed before right (1)
        } else {
            // now both have same x and same type
            if (a.type == 0) {
                // both left edges: higher height first
                return a.h > b.h;
            } else {
                // both right edges: lower height first
                return a.h < b.h;
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    // Collect all "edge" events
    vector<Event> events;
    events.reserve(2 * N);

    for(int i = 0; i < N; i++){
        int x1, x2, h;
        cin >> x1 >> x2 >> h;
        // Left edge
        events.push_back({x1, h, 0});
        // Right edge
        events.push_back({x2, h, 1});
    }

    // Sort events by our custom comparator
    sort(events.begin(), events.end(), eventCompare);

    // Multiset to track current building heights
    multiset<int> ms;
    ms.insert(0); // always have "ground" = 0
    int maxH = 0; // current maximum height

    // We'll print (x, height) whenever the max changes
    for (auto &e : events) {
        if (e.type == 0) {
            // Left edge: insert height
            ms.insert(e.h);
        } else {
            // Right edge: remove one occurrence of height
            auto it = ms.find(e.h);
            if (it != ms.end()) {
                ms.erase(it);
            }
        }

        // Check if the max height in the multiset changed
        int currentMax = *ms.rbegin();
        if (currentMax != maxH) {
            maxH = currentMax;
            cout << e.x << " " << maxH << "\n";
        }
    }

    return 0;
}
