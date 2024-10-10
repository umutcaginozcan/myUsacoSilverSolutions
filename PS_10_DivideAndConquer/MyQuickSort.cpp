#include <iostream>
#include <vector>
using namespace std;

void quickSort(vector<int>& nums, int start, int end) {
  if (start >= end) {
    return; // Base case: If the part of the vector has 0 or 1 element, it's already sorted.
  }

  int pivot = nums[(start + end) / 2]; // Using the middle element as the pivot.
  vector<int> L, R;
  int pivotCount = 0;

  for (int i = start; i <= end; i++) {
    if (nums[i] < pivot) {
      L.push_back(nums[i]);
    } else if (nums[i] > pivot) {
      R.push_back(nums[i]);
    } else {
      pivotCount++;  // Count how many times the pivot appears
    }
  }

  // Recursively sort the left and right partitions
  quickSort(L, 0, L.size() - 1);
  quickSort(R, 0, R.size() - 1);

  // Merging results back to the original vector
  int idx = start;
  for (int num : L) {
    nums[idx++] = num;
  }
  // Place the correct number of pivot elements back
  while (pivotCount--) {
    nums[idx++] = pivot;
  }
  for (int num : R) {
    nums[idx++] = num;
  }
}

int main() {
  vector<int> nums = {10, 5, 17, 9, 6, -10, -39, 5};
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
