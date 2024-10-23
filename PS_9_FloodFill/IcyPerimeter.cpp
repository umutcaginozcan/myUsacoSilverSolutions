#include <iostream>
#include <fstream>
#include <string>
#include <climits>
using namespace std;

const int MAXN = 1000;
int N;
char grid[MAXN][MAXN];
int xD[] = {-1, 0, 1, 0}, yD[] = {0, 1, 0, -1};
int bestA = INT_MIN, bestP = INT_MAX;
int A = 0, P = 0, commonEdges = 0;

void area(int x, int y) {
    A++;
    grid[x][y] = 'A'; // Mark as visited

    for (int i = 0; i < 4; i++) {
        int xx = x + xD[i];
        int yy = y + yD[i];

        if (xx >= 0 && xx < N && yy >= 0 && yy < N) {
            if (grid[xx][yy] == '#' || grid[xx][yy] == 'A') {
                commonEdges++;
            }
        }
    }

    for (int i = 0; i < 4; i++) {
        int xx = x + xD[i];
        int yy = y + yD[i];

        if (xx >= 0 && xx < N && yy >= 0 && yy < N) {
            if (grid[xx][yy] == '#') {
                area(xx, yy);
            }
        }
    }
}

int main() {
    ifstream fin("perimeter.in");
    fin >> N;

    string s;
    for (int i = 0; i < N; i++) {
        fin >> s;
        for (int j = 0; j < N; j++) {
            grid[i][j] = s[j];
        }
    }

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (grid[i][j] == '#') {
                area(i, j);
                commonEdges /= 2;
                P = 4 * A - 2 * commonEdges;
                if (A > bestA || (A == bestA && P < bestP)) {
                    bestA = A;
                    bestP = P;
                }
                commonEdges = 0, P = 0, A = 0;
            }
        }
    }
    ofstream fout("perimeter.out");
    fout << bestA << " " << bestP << "\n";
    return 0;
}
