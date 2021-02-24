/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.stacks;

import java.util.Iterator;

/**
 *
 * @author Eduardo
 */
public class StackArray<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 8;
    private Item[] stack;
    private int n;

    public StackArray() {
        stack = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void push(Item data) {

        if (n == stack.length) {
            resize(stack.length * 2);
        }

        stack[n++] = data;

    }

    public Item pop() {

        Item item = stack[n - 1];
        stack[n - 1] = null;
        n--;

        if (n > 0 && n == stack.length / 4) {
            resize(stack.length / 2);
        }

        return item;

    }
    
    

    private void resize(int capacity) {

        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < n; i++) {
            copy[i] = stack[i];
        }

        stack = copy;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append(stack[i]).append(" ");
        }

        return sb.toString();

    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {
        
        private Item[] copy;
        private int i;
        
        public StackIterator(){
            copy = (Item[]) new Object[n];
            
            for (int i = 0; i < n; i++){
                copy[i] = stack[i];
            } 
            
        }

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException();
            }

            return copy[i++];

        }

    }

    public static void main(String[] args) {
        StackArray<Integer> stack = new StackArray();

        stack.push(2);
        stack.push(4);
        stack.push(5);
        stack.pop();

        System.out.println(stack);
        System.out.println("size: " + stack.size());
    }

}
