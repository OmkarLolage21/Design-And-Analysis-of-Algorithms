package org.example;
import java.util.Scanner;
public class JosephusProblemLinkedLIst {
        static class Node {
            int player;
            Node next;

            Node(int player) {
                this.player = player;
                this.next = null;
            }
        }
        public static int findJosephusWinner(int n, int k) {
            Node head = new Node(1);
            Node prev = head;

            for (int i = 2; i <= n; i++) {
                prev.next = new Node(i);
                prev = prev.next;
            }
            prev.next = head;
            Node curr = head;
            while (curr.next != curr) {
                for (int count = 1; count < k - 1; count++) {
                    curr = curr.next;
                }
                curr.next = curr.next.next;
                curr = curr.next;
            }
            return curr.player;
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
