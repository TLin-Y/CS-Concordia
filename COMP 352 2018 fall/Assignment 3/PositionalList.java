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

public interface PositionalList<E> {
    int size();
    boolean isEmpty();
    Position<E> first();
    Position<E> last();
    Position<E> before(Position<E> p) throws IllegalArgumentException;
    Position<E> after(Position<E> p) throws IllegalArgumentException;
    Position<E> addFirst(E e);
    Position<E> addLast(E e);
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;
    E set(Position<E> p, E e) throws IllegalArgumentException;
    E remove(Position<E> p) throws IllegalArgumentException;
}
