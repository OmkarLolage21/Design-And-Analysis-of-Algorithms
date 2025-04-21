package org.example.Assignment9;

import java.util.*;

class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {
    char data;
    int freq;
    HuffmanTreeNode left, right;

    public HuffmanTreeNode(char character, int frequency) {
        data = character;
        freq = frequency;
        left = right = null;
    }

    public int compareTo(HuffmanTreeNode other) {
        return freq - other.freq;
    }
}

class HuffmanCodes {
    static final int MAX_SIZE = 100;

    static void printCodes(HuffmanTreeNode root, int[] arr, int top) {
        if (root.left != null) {
            arr[top] = 0;
            printCodes(root.left, arr, top + 1);
        }
        if (root.right != null) {
            arr[top] = 1;
            printCodes(root.right, arr, top + 1);
        }
        if (root.left == null && root.right == null) {
            System.out.print(root.data + " ");
            for (int i = 0; i < top; ++i) {
                System.out.print(arr[i]);
            }
            System.out.println();
        }
    }

    static HuffmanTreeNode generateTree(PriorityQueue<HuffmanTreeNode> pq) {
        while (pq.size() != 1) {
            HuffmanTreeNode left = pq.poll();
            HuffmanTreeNode right = pq.poll();
            HuffmanTreeNode node = new HuffmanTreeNode('$', left.freq + right.freq);
            node.left = left;
            node.right = right;
            pq.add(node);+qq
        }
        return pq.poll();
    }

    static void HuffmanCodes(char[] data, int[] freq, int size) {
        PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue<>();
        for (int i = 0; i < size; i++) {
            pq.add(new HuffmanTreeNode(data[i], freq[i]));
        }
        HuffmanTreeNode root = generateTree(pq);
        int[] arr = new int[MAX_SIZE];
        int top = 0;
        printCodes(root, arr, top);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of characters: ");
        int size = scanner.nextInt();
        char[] data = new char[size];
        int[] freq = new int[size];
        System.out.println("Enter characters and their frequencies:");
        for (int i = 0; i < size; i++) {
            data[i] = scanner.next().charAt(0);
            freq[i] = scanner.nextInt();
        }
        HuffmanCodes(data, freq, size);
    }
}
