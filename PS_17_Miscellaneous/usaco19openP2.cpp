#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

struct Point {
    int x, y;
    // Returns the determinant of the 3x3 matrix formed by points (this, p2, p3)
    int orientation(const Point &p2, const Point &p3) const {
        return (x * p2.y - p2.x * y)
             + (p2.x * p3.y - p3.x * p2.y)
             + (p3.x * y - x * p3.y);
    }
};

// Returns true if point q lies on segment pr
bool onSegment(const Point &p, const Point &q, const Point &r) {
    return (q.x <= max(p.x, r.x) && q.x >= min(p.x, r.x) &&
            q.y <= max(p.y, r.y) && q.y >= min(p.y, r.y));
}

struct LineSegment {
    Point p1, p2;

    // Returns true if this segment intersects segment L2
    bool intersects(const LineSegment &L2) const {
        int o1 = p1.orientation(p2, L2.p1);
        int o2 = p1.orientation(p2, L2.p2);
        int o3 = L2.p1.orientation(L2.p2, p1);
        int o4 = L2.p1.orientation(L2.p2, p2);

        // General case: segments straddle each other.
        if (o1 * o2 < 0 && o3 * o4 < 0)
            return true;

        // Special cases: check for collinearity.
        if (o1 == 0 && onSegment(p1, L2.p1, p2)) return true;
        if (o2 == 0 && onSegment(p1, L2.p2, p2)) return true;
        if (o3 == 0 && onSegment(L2.p1, p1, L2.p2)) return true;
        if (o4 == 0 && onSegment(L2.p1, p2, L2.p2)) return true;

        return false;
    }
};

// Comparator function to sort based on p1.x values
bool compareLineSegments(const LineSegment &a, const LineSegment &b) {
    return a.p1.x < b.p1.x;
}

int main() {
    ifstream fin("cowjump.in");
    ofstream fout("cowjump.out");

    int N;
    fin >> N;
    vector<LineSegment> lsegments;

    for (int i = 0; i < N; i++) {
        Point p1, p2;
        fin >> p1.x >> p1.y >> p2.x >> p2.y;
        // Ensure that p1 is the leftmost point
        if (p1.x > p2.x) swap(p1, p2);
        lsegments.push_back({p1, p2});
    }

    // Sort segments by their starting x-coordinate
    sort(lsegments.begin(), lsegments.end(), compareLineSegments);

    int ans = -1;
    // For each segment, count how many later segments it intersects.
    for (int i = 0; i < N; i++) {
        int cnt = 0;
        for (int j = i + 1; j < N; j++) {
            // If the next segment's starting x-coordinate is beyond
            // the current segment's ending x-coordinate, no intersection is possible.
            if (lsegments[j].p1.x > lsegments[i].p2.x) break;
            if (lsegments[i].intersects(lsegments[j])) cnt++;
        }
        ans = max(ans, cnt);
        if (ans > 1) break;
    }

    fout << ans << endl;
    return 0;
}
