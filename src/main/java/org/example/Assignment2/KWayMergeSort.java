package org.example.Assignment2;

import java.util.*;

class KWayMergeSort {

    public static List<Integer> merge(List<List<Integer>> sortedParts) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < sortedParts.size(); i++) {
            if (!sortedParts.get(i).isEmpty()) {
                minHeap.offer(new Element(i, 0, sortedParts.get(i).get(0)));
            }
        }

        while (!minHeap.isEmpty()) {
            Element smallest = minHeap.poll();
            result.add(smallest.value);

            int nextIndex = smallest.index + 1;
            if (nextIndex < sortedParts.get(smallest.part).size()) {
                minHeap.offer(new Element(smallest.part, nextIndex, sortedParts.get(smallest.part).get(nextIndex)));
            }
        }

        return result;
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
        return merge(sortedParts);
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

class Element {
    int part;
    int index;
    int value;

    Element(int part, int index, int value) {
        this.part = part;
        this.index = index;
        this.value = value;
    }
}
