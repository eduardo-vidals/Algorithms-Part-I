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
 * @author EduardoPC
 * @param <Item>
 */
 public class StackLinked<Item> implements Iterable<Item> {

    private Node first;
    private int n;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }
    
    public int size(){
        return n;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item).append(" ");
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        StackLinked<Integer> stack = new StackLinked<>();

        stack.push(3);
        stack.push(5);
        stack.push(6);
        stack.push(8);
        System.out.println(stack);
    }

}
