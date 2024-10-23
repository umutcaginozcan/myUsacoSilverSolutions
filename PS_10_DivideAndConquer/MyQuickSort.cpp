#include <iostream>
#include <vector>
using namespace std;

vector<int> nums = {10, 5, 17, 9, 6, -10, -39, 5};
void quickSort(int left, int right) {

  if (left >= right)
    return;

  int j = left - 1;
  for (int i = left; i <= right; i++) {
    if (nums[i] <= nums[right]) {
      j++;
      swap(nums[i], nums[j]);
    }
  }

  quickSort(left, j - 1);
  quickSort(j + 1, right);
}

int main() {
  cout << "Before: ";
  for (int i : nums) {
    cout << i << " ";
  }
  cout << endl;

  quickSort(0, nums.size() - 1);

  cout << "After: ";
  for (int i : nums) {
    cout << i << " ";
  }
  cout << endl;

  return 0;
}
