/**
 * Tianlin Yang
 * 40010303
 * Haitun Liao
 * 40080732
 * Reference:
 * 1 https://docs.oracle.com/javase/7/docs/api/java/util/List.html
 * 2 https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html
 * 3 https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 * 4 http://www.cs.nuim.ie/~tlysaght/CS210/code/LinkedLists/ListTester.java
 * 5 https://github.com/eclipse/jubula.core/blob/master/org.eclipse.jubula.rc.common/src/org/eclipse/jubula/rc/common/tester/ListTester.java
 */

public class LinkedPositionalList<E> implements PositionalList<E> {

    private static class Node<E> implements Position<E> {
        private E element; // reference to the element stored at this node
        private Node<E> prev; // reference to the previous node in the list
        private Node<E> next; // reference to the subsequent node in the list

        /**
         * Initialize of Node
         * @param e
         * @param p
         * @param n
         */
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        /**
         * Get Element From position
         * @return
         * @throws IllegalStateException
         */
        public E getElement() throws IllegalStateException {
            if (next == null)
                throw new IllegalStateException("Position no longer VALID");
            return element;
        }

        /**
         * Set element at position
         * @param element
         */
        public void setElement(E element) {
            this.element = element;
        }

        /**
         * Get previous element
         * @return
         */
        public Node<E> getPrev() {
            return prev;
        }

        /**
         * Set previous element
         * @param prev
         */
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        /**
         * Get next element
         * @return
         */
        public Node<E> getNext() {
            return next;
        }

        /**
         * Set next element
         * @param next
         */
        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    /**
     * Initialize LinkedPositionalList
     */
    public LinkedPositionalList() {
        head = new Node<>(null,null,null);
        tail = new Node<>(null,head,null);
        head.setNext(tail);
    }

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
        Node<E> node = (Node<E>) p;
        if (node.getNext() == null)
            throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }

    private Position<E> position(Node<E> node) {
        if (node == head || node == tail)
            return null; // do not expose user to the sentinels
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> first() {
        return position(head.getNext());
    }

    @Override
    public Position<E> last() {
        return position(tail.getPrev());
    }

    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e,pred,succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e,head,head.getNext());
    }

    @Override
    public Position<E> addLast(E e) {
        return addBetween(e,tail.getPrev(),tail);
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null); // help with garbage collection
        node.setNext(null); // and convention for defunct node
        node.setPrev(null);
        return answer;
    }
}
