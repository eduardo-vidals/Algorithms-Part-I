/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.heaps;

import java.util.Arrays;

/**
 *
 * @author Eduardo
 */
public class MinHeapPractice {

    private int n;
    private int[] heap;
    private static final int INIT_CAPACITY = 8;

    public MinHeapPractice() {
        n = 0;
        heap = new int[INIT_CAPACITY];
    }

    private void resize(int size) {
        int[] copy = new int[size];

        for (int i = 1; i <= n; i++) {
            copy[i] = heap[i];
        }

        heap = copy;
    }

    public int size() {
        return n;
    }

    public void insert(int element) {
        if (n == heap.length - 1) {
            resize(2 * heap.length);
        }
        heap[++n] = element;
        swim(n);
    }

    public int extractMin() {
        int min = heap[1];
        exch(1, n--);
        heap[n + 1] = 0;
        sink(1);

        if ((n > 0) && (n == (heap.length - 1) / 4)) {
            resize(heap.length / 2);
        }
        return min;
    }

    private void exch(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void swim(int k) {
        while (k > 1 && heap[k / 2] > heap[k]) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;

            if (j < n && heap[j] > heap[j + 1]) {
                j++;
            }

            if (!(heap[k] > heap[j])) {
                break;
            }

            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap();

        heap.insert(2);
        heap.insert(1);
        heap.insert(5);
        heap.insert(10);
        heap.insert(6);
        heap.insert(3);
        heap.insert(3);
        heap.insert(3);

        int[] arr = new int[heap.size()];
        int index = heap.size();
        for (int i = 0; i < index; i++) {
            arr[i] = heap.extractMin();
        }

        System.out.println(Arrays.toString(arr));
    }

}
