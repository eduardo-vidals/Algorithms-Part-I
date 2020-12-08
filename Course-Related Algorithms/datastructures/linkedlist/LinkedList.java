/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author EduardoPC
 */
public class LinkedList<Item> implements Iterable<Item> {

    private Node head;

    private class Node {
        Item data;
        Node next;
    }

    private void push(Item data) {
        Node node = new Node();
        node.data = data;
        node.next = head;
        head = node;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {

        private Node current = head;

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
            Item item = current.data;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> s = new LinkedList<>();
        s.push(2);
        s.push(4);
        System.out.println(s);
    }

}
