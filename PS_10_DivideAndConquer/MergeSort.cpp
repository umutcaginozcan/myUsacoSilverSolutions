#include <iostream>
#include <vector> // Include for std::vector
using namespace std;

vector<int> arr = { 12, 11, 13, 5, 6, 7 };

void merge(int left, int right)
{

  	int mid = (left + right) / 2;
    // Create temp vectors
    vector<int> temp;


    int i = left, j = mid + 1;

    // Merge the temp vectors back into arr[left..right]
    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) {
            temp.push_back(arr[i]);
            i++;
        }
        else {
            temp.push_back(arr[j]);
            j++;
        }
    }

    // Copy the remaining elements
    for (; i <= mid; i++) {
      temp.push_back(arr[i]);
    }

    for (; j <= right; j++) {
      temp.push_back(arr[j]);
    }

    for (int i = 0; i < temp.size(); i++) {
      arr[left + i] = temp[i];
    }
}

// begin is for left index and end is right index
// of the sub-array of arr to be sorted
void mergeSort(int left, int right)
{
    if (left >= right)
        return;

    int mid = left + (right - left) / 2;
    mergeSort(left, mid);
    mergeSort(mid + 1, right);
    merge(left, right);
}

// Function to print a vector
void printVector(const vector<int>& arr)
{
    for (const int i : arr)
        cout << i << " ";
    cout << endl;
}

// Driver code
int main()
{
    int arrSize = arr.size();

    cout << "Given vector is \n";
    printVector(arr);

    mergeSort(0, arrSize - 1);

    cout << "\nSorted vector is \n";
    printVector(arr);
    return 0;
}
