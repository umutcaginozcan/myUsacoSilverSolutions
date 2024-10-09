#include <iostream>
using namespace std;

int findMax(int arr[], int size) {
    if (size <= 1) {
        return arr[0];
    } else {
        if (arr[size - 1] > findMax(arr, size - 1)) {
            return arr[size  - 1];
        } else {
            return findMax(arr, size - 1);
        }
    }

}

int main() {
    int arr[] = {10, 2, 30, -5, 150};
    int size = sizeof(arr) / sizeof(arr[0]);  // Correct way to calculate the number of elements
    int max = findMax(arr, size);
    cout << "Max element in the array is: " << max << endl;
    return 0;
}