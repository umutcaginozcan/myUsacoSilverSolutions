#include <iostream>
using namespace std;

/*
 * SOME USEFUL METHODS:
 * Checking if the j-th Bit is 1: if (mask & (1 << j))
 * Setting (Turning ON) the j-th Bit: mask |= (1 << j);
 * Clearing (Turning OFF) the j-th Bit: mask &= ~(1 << j);
 */
void generateSubsets(vector<int>& elements) {
    int n = elements.size();
    int total = 1 << n;

    for (int mask = 0; mask < total; mask++) {
        cout << "\"";
        for (int j = 0; j < n; j++) {
            if (mask & (1 << j)) cout << elements[j];
        }
        cout << "\"" << endl;
    }
}

int main()
{
    vector<int> elements = {1, 2, 3};
    generateSubsets(elements);
    return 0;
}

