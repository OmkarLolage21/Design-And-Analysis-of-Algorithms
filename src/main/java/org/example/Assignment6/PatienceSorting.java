package org.example.Assignment6;

import java.util.*;

public class PatienceSorting {
    public static List<Stack<Integer>> patienceSorting(int[] arr) {
        List<Stack<Integer>> piles = new ArrayList<>();

        for (int num : arr) {
            boolean placed = false;

            for (Stack<Integer> pile : piles) {
                if (pile.peek() >= num) {
                    pile.push(num);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                Stack<Integer> newPile = new Stack<>();
                newPile.push(num);
                piles.add(newPile);
            }
        }
        return piles;
    }
    public static int[] MergePiles(List<Stack<Integer>> piles, int n) {
        int[] result = new int[n];

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < piles.size(); i++) {
            if (!piles.get(i).isEmpty()) {
                heap.add(new int[]{piles.get(i).pop(), i});
            }
        }
        for (int i = 0; i < n; i++) {
            int[] min = heap.poll();
            if (min != null) {
                result[i] = min[0];
            }

            if (min != null && !piles.get(min[1]).isEmpty()) {
                heap.add(new int[]{piles.get(min[1]).pop(), min[1]});
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        List<Stack<Integer>> piles = patienceSorting(arr);

        int[] sortedArray = MergePiles(piles, n);

        System.out.println("Sorted Array:");
        System.out.println(Arrays.toString(sortedArray));
    }
}
