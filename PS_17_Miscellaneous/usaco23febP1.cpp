#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

using ll = long long;

// Helper: Compute ceil(a/b) when b > 0.
inline ll ceil_div(ll a, ll b) {
    // When a is nonnegative, this formula works.
    if(a >= 0)
        return (a + b - 1) / b;
    // For negative a, C++ division truncates toward 0 which here equals the ceiling.
    return a / b;
}
 
// Helper: Compute floor(a/b) when b > 0.
inline ll floor_div(ll a, ll b) {
    if(a >= 0)
        return a / b;
    // For negative a, adjust to get the mathematical floor.
    return -(( -a + b - 1 ) / b);
}
 
int main() {
    int T;
    cin >> T;

    for (int tc = 0; tc < T; tc++) {
        int N;
        ll X, Y;
        cin >> N >> X >> Y;
        vector<ll> A(N), B(N), C(N);
        for (int i = 0; i < N; i++){
            cin >> A[i] >> B[i] >> C[i];
        }

        auto check = [&](ll w) -> bool {
            ll d = X + Y - w;
            ll lx = max(1LL, X - w);
            ll hx = min(d - 1, X);

            for (int i = 0; i < N; i++){
                ll a = A[i], b = B[i], c = C[i];
                if(b - a > 0){
                    ll num = -c + b * d;
                    ll denom = b - a; // positive
                    ll new_lx = ceil_div(num, denom);
                    lx = max(lx, new_lx);
                }
                else if(a - b > 0){
                    ll num = c - b * d;
                    ll denom = a - b; // positive
                    ll new_hx = floor_div(num, denom);
                    hx = min(hx, new_hx);
                }
                else {
                    if(a * d > c)
                        return false;
                }
            }
            return lx <= hx;
        };

        ll lo = 0, hi = X + Y - 2;
        while(hi > lo) {
            ll mid = (lo + hi) / 2;
            if(check(mid))
                hi = mid;
            else
                lo = mid + 1;
        }
 
        cout << lo << "\n";
    }
    return 0;
}
