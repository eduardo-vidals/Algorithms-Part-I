/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Eduardo
 */
public class StackArrayPractice<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 8;
    private Item[] stack;
    private int n;

    public StackArrayPractice() {
        stack = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int size) {
        Item[] copy = (Item[]) new Object[size];

        for (int i = 0; i < n; i++) {
            copy[i] = stack[i];
        }

        stack = copy;
    }

    public void push(Item data) {
        if (n == stack.length) {
            resize(stack.length * 2);
        }

        stack[n++] = data;
    }

    public Item pop() {
        Item popped = stack[n - 1];
        stack[n - 1] = null;
        n--;

        if (n > 0 && n == stack.length / 4) {
            resize(stack.length / 2);
        }

        return popped;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for (Item data : this){
            sb.append(data).append(" ");
        }
        
        return sb.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {

        private int i = 0;
        private Item[] copy;

        public StackIterator() {
            copy = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
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
                throw new NoSuchElementException();
            }

            return copy[i++];
        }

    }

    public static void main(String[] args) {

        StackArrayPractice<Integer> stack = new StackArrayPractice<>();
        stack.push(2);
        stack.push(4);
        stack.push(5);
        System.out.println("popped: " + stack.pop());
        System.out.println("stack: " + stack);
        System.out.println("size: " + stack.size());
    }

}
