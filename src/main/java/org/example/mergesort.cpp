#include <iostream>
#include <vector>
#include <queue>
#include <cmath> // for ceil
#include <algorithm> // for sort

using namespace std;

// Function to merge k sorted parts using a priority queue
vector<int> merge(vector<int>& arr, int k, int part_size, int lowBound, int highBound) {
    // Priority queue to track the smallest elements in each part
    auto customCompare = [&arr](int a, int b) {
        return arr[a] > arr[b]; // Min-Heap (smaller elements have higher priority)
    };

    priority_queue<int, vector<int>, decltype(customCompare)> pq(customCompare);

    // Push the starting indices of all k parts into the priority queue
    for (int i = 0; i < k; i++) {
        int startIdx = lowBound + i * part_size;
        if (startIdx < highBound) { // Ensure the index is within bounds
            pq.push(startIdx);
        }
    }

    // Calculate the total number of elements in this range
    vector<int> tempSorted;
    tempSorted.reserve(highBound - lowBound);

    // Merge the k sorted parts
    while (!pq.empty()) {
        int ptr = pq.top(); // Get the smallest element's index
        pq.pop();

        // Add the smallest element to the sorted array
        tempSorted.push_back(arr[ptr]);

        // Move to the next element in the same part
        int nextIdx = ptr + 1;
        if (nextIdx < lowBound + ((ptr - lowBound) / part_size + 1) * part_size && nextIdx < highBound) {
            pq.push(nextIdx);
        }
    }

    return tempSorted;
}

// Recursive K-way merge sort function
vector<int> mergeSort(vector<int>& arr, int k, int low, int high) {
    if (high - low <= 1) { 
        // Base case: single-element array is already sorted
        return vector<int>(arr.begin() + low, arr.begin() + high);
    }

    int part_size = ceil((double)(high - low) / k);
    vector<vector<int>> sortedParts;

    // Create k sorted parts
    for (int i = 0; i < k; i++) {
        int start = low + i * part_size;
        int end = min(start + part_size, high);

        if (start < high) { // Avoid unnecessary recursion
            vector<int> sortedPart = mergeSort(arr, k, start, end);
            sortedParts.push_back(sortedPart);
        }
    }

    // Merge the sorted parts using the priority queue
    vector<int> mergedArray;
    for (int i = 0; i < sortedParts.size(); i++) {
        mergedArray.insert(mergedArray.end(), sortedParts[i].begin(), sortedParts[i].end());
    }

    // Sort the merged array
    sort(mergedArray.begin(), mergedArray.end());

    return mergedArray;
}

int main() {
    vector<int> arr;
    int n, k;

    // Input array size and number of parts
    cout << "Enter the size of the array: ";
    cin >> n;
    cout << "Enter the number of parts (k): ";
    cin >> k;

    // Input the array elements
    cout << "Enter the elements of the array: ";
    arr.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    // Perform K-way merge sort
    vector<int> sortedArray = mergeSort(arr, k, 0, n);

    // Output the sorted array
    cout << "Sorted array: ";
    for (int num : sortedArray) {
        cout << num << " ";
    }
    cout << endl;

    return 0;
}
