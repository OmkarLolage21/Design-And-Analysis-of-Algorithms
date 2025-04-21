package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class MajoritySearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter array elements: ");
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        // 13 27 20 17 36 70 74
        //1 2 2 2 3 5 1
        System.out.println("Brute "+bruteForce(arr,n));
        System.out.println("Bayer-Moore: "+ votingAlgo(arr,n));
        System.out.println("Sorting "+betterSol(arr,n));
        System.out.println("Map-based: "+optimalSol(arr,n));
    }
    public static int bruteForce(int arr[], int n){
        for (int i = 0; i < n; i++) {
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }
            if (count > n / 2) {
                return arr[i];
            }
        }
        return -1;
    }
    public static int betterSol(int[] arr, int n){
        Arrays.sort(arr);
        int count = 1;
        for (int i=1;i<n;i++){
            if(arr[i-1] == arr[i]){
                count++;
            }else {
                if (count > n / 2) {
                    return arr[i - 1];
                }
                count = 1;
            }
        }
        if (count > n / 2) {
            return arr[n - 1];
        }
        return -1;
    }
    public static int optimalSol(int[] arr, int n){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<n;i++){
            map.put(arr[i], map.getOrDefault(arr[i],0)+1);
            if (map.get(arr[i]) > n / 2) {
                return arr[i];
            }
        }
        return -1;
    }
//    1 1 2 1 3 5 1
//    1 2 2 2 3 5 1
    public static int votingAlgo(int[] arr, int n){
        int candidate = -1;
        int count = 0;

        for (int num : arr) {
//            System.out.println("candidate "+candidate);
//            System.out.println("count "+count);
//            System.out.println("num "+num);
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        for (int num : arr) {
            if (num == candidate) {
                count++;
            }
        }
        if (count > n / 2) {
            return candidate;
        } else {
            return -1;
        }
    }
}
