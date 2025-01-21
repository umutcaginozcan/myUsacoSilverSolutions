#include <algorithm>
#include <iostream>
#include <vector>
#include <array>
#include <set>
using namespace std;

int go(multiset<array<int,2>> &ms) {
    vector<array<int, 2>> events;
    for (auto item : ms) {
        events.push_back({item[0], 1});
        events.push_back({item[1], -1});
    }

    sort(events.begin(), events.end());

    int h = 0, cnt = 0, preX = 0;
    for (auto e : events) {
        if (cnt > 0) h += e[0] - preX;
        cnt += e[1];
        preX = e[0];
    }

    return h;
}

int main() {
    int N;
    cin >> N;
    vector<array<int, 4>> events;

    while (N--) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        events.push_back({x1, y1, y2, 0});
        events.push_back({x2, y1, y2, 1});
    }

    sort(events.begin(), events.end());

    int preX = 0;

    multiset<array<int, 2>> ms;
    int ans = 0;
    for (auto e : events) {
        int w = e[0] - preX;
        int h = go(ms);
        ans += w*h;

        if (e[3] == 0) {
            ms.insert({e[1], e[2]});
        } else {
            ms.erase(ms.find({e[1], e[2]}));
        }

        preX = e[0];
    }

    cout << ans << endl;
}