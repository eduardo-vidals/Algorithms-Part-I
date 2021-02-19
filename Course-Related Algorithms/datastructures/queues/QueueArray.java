/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.queues;

import java.util.Iterator;

/**
 *
 * @author Eduardo
 */
public class QueueArray<Item> implements Iterable<Item> {
    
    private static final int INIT_CAPACITY = 8;
    private Item[] a;
    private int n;
    
    public QueueArray(){
        a = (Item[]) new Object[INIT_CAPACITY];
    }
    
    public boolean isEmpty(){
        return n == 0;
    }
    
    public int size(){
        return n;
    }
    
    public void enqueue(){
        
    }
    
    private void resize(int capacity){
        
        Item[] copy = (Item[]) new Object[capacity];
        
        for(int i = 0; i < n; i++){
            copy[i] = a[i];
        }
        
        a = copy;
        
    }

    @Override
    public Iterator<Item> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
