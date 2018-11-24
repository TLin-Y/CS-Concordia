public class MyArrayList<E> {

    private static final int INITIAL_CAPACITY = 10;
    private int capacity;
    private E[] data;
    private int size;

    public MyArrayList() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
        capacity = INITIAL_CAPACITY;
    }

    public E get(int i)throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    public E set(int i, E e) {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    public void add(int i, E e) {
        checkIndex(i, size);
        for(int j = size; j > i; j--) {
            data[j] = data[j - 1];
        }
        data[i] = e;
        size++;
        resize();
    }

    public E remove(int index) throws IndexOutOfBoundsException, IllegalStateException {
        if(this.size == 0) {
            throw new IllegalStateException("List is empty");
        }
        checkIndex(index, size + 1);
        E element = data[index];
        for(int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        size--;
        resize();
        return element;
    }

    public void clear() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
        capacity = INITIAL_CAPACITY;
    }

    @Override
    public String toString() {
        String a = "[";
        for (int i = 0; i < size; i++) {
            a += data[i] + ", ";
        }
        a += "]";
        return a;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if(i < 0 || i > n) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    public void resize() {
        if(size == capacity) {
            E[] newList = (E[]) new Object[2 * capacity];
            for(int i = 0; i < size; i++) {
                newList[i] = data[i];
            }
            capacity = 2 * capacity;
            data = newList;
        }
        if(size <= capacity / 4) {
            E[] newList = (E[]) new Object[capacity / 2];
            for(int i = 0; i < size; i++) {
                newList[i] = data[i];
            }
            capacity = capacity / 2;
            data = newList;
        }
    }

}
