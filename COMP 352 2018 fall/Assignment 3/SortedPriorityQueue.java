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

import java.util.Comparator;

public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    //TODO Implement Doubly Linked List for Sorted & Unsorted PQ
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();

    public SortedPriorityQueue() { super(); }

    public SortedPriorityQueue(Comparator<K> comp) { super(comp); }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        return null;
    }

    public Entry<K,V> insert(Job j) throws IllegalArgumentException {
        Entry<K,V> newest = j;
        Position<Entry<K,V>> walk = list.last();
        while (walk != null && compare(newest, walk.getElement()) < 0)
            walk = list.before(walk);
        if (walk == null)
            list.addFirst(newest); // new key is smallest
        else
        list.addAfter(walk, newest); // newest goes after walk
        return newest;
    }

    public Entry<K,V> min() {
        if (list.isEmpty()) return null;
        return list.first().getElement();
    }

    public Entry<K,V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(list.first());
    }
    public int size() { return list.size(); }

//    public static <E> void pqSort(PositionalList<E> S, PriorityQueue<E, V> P) {
//        int n = S.size();
//        for (int i=0; i<n; i++) {
//            E element = S.remove(S.first());
//            P.insert(element, null);
//        }
//    }
}
