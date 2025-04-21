package org.example;

import java.util.Scanner;

public class calculateAverageUpdates {
    public static double calculateAverageUpdates(int n) {
        int totalUpdates = n;
        for (int i = 1; i <= n - 1; i++) {
          totalUpdates += i * (i + 1);
        }
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
          factorial *= i;
        }
        System.out.println(totalUpdates + "/" + factorial);
        return (double) totalUpdates / factorial;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double averageUpdates = calculateAverageUpdates(n);
        System.out.println("Average number of updates to the max variable per permutation for n = " + n + " is: " + averageUpdates);
    }
}