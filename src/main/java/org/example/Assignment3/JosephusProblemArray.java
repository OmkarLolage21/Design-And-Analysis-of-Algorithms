package org.example;

import java.util.*;
class JosephusProblemArray {
    public static int findJosephusWinner(int n, int k) {
        List<Integer> players = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            players.add(i);
        }
        int index = 0;
        while (players.size() > 1) {
            index = (index + k - 1) % players.size();
            players.remove(index);
        }

        return players.get(0);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of players (N): ");
        int n = scanner.nextInt();

        int winner = findJosephusWinner(n, 2);
        System.out.println("The winner is player: " + winner);

        scanner.close();
    }
}