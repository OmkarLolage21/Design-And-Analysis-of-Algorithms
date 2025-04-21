package org.example.Assignment7;

import java.util.Scanner;

public class LargestBlockSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int [n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Maximum Block Sum Brute: " + bruteSum(arr,n));
        System.out.println("Maximum Block Sum Kadane: " + KadaneSum(arr,n));
    }
    public static int bruteSum(int[] arr, int n){
        int sum = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int currentSum = 0;
            for (int j = i; j < arr.length; j++) {
                currentSum = currentSum + arr[j];

                sum = Math.max(sum, currentSum);
            }
        }
        return sum;
    }
    public static int KadaneSum(int[] arr, int n){
        int sum = arr[0];
        int end = arr[0];

        for (int i = 1; i < arr.length; i++) {

            end = Math.max(end + arr[i], arr[i]);

            sum = Math.max(sum, end);
        }
        return sum;
    }
}