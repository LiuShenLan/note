import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
    private Node first; // 链表的首结点
    
    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
        // N == 0;
    }

    public int size() {
        return N;
    }

    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            ;
        }
    }
}
