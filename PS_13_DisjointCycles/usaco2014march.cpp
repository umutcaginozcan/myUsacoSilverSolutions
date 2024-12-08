#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>
using namespace std;

int main()
{
    ifstream fin("reorder.in");
    ofstream fout("reorder.out");

    int N;
    fin >> N;

    vector<int> A(N + 1, 0);
    vector<int> whereInA(N + 1, 0);
    for (int i = 1; i <= N; i++) {
        fin >> A[i];
        whereInA[A[i]] = i;
    }

    vector<int> B(N + 1, 0);
    for (int i = 1; i <= N; i++)
        fin >> B[i];

    vector<int> visited(N + 1, 0);

    int cnt = 0, len = -1;
    for (int i = 1; i <= N; i++) {
        if (visited[i] == 1) continue;

        int j = i, size = 0;
        while (visited[j] == 0) {
            visited[j] = 1;
            size++;
            j = whereInA[B[j]];
        }

        if (size > 1) {
            cnt++;
            len = max(len, size);
        }
    }

    fout << cnt << " " << len;
    return 0;
}