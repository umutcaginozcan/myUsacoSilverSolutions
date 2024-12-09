#include <iostream>
#include <vector>
#include <array>
#include <algorithm>

using namespace std;

int main()
{
    int N;
    cin >> N;
    vector<array<int, 2>> events;

    while (N--) {
        int x1, x2;
        cin >> x1 >> x2;
        events.push_back({x1, 0});
        events.push_back({x2, 1});
    }

    sort(events.begin(), events.end());

    int cnt = 0, res = 0;
    for (auto e: events) {
        if (e[1] == 0) {
            cnt++;
            res = max(res, cnt);
        } else
            cnt--;
    }

    cout << res << endl;
}