// -----------------------------------------------------
// Assignment 2
// COMP 352
// Written by: Dmitry Shilchikov #27265123
// -----------------------------------------------------
import java.util.*;

/**
 * Tianlin Yang
 * 40010303
 * Haituan Zhuo
 * 40080732
 * Reference:
 * 1 https://docs.oracle.com/javase/7/docs/api/java/util/List.html
 * 2 https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html
 * 3 https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 * 4 http://www.cs.nuim.ie/~tlysaght/CS210/code/LinkedLists/ListTester.java
 * 5 https://github.com/eclipse/jubula.core/blob/master/org.eclipse.jubula.rc.common/src/org/eclipse/jubula/rc/common/tester/ListTester.java
 */

public class MyArrayList<E> implements List{

	//Define the size of arraylist
	private static final int StartSize = 10;
	private int size = 0;
	private Object myArray[] = {};
	
	/**
	 * <h1> Default constructor</h1>
	 * initializes the arraylist to the default size of 10
	 */
	public MyArrayList()
	{
		myArray = new Object[StartSize];
	}
	
	/**
	 *  * <h1> Custom constructor</h1>
	 * initializes the arraylist to the wished size
	 * @param n arraylist size
	 */
	public MyArrayList(int n) throws IllegalArgumentException
	{
		// just to verify that the size is valid
		if (n<0)
			throw new IllegalArgumentException();
		myArray = new Object[n];
	}
	
	/**
	 * <h1> returns the size of the arraylist </h1>
	 */
	@Override
	public int size() {
		if(size != 0)
			return size;
		return 0;
	}
	
	/**
	 * <h1> resize the arraylist </h1>
	 * doubles the size if reaching the boundary
	 */
	private void resizeUp() 
	 {
		   int newSize = myArray.length * 2;
		   myArray = Arrays.copyOf(myArray, newSize);
	 }
	
	/**
	 * <h1> add and object </h1>
	 * Appends the specified element to the end of this list
	 * @param e and object to add
	 */
	@Override
	public boolean add(Object e)
	{
		if (size == myArray.length)
			resizeUp();
		myArray[size] = e;
		size++;
		return false;
	}

	/**
	 * <h1> add and object </h1>
	 * Inserts the specified element at the specified position in this list
	 * @param e and object to add
	 * @param index position in the list
	 */
	@Override
	public void add(int index, Object e) throws IndexOutOfBoundsException
	{
		//just to check if the index is valid
		if ( index < 0 || index > size )
    		throw new IndexOutOfBoundsException();
		//check arraylist size
		if (size == myArray.length)
			resizeUp();
		//move all the proceeding elements to make room for the desired element
		for ( int i = size;  i > index; i-- )
    		myArray[i] = myArray[i-1];
    	//plug the element
		myArray[index] = e;
    	++this.size;

		
	}
	
	/**
	 * <h1> resize the arraylist </h1>
	 * halve the size when less than 25% of the capacity is used
	 */
	private void resizeDown()
	{
		int newSize = myArray.length/2;
		myArray = Arrays.copyOf(myArray, newSize);
	}
	
	/**
	 * <h1> clear the arraylist </h1>
	 * Removes all of the elements from this list
	 */
	@Override
	public void clear() 
	{
		while (size!=0)
		{
			//to check if need to resize
			if(size==myArray.length/4)
				resizeDown();
			myArray[size-1] = null;
			size--;
		}
		size=0;
	}
	
	/**
	 * <h1> removes from arraylist </h1>
	 * Removes the first occurrence of the specified element from this list
	 * @param o object to remove
	 */
	@Override
	public boolean remove(Object o) throws IndexOutOfBoundsException
	{
		int index= 0;
		while (myArray[index]!=null && !(myArray[index].equals(o)))
			index++;
		if(myArray[index]== null)
			return false;
		else
		{
			while(index < size)
			{
				myArray[index] = myArray[index+1];
				myArray[index+1] = null;
				index++;
			}
		}
	      //to check if need to resize
			if(size==myArray.length/4)
				resizeDown();
			
		/*
		myArray[index] = null;
		int temp = index;
		while(index < size)
        {
        	myArray[index] = myArray[index+1];
        	myArray[index+1] = null;
            index++;
        }*/
        size--;
        
	
		
		return false;
	}
	
	/**
	 * <h1> removes from arraylist </h1>
	 * Removes the element at the specified position in this list
	 * @param index index
	 */
	@Override
	public Object remove(int index) throws IndexOutOfBoundsException
	{
		
		if(index < size)
		{
            Object o = myArray[index];
            if(index < size)
                System.arraycopy(myArray, index + 1, myArray, index, size - index - 1);
            myArray[size - 1] = null;
            size--;
            //to check if need to resize
			if(size==myArray.length/4)
				resizeDown();
            return o;
        } 
		else 
            throw new ArrayIndexOutOfBoundsException();
	}
	
	/**
	 * <h1> printing the array list </h1>
	 */
	public String toString()
	{
		System.out.print("Array list contains: [ ");
		for(int i=0; i<size; i++)
			System.out.print(myArray[i]+ ", ");
		return" ]";
	}

	@Override
	public boolean addAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object set(int index, Object element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray(Object[] a) {
		throw new UnsupportedOperationException();
	}

	
	
	
}