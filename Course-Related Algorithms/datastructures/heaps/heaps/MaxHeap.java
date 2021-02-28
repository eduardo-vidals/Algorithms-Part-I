/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * @author Eduardo
 */
public class MaxHeap {

    private int[] heap;
    private int n;
    private static final int INIT_CAPACITY = 8;

    public MaxHeap() {
        n = 0;
        heap = new int[INIT_CAPACITY];
        heap[0] = Integer.MAX_VALUE;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        return pos >= (n / 2) && pos <= n;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void maxHeapify(int pos) {
        // If the element is a leaf, there is no need to check whether it has
        // children in heap order
        if (isLeaf(pos)) {
            return;
        }

        // Checks to see whether the top element is the max within the heap
        if (heap[pos] < heap[leftChild(pos)] || heap[pos] < heap[rightChild(pos)]) {

            // if the left child is greater, then it will move one spot up
            // after that, we will coontinue to max heapify at the position
            // we just swapped elements
            if (heap[leftChild(pos)] > heap[rightChild(pos)]) {
                swap(heap, pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            } // if the right child is greater, then it will move one spot up
            // after that, we will coontinue to max heapify at the position
            // we just swapped elements
            else {
                swap(heap, pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }

    }

    public void insert(int element) {
        heap[++n] = element;
        int current = n;

        // current will never be greater than max integer value
        // this is why we make heap[0] the greatest value
        while (heap[current] > heap[parent(current)]) {
            swap(heap, current, parent(current));
            current = parent(current);
        }
    }

    public int extractMax() {

        // exchange max value with lowest value in the heap
        // remove max value by making it equal to 0
        // max heapify from the top
        // return the max value that was removed
        int popped = heap[1];
        heap[1] = heap[n--];
        heap[n + 1] = 0;
        maxHeapify(1);
        return popped;
    }

    public void print() {
        for (int i = 1; i <= n / 2; i++) {
            System.out.print("PARENT: " + heap[i] + " LEFT CHILD: "
                    + heap[2 * i] + " RIGHT CHILD: " + heap[2 * i + 1]);
            System.out.println();
        }

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            sb.append(heap[i]).append(" ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();

        heap.insert(2);
        heap.insert(4);
        heap.insert(5);
        heap.insert(10);
        heap.insert(6);
        heap.insert(12);

        heap.extractMax();

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(10);
        pq.add(5);
        pq.add(2);
        pq.add(14);
        pq.add(11);

        System.out.println(pq);
    }

}
