import java.util.Comparator;

public class ArrayHeap<K, V> extends AbstractPriorityQueue<K, V> {

    private MyArrayList<Entry<K, V>> heap = new MyArrayList<>();

    public ArrayHeap(Comparator<K> comp) {
        super(comp);
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private boolean hasLeft(int i) {
        return left(i) < heap.size();
    }

    private boolean hasRight(int i) {
        return right(i) < heap.size();
    }

    private void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private void upheap(int j) {
        while(j > 0) {
            int p = parent(j);
            if(compare(heap.get(j), heap.get(p)) >= 0) {
                break;
            }
            swap(j, p);
            j = p;
        }
    }

    private void downheap(int j) {
        while(hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if(hasRight(j)) {
                int rightIndex = right(j);
                if(compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
                    smallChildIndex = rightIndex;
                }
            }
            if(compare(heap.get(smallChildIndex), heap.get(j)) >= 0) {
                break;
            }
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        return null;
    }

    public Entry<K, V> min() {
        if(heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public Entry<K, V> insert(Job j) throws IllegalArgumentException {
        Entry<K, V> newest = j;
        heap.add(heap.size(), newest);
        upheap(heap.size() - 1);
        return newest;
    }

    public Entry<K, V> removeMin() {
        if(heap.isEmpty()) {
            return null;
        }
        Entry<K, V> answer = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return answer;
    }

}
