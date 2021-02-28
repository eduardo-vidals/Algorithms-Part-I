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
    private final int INIT_CAPACITY = 8;
    
    public MinHeapPractice(){
        n = 0;
        heap = new int[INIT_CAPACITY];
        heap[0] = Integer.MIN_VALUE;
    }
    
    private int parent(int pos){
        return (pos / 2);
    }
    
    private int leftChild(int pos){
        return (pos * 2);
    }
    
    private int rightChild(int pos){
        return (pos * 2) + 1;
    }
    
    private boolean isLeaf(int pos){
        return pos >= n / 2 && pos <= n;
    }
    
    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private int size(){
        return n;
    }
    
    private void minHeapify(int pos){
        
        if (isLeaf(pos)){
            return;
        }
        
        if (heap[pos] > heap[leftChild(pos)] || heap[pos] > heap[rightChild(pos)]){
            if (heap[leftChild(pos)] < heap[rightChild(pos)]){
                swap(heap, pos, leftChild(pos));
                minHeapify(leftChild(pos));
            } else {
                swap(heap, pos, rightChild(pos));
                minHeapify(rightChild(pos));
            }
        }
        
    }
    
    public void insert(int element){
        heap[++n] = element;
        int current = n;
        
        while (heap[current] < heap[parent(current)]){
            swap(heap, current, parent(current));
            current = parent(current);
        }
        
    }
    
    public int extractMin(){
        int min = heap[1];
        heap[1] = heap[n];
        heap[n] = 0;
        minHeapify(1);
        n--;
        return min;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i <= n; i++){
            sb.append(heap[i]).append(" ");
        }
        
        return sb.toString();
    }
    

    public static void main(String[] args) {
        MinHeapPractice heap = new MinHeapPractice();

        heap.insert(2);
        heap.insert(1);
        heap.insert(5);
        heap.insert(10);
        heap.insert(6);
        heap.insert(3);
        
        System.out.println(heap);

       int[] arr = new int[heap.size()];
        int index = heap.size();
        for (int i = 0; i < index; i++){
            arr[i] = heap.extractMin();
        }
        
        System.out.println(Arrays.toString(arr));
        System.out.println(heap);
    }

}
