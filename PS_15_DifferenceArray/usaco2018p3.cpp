#include <iostream>
#include <fstream>
#include <map>
#include <algorithm>
using namespace std;

int main()
{
    ifstream fin("teleport.in");
    ofstream fout("teleport.out");

    long long N, cur_total = 0;
    fin >> N;
    map<long long, long long> events;

    for (long long i = 0; i < N; i++) {
        long long a, b;
        fin >> a >> b;
        cur_total += abs(a - b); // Start with worst case: no teleportation.

        if (abs(a) > abs(a - b)) continue; // No events for this scenario.
        // Now deal with events.
        events[b] += 2;
        if (a < b && a < 0) { events[0]--; events[2 * b]--; }
        if (a < b && a >= 0) { events[2 * b - 2 * a]--; events[2 * a]--; }
        if (a >= b && a < 0) { events[2 * b - 2 * a]--; events[2 * a]--; }
        if (a >= b && a >= 0) { events[0]--; events[2 * b]--; }
    }

    // Now sweep line on our ordered map:
    long long cur_y = -2000000000, cur_slope = 0, res = cur_total;
    for (auto& e : events) {
        long long new_y = e.first, new_slope = e.second;
        cur_total += (new_y - cur_y) * cur_slope;
        cur_y = new_y;
        cur_slope += new_slope;
        res = min(res, cur_total);
    }

    fout << res << endl;
    return 0;
}
