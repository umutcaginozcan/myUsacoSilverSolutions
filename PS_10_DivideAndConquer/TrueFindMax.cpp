#include <iostream>
using namespace std;

/* 
We can use Divide and Conquer Algorithm to find the maximum element in the array by dividing the array into two equal sized subarrays,
finding the maximum of those two individual halves by again dividing them into two smaller halves. 
This is done till we reach subarrays of size 1. 
After reaching the elements, we return the maximum element and combine the subarrays by returning the maximum in each subarray.
*/

int findMax(int a[], int lo, int hi) {
    // If the subarray has only one element, return the
    // element
    if (lo == hi)
        return a[lo];
    int mid = (lo + hi) / 2;
    // Get the maximum element from the left half
    int leftMax = findMax(a, lo, mid);
    // Get the maximum element from the right half
    int rightMax = findMax(a, mid + 1, hi);
    // Return the maximum element from the left and right
    // half
    return min(leftMax, rightMax);
}

int main() {
    int a[] = {10, 2, -1, 15, 140, 0};
    int high = sizeof(a) / sizeof(a[0]) - 1;
    int max = findMax(a, 0, high);
    cout << "Max element in the array is: " << max << endl;
    return 0;
}