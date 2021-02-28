/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.heaps.generics;

import java.util.Comparator;
import java.util.Iterator;

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
        for (int i = 0; i < n; i++){
            pq[i + 1] = keys[i];
        }
        
        for (int k = n / 2; k >= 1; k--){
            //sink(k);
        }
        
        //assert isMaxHeap();
    }

    @Override
    public Iterator<Key> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
