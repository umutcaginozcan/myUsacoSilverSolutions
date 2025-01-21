#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>

using namespace std;

#define NMAX 100005

struct Event {
    int x;     // Bu "event"in konumu
    int inc;   // Konumda katman sayısını +1 veya -1 arttıracak mı
    bool operator<(Event const& e) const {
        return x < e.x; // Event’leri konumlarına göre sıralayacağız
    }
};

int main() {
    ifstream input("paint.in");
    ofstream output("paint.out");

    int n, k;
    input >> n >> k;

    vector<Event> events(2 * n);

    int x = 0; // Bessie'nin anlık konumu; başlangıçta 0

    for (int i = 0; i < n; i++) {
        int dist;
        char c;
        input >> dist >> c;

        // Yeni konum:
        int x1 = x + dist * (c == 'L' ? -1 : 1);

        // Daha küçük ve büyük konumları buluyoruz
        int left  = min(x, x1);
        int right = max(x, x1);

        // events dizisine, bu aralığın başlangıcında +1,
        // bitişinde -1 olacak şekilde iki event ekliyoruz
        events[2 * i] = {left, 1};
        events[2 * i + 1] = {right, -1};

        // Bessie’nin konumu, x1'e güncelleniyor
        x = x1;
    }

    // Event'leri (aralık başlangıç ve bitiş noktalarını) x'e göre sıralıyoruz
    sort(events.begin(), events.end());

    int nCoats = 0;   // Şu anki katman sayısı
    int answer = 0;   // En az K kat boyalı toplam mesafe

    for (size_t i = 0; i < events.size(); i++) {
        if (i > 0 && nCoats >= k) {
            // events[i].x (şimdiki konum) - events[i-1].x (bir önceki konum)
            // arada geçen mesafeyi topluyoruz
            answer += (events[i].x - events[i - 1].x);
        }
        // nCoats'ı bu event'in inc değeri kadar güncelliyoruz
        nCoats += events[i].inc;
    }

    output << answer << endl;

    return 0;
}