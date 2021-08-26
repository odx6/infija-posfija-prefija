/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

/**
 *
 * @author ever
 */
/******************************************************************************
 *  Una queue generica, implementada usando una lista ligada.
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * La clase Queue representa una queue first-in-first-out (FIFO) de
 * items genericos. Implementa las usuales operaciones enqueue y dequeue,
 * junto con metodos para ver (peeking) el item en el tope, probar si la
 * queue esta vacia, conseguir el numero de items que la forman, e iterar
 * los items en orden FIFO.
 * 
 * Esta implementacion usa una lista simplemente-ligada con una clase anidada
 * para formar los nodos de la lista. las operaciones enqueue, dequeue, peek,
 * size e is-empty se ejecutaran en un tiempo constante en el peor caso.
 * 
 * @param <Item> es el tipo generico de un item en esta queue
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public class Queue<Item> implements Iterable<Item> {
    private int n;           // numero de elementos en la queue
    private Node first;      // comienzo de la queue
    private Node last;       // termino de la queue

    /** clase anidada para crear los nodos de la queue */
    private class Node {
        private Item item;   // el item en el nodo
        private Node next;   // referencia al siguiente item
    }

    /**
     * Inicializa una queue vacia
     */
    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * @return true si la queue esta vacia; false de otra manera
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * @return el numero de items en la queue
     */
    public int size() {
        return n;
    }

    /**
     * @return el numero de items en la queue
     */
    public int length() {
        return n;
    }

    /**
     * @return el item que llego primero
     * @throws NoSuchElementException si la queue esta vacia
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue desbordada.");
        return first.item;
    }

    /**
     * Agrega el item a la queue
     * @param item
     */
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

    /**
     * Elimina y retorna el item que llego primero a la queue
     *
     * @return el item que llego primero a la queue
     * @throws NoSuchElementException si la queue esta vacia
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue desbordada.");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // para evitar el merodeo
        return item;
    }

    /**
     * Retorna la representation string de la queue
     *
     * @return la secuencia de items en orden FIFO, separados por espacios
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }


    /**
     * @return un iterator que recorre los items en la queue en orden FIFO
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
	 * clase interna que programa el iterador que recorre la queue
	 * no implementa remove() puesto que es opcional
      */
    private class ListIterator implements Iterator<Item> {
        private Node current = first;  // nodo conteniendo el item actual

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
