#include <iostream>
#include <vector>
#include <string>
using namespace std;

int main()
{
    // Read input strings
    string s, t;
    cin >> s >> t;
    int q;
    cin >> q;

    // Frequency arrays for s and t (size 26 for all lowercase letters)
    vector<int> freqS(26, 0), freqT(26, 0);
    for (char ch : s) freqS[ch - 'a']++;
    for (char ch : t) freqT[ch - 'a']++;

    // Pairwise compatibility table (-1 = not yet checked, 1 = compatible, 0 = incompatible)
    vector<vector<int>> compatible(26, vector<int>(26, -1));

    // Compute compatibility for each letter pair
    for (int i = 0; i < 26; i++) {
        for (int j = i + 1; j < 26; j++) {  // Only check pairs (i, j) where i < j
            string subsetS, subsetT;
            for (char ch : s) {
                if (ch == 'a' + i || ch == 'a' + j)
                    subsetS += ch;
            }
            for (char ch : t) {
                if (ch == 'a' + i || ch == 'a' + j)
                    subsetT += ch;
            }
            compatible[i][j] = (subsetS == subsetT) ? 1 : 0;  // Store 1 (true) or 0 (false)
        }
    }

    string finalAns;

    // Process queries
    while (q--) {
        string query;
        cin >> query;
        char answer = 'Y';

        // Check if total frequency of the subset is the same
        int sumS = 0, sumT = 0;
        for (char ch : query) {
            sumS += freqS[ch - 'a'];
            sumT += freqT[ch - 'a'];
        }
        if (sumS != sumT) {
            answer = 'N';
        }

        // Check pairwise compatibility
        for (size_t i = 0; i < query.size(); i++) {
            for (size_t j = i + 1; j < query.size(); j++) {
                int x = query[i] - 'a';
                int y = query[j] - 'a';

                if (compatible[x][y] == 0) {
                    answer = 'N';
                    break;
                }
            }
            if (answer == 'N') break;  // Stop early if found incompatible
        }

        finalAns += answer;
    }

    cout << finalAns;
    return 0;
}
