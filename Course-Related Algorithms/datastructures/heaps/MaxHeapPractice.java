/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.heaps;

/**
 *
 * @author Eduardo
 */
public class MaxHeapPractice {

    private int n;
    private int[] heap;
    private static final int INIT_CAPACITY = 8;

    public MaxHeapPractice() {
        n = 0;
        heap = new int[INIT_CAPACITY + 1];
        heap[0] = Integer.MAX_VALUE;
    }

    private int parent(int pos) {
        return (pos / 2);
    }

    private int leftChild(int pos) {
        return (pos * 2);
    }

    private int rightChild(int pos) {
        return (pos * 2) + 1;
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

        if (isLeaf(pos)) {
            return;
        }

        if (heap[pos] < heap[leftChild(pos)] || heap[pos] < heap[rightChild(pos)]) {

            if (heap[leftChild(pos)] > heap[rightChild(pos)]) {
                swap(heap, pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            } else {
                swap(heap, pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }

        }

    }

    public void insert(int element) {
        heap[++n] = element;
        int current = n;

        while (heap[current] > heap[parent(current)]) {
            swap(heap, current, parent(current));
            current = parent(current);
        }
    }

    public int extractMax() {

        int max = heap[1];
        heap[1] = heap[n--];
        heap[n + 1] = 0;
        maxHeapify(1);
        return max;

    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            sb.append(heap[i]).append(" ");
        }

        return sb.toString();

    }

    public static void main(String[] args) {

        MaxHeapPractice heap = new MaxHeapPractice();
        
        heap.insert(2);
        heap.insert(4);
        heap.insert(5);
        heap.insert(10);
        heap.insert(6);
        heap.insert(12);
        
        heap.extractMax();

        System.out.println(heap);
    }

}
