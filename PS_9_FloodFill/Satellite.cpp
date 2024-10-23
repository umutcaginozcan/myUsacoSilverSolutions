#include <iostream>
#include <vector>
#include <string>
using namespace std;

vector<string> A;
int N, M, cnt;
int xD[] = {-1, 0, 1, 0};
int yD[] = {0, 1, 0, -1};

void floodFill(int x, int y) {
  cnt++;
  A[x][y] = '.';

  for (int i = 0; i < 4; i++) {
    int xx = x + xD[i];
    int yy = y + yD[i];

    if (xx < 0 || x >= N || y < 0 || y >= M)
      continue;

    if (A[xx][yy] == '*')
      floodFill(xx, yy);
  }
}

int main() {

  cin >> M >> N;
  A.resize(N);
  for (auto &str : A) {
    cin >> str;
  }

  int res = 0;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      if (A[i][j] == '*') {
        cnt = 0;
        floodFill(i, j);
        res = max(res, cnt);
      }
    }
  }

  cout << res;
}

/*

10 5
..*.....**
.**..*****
.*...*....
..****.***
..****.***

*/