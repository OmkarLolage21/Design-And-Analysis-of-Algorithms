package org.example.Assignment5;

import java.util.Scanner;

public class FastModularExponentiation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a =  sc.nextInt();
        int b = sc.nextInt();
        int n = sc.nextInt();

        int result = Brute(a, b, n);

        System.out.println("Iterative: "+a + "^" + b + " mod " + n + " = " + result);

        result = mod_exp(a, b, n);

        System.out.println("Recursive: "+a + "^" + b + " mod " + n + " = " + result);
    }
    public static int Brute(int a, int b, int n) {
        long result = 1;

        for (int i = 0; i < b; i++) {
            result = (result * a) % n;
        }
        return (int) result;
    }
    public static int mod_exp(int a, int b, int n) {
        if (b == 1) return a % n;

        int half = mod_exp(a, b / 2, n); // 3 5 7
        System.out.println(half);
        if (b % 2 == 0) {
            System.out.println("return: "+(half * half) % n);
            return (half * half) % n;
        } else {
            System.out.println("return: "+ (half * half % n * a % n) % n);
            return (half * half % n * a % n) % n;
        }
    }

}




