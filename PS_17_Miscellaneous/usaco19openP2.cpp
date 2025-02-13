#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
#include <fstream>

using namespace std;
typedef long long LL;

struct Point {
    LL x, y;
    int segindex;
};

struct Segment {
    Point p, q;
    int index;
};

bool operator<(Point p1, Point p2) {
    return (p1.x == p2.x) ? (p1.y < p2.y) : (p1.x < p2.x);
}

int sign(LL x) {
    return (x == 0) ? 0 : (x < 0 ? -1 : 1);
}

int operator*(Point p1, Point p2) {
    return sign(p1.x * p2.y - p1.y * p2.x);
}

Point operator-(Point p1, Point p2) {
    return {p1.x - p2.x, p1.y - p2.y};
}

bool isect(Segment &s1, Segment &s2) {
    Point &p1 = s1.p, &q1 = s1.q, &p2 = s2.p, &q2 = s2.q;
    return ((q2 - p1) * (q1 - p1)) * ((q1 - p1) * (p2 - p1)) >= 0 &&
           ((q1 - p2) * (q2 - p2)) * ((q2 - p2) * (p1 - p2)) >= 0;
}

double x; // Current sweep line position

double eval(Segment s) {
    if (s.p.x == s.q.x) return s.p.y;
    return s.p.y + (s.q.y - s.p.y) * (x - s.p.x) / (s.q.x - s.p.x);
}

bool operator<(Segment s1, Segment s2) {
    return s1.index != s2.index && eval(s1) < eval(s2);
}

int N;
vector<Segment> segments;
vector<Point> points;

int main() {
    ifstream fin("cowjump.in");
    ofstream fout("cowjump.out");

    fin >> N;
    segments.resize(N);
    points.reserve(2 * N);

    for (int i = 0; i < N; i++) {
        Segment s;
        fin >> s.p.x >> s.p.y >> s.q.x >> s.q.y;
        s.index = s.p.segindex = s.q.segindex = i;
        segments[i] = s;
        points.push_back(s.p);
        points.push_back(s.q);
    }

    sort(points.begin(), points.end());

    set<Segment> active;
    int ans1 = -1, ans2 = -1;

    for (int i = 0; i < 2 * N; i++) {
        ans1 = points[i].segindex;
        x = points[i].x;

        auto it = active.find(segments[ans1]);
        if (it != active.end()) {
            auto after = it, before = it;
            after++;
            if (before != active.begin() && after != active.end()) {
                before--;
                if (isect(segments[before->index], segments[after->index])) {
                    ans1 = before->index;
                    ans2 = after->index;
                    break;
                }
            }
            active.erase(it);
        } else {
            auto it = active.lower_bound(segments[ans1]);
            if (it != active.end() && isect(segments[it->index], segments[ans1])) {
                ans2 = it->index;
                break;
            }
            if (it != active.begin()) {
                it--;
                if (isect(segments[it->index], segments[ans1])) {
                    ans2 = it->index;
                    break;
                }
            }
            active.insert(segments[ans1]);
        }
    }

    if (ans1 > ans2) swap(ans1, ans2);
    int ans2_count = 0;

    for (int i = 0; i < N; i++) {
        if (segments[i].index != ans2 && isect(segments[i], segments[ans2])) {
            ans2_count++;
        }
    }

    fout << (ans2_count > 1 ? ans2 + 1 : ans1 + 1) << "\n";

    return 0;
}
