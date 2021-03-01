/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.heaps.generics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Eduardo
 */
public class MaxPQPractice<Key> implements Iterable<Key> {

    private Comparator comparator;
    private Key[] pq;
    private int n;

    public MaxPQPractice(int capacity) {
        pq = (Key[]) new Object[capacity + 1];
        n = 0;
    }

    public MaxPQPractice() {
        this(1);
    }

    public MaxPQPractice(int capacity, Comparator comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[capacity + 1];
    }

    public MaxPQPractice(Comparator comparator) {
        this(1, comparator);
    }

    public MaxPQPractice(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[n + 1];
        for (int i = 0; i < keys.length; i++) {
            pq[i + 1] = keys[i];
        }

        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
        
        assert isMaxHeap();
    }
    
    public boolean isEmpty(){
        return n == 0;
    }
    
    public int size(){
        return n;
    }
    
    public Key getMax(){
        if (isEmpty()){
            throw new NoSuchElementException("Priority queue underflow.");
        }
        
        return pq[1];
    }
    
    public void resize(int capacity){
        assert capacity > n;
        Key[] copy = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++){
            copy[i] = pq[i];
        }
        pq = copy;
    }
    
    public void insert(Key item){
        if (n == pq.length - 1){
            resize(2 * pq.length);
        } 
        
        pq[++n] = item;
        swim(n);
        assert isMaxHeap();
    }
    
    public Key delMax(){
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        
        if ((n > 0) && n == (pq.length - 1) / 4){
            resize(pq.length / 2);
        }
        
        assert isMaxHeap();
        return max;
    }
    
    private void swim(int k){
        while (k > 1 && less(k / 2, k)){
            exch(k, k / 2);
            k = k / 2;
        }
    }
    
    private void sink(int k){
        while (2 * k <= n){
            int j = 2 * k;
            if (j < n && less(j, j + 1)){
                j++;
            }
            if (!less(k, j)){
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        if (comparator == null){
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
    
    private boolean isMaxHeap(){
        for (int i = 1; i <= n; i++){
            if (pq[i] == null){
                return false;
            }
        }
        
        for (int i = n + 1; i < pq.length; i++){
            if (pq[i] != null){
                return false;
            }
        }
        
        if (pq[0] != null){
            return false;
        }
        
        return isMaxHeapOrdered(1);
    }
    
    private boolean isMaxHeapOrdered(int k){
        if (k > n){
            return true;
        }
        
        int left = k * 2;
        int right = k * 2 + 1;
        
        if (left <= n && less(k, left)){
            return false;
        }
        
        if (right <= n && less(k, right)){
            return false;
        }
        
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }
    
    private class HeapIterator implements Iterator<Key> {
        
        
        public HeapIterator(){
            if (comparator == null){
                
            }
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Key next() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }

    public static void main(String[] args) {
        MaxPQPractice<Integer> heap = new MaxPQPractice();
        heap.insert(2);
        heap.insert(1);
        heap.insert(5);
        heap.insert(10);
        heap.insert(6);
        heap.insert(3);
        heap.insert(3);
        heap.insert(3);
        
        int[] sortedArr = new int[heap.size()];
        int index = heap.size();
        for (int i = 0; i < index; i++){
            sortedArr[i] = heap.delMax();
        }
        System.out.println(Arrays.toString(sortedArr)); 
    }
}