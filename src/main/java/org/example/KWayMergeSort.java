package org.example;

import java.util.*;

class KWayMergeSort {

    public static List<Integer> merge(List<Integer> arr, int k, int partSize, int lowBound, int highBound) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(arr::get));
        List<Integer> tempSorted = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            int startIdx = lowBound + i * partSize;
            if (startIdx < highBound) {
                minHeap.offer(startIdx);
            }
        }
        while (!minHeap.isEmpty()) {
            int ptr = minHeap.poll();
            tempSorted.add(arr.get(ptr));

            int nextIdx = ptr + 1;
            if (nextIdx < lowBound + ((ptr - lowBound) / partSize + 1) * partSize && nextIdx < highBound) {
                minHeap.offer(nextIdx);
            }
        }

        return tempSorted;
    }
    public static List<Integer> mergeSort(List<Integer> arr, int k, int low, int high) {
        if (high - low <= 1) {
            return new ArrayList<>(arr.subList(low, high));
        }

        int partSize = (int) Math.ceil((double) (high - low) / k);
        List<List<Integer>> sortedParts = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            int start = low + i * partSize;
            int end = Math.min(start + partSize, high);
            if (start < high) {
                sortedParts.add(mergeSort(arr, k, start, end));
            }
        }
        List<Integer> mergedArray = new ArrayList<>();
        for (List<Integer> part : sortedParts) {
            mergedArray.addAll(part);
        }
        Collections.sort(mergedArray);
        return mergedArray;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();
        System.out.print("Enter the number of parts (k): ");
        int k = scanner.nextInt();

        List<Integer> arr = new ArrayList<>();
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr.add(scanner.nextInt());
        }

        List<Integer> sortedArray = mergeSort(arr, k, 0, n);

        System.out.println("Sorted array: " + sortedArray);

        scanner.close();
    }
}