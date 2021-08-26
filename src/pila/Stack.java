package pila;




import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *  La clase Stack representa un stack de items genericos last-in-first-out (LIFO).
 *  Implementa las operaciones usuales push y pop, junto con metodos para
 *  ver el item en el tope, probar si el stack esta vacio, conseguir el numero de
 *  items en el stack, e iterar sobre los items en orden LIFO.
 *  
 *  Esta implementacion usa una lista simplemente ligada con una clase anidada
 *  para crear los nodos de la lista.
 *  Las operaciones push, pop, peek, size, e is-empty tardan un tiempo constante
 *  en el peor caso.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Item> el tipo generico de un item en el stack
 */
public class Stack<Item> implements Iterable<Item> {
    private int n;          // tamaño del stack
    private Node first;     // tope del stack

    /** clase anidada para crear los nodos de la lista ligada */
    private class Node {
        private Item item;
        private Node next;
    }

   /**
     * Inicializa un stack vacio
     */
    public Stack() {
        first = null;
        n = 0;
    }

    /**
     * @return true si el stack esta vacio; false de otra manera
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * @return el numero de items en el stack
     */
    public int size() {
        return n;
    }

    /**
     * @param Agrega el item al stack
     */
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    /**
     * elimina y retorna el item mas recientemente agregado al stack
     *
     * @return the item mas recientemente agregado
     * @throws NoSuchElementException si el stack esta vacio
     */
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack desbordado.");
        Item item = first.item;        // recupera el item a retornar
        first = first.next;            // elimina el primer nodo
        n--;
        return item;                   // retorna el item recuperado
    }
     public Stack copia(Stack<Item> t){
         Stack<Item> copia = new Stack();
         Queue<Item> aux =new Queue();
         for(Item r:t)
         aux.enqueue(t.pop());
         for(Item r:aux){
             Item x= aux.dequeue();
             t.push(x);
             copia.push(x);
            }
            
         return copia;
        }
    /**
     * Retorna (sin eliminar) el item mas recientemente agregado al stack
     *
     * @return el item mas recientemente agregado al stack
     * @throws NoSuchElementException si el stack esta vacio
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack desbordado.");
        return first.item;
    }

    /**
     * Retorna una representacion string del stack
     *
     * @return la secuencia de items en el stack en orden LIFO, separado por espacios
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
       
    /**
     * @return un iterator para el stack que itera a traves de los items en orden LIFO
     */
    @Override
    public Iterator<Item> iterator()  { return new ListIterator();  }

    /** un iterator, no se implementa remove() puesto que es opcional */
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        @Override
        public boolean hasNext()  { return current != null;                     }
        @Override
        public void remove()      { throw new UnsupportedOperationException();  }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    
   
}
