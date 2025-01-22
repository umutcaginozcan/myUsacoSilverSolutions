#include <cstdio>
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

struct Event {
    int x;
    int LR;
    bool operator<(const Event &other) const {
        return x < other.x;
    }
};

int main() {
    freopen("paint.in",  "r", stdin);
    freopen("paint.out", "w", stdout);

    int N, K;
    cin >> N >> K;

    vector<Event> events;
    events.reserve(2*N);

    int curx = 0;
    for(int i = 0; i < N; i++) {
        int dist;
        char dir;
        cin >> dist >> dir;

        Event e1;
        e1.x  = curx;
        Event e2;

        if (dir == 'R') {
            e1.LR =  1;
            e2.LR = -1;
            e2.x  = curx + dist;
        } else {
            e1.LR = -1;
            e2.LR =  1;
            e2.x  = curx - dist;
        }
        curx = e2.x;

        events.push_back(e1);
        events.push_back(e2);
    }
    sort(events.begin(), events.end());

    long long answer = 0;
    long long fold = 0;
    int prevX = events[0].x;

    for (int i = 0; i < (int)events.size(); i++) {
        int nowX = events[i].x;

        if (fold >= K) {
            answer += (long long)nowX - prevX;
        }
        
        fold += events[i].LR;
        prevX = nowX;
    }

    cout << answer << endl;
    return 0;
}