#include <iostream>
#include <vector>
using namespace std;

void quickSort(vector<int>& nums, int start, int end) {
    if (start >= end) {
        return; // Base case: If the part of the vector has 0 or 1 element, it's already sorted.
    }

    int pivot = nums[(start + end) / 2]; // Using the middle element as the pivot.
    int i = start, j = end;

    while (i <= j) {
        while (nums[i] < pivot) i++; // Move right until an element >= pivot is found.
        while (nums[j] > pivot) j--; // Move left until an element <= pivot is found.

        if (i <= j) {
            swap(nums[i], nums[j]); // Swap the elements.
            i++;
            j--;
        }
    }

    // Recursively sort the two partitions.
    quickSort(nums, start, j);
    quickSort(nums, i, end);
}

int main() {
    vector<int> nums = {10, 5, 17, 9, 6, -10, -39};
    cout << "Before: ";
    for (int i : nums) {
        cout << i << " ";
    }
    cout << endl;

    quickSort(nums, 0, nums.size() - 1);

    cout << "After: ";
    for (int i : nums) {
        cout << i << " ";
    }
    cout << endl;

    return 0;
}
