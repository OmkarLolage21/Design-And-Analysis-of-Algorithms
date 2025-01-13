package org.example;

import java.util.Scanner;

public class Assignment1Problem2 {
    public static long countDerangements(int n) {
        if (n == 0) return 1;
        if (n == 1) return 0;

        long Dn_2 = 1;
        long Dn_1 = 0;
        long Dn = 0;

        // Use the recurrence relation D_n = (n-1) * (D_{n-1} + D_{n-2})
        for (int i = 2; i <= n; i++) {
            Dn = (i - 1) * (Dn_1 + Dn_2);
            Dn_2 = Dn_1;
            Dn_1 = Dn;
        }

        return Dn;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();

        long goodPermutations = countDerangements(n);
        System.out.printf("The number of good permutations (derangements) for n = %d is: %d\n", n, goodPermutations);

        scanner.close();
    }
}