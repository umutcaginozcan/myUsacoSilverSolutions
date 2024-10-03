#include <iostream>
#include <fstream>
using namespace std;

static int A[5][5]; // The field
int K; // Number of squares we stepped on. 

int count(int i, int j) {
    int x;
    if (i < 0 || i > 4 || j < 0 || j > 4 || A[i][j] != 0) 
        return 0;
    
    A[i][j] = 1; 
    K++;

    // Success:
    if (K == 25 && i == 4 && j == 4) {
        x = 1;
    } else {
        // Continue Counting:
        x = count(i - 1, j) + count(i + 1, j) + count(i, j - 1) + count(i, j + 1);
    }

    A[i][j] = 0; 
    K--;
    return x;
}


int main() {
    int i, j, k;
    cin >> k;
    for (int idx = 0; idx < k; idx++) { // renamed to 'idx' to avoid confusion with the loop's 'i'
        cin >> i >> j;
        A[i - 1][j - 1] = 1; // Marking barren squares
    }

    K += k; // Initializing K
    cout << count(0, 0) << endl; // Starting from (0, 0)
    return 0;
}
