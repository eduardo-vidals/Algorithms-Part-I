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
public class MinPQ<Key> implements Iterable<Key> {

    private Comparator comparator;
    private Key[] pq;
    private int n;

    public MinPQ(int capacity) {
        pq = (Key[]) new Object[capacity];
        n = 0;
    }

    public MinPQ() {
        this(1);
    }

    public MinPQ(int capacity, Comparator comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[capacity];
        n = 0;
    }

    public MinPQ(Comparator comparator) {
        this(1, comparator);
    }

    public MinPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[n + 1];
        for (int i = 0; i < n; i++) {
            pq[i + 1] = keys[i];
        }

        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }

        assert isMinHeap();
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key getMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow.");
        }

        return pq[1];
    }

    private void resize(int capacity) {
        Key[] copy = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            copy[i] = pq[i];
        }
        pq = copy;
    }

    public void insert(Key item) {
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }

        pq[++n] = item;
        swim(n);
        assert isMinHeap();
    }

    public Key delMin() {
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        if ((n > 0) && (n == (pq.length - 1 / 4))) {
            resize(pq.length / 2);
        }
        assert isMinHeap();
        return min;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) {
                j++;
            }

            if (!greater(k, j)) {
                break;
            }

            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private boolean isMinHeap() {
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) {
                return false;
            }
        }

        for (int i = n + 1; i < pq.length; i++) {
            if (pq[i] != null) {
                return false;
            }
        }

        if (pq[0] != null) {
            return false;
        }

        return isMinHeapOrdered(1);
    }

    private boolean isMinHeapOrdered(int k) {
        if (k > n) {
            return true;
        }

        int left = 2 * k;
        int right = 2 * k + 1;

        if (left <= n && greater(k, left)) {
            return false;
        }

        if (right <= n && greater(k, right)) {
            return false;
        }

        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        MinPQ<Key> copy;

        public HeapIterator() {
            if (comparator == null) {
                copy = new MinPQ<>(size());
            } else {
                copy = new MinPQ<>(size(), comparator);
            }
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return copy.delMin();
        }

    }

    public static void main(String[] args) {
        MinPQ<Integer> heap = new MinPQ();
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
        for (Integer value : heap){
            System.out.print(value + " ");
        }
    }

}
