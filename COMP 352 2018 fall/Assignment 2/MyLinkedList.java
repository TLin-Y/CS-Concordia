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

public class MyLinkedList<E> implements List{
	
	private Node<E> head;
	private Node<E> tail;
	private  int size;
	
	/**
	 * <h1> Default constructor</h1>
	 * initializes the linked list
	 */
	public MyLinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * <h1> tail accessor </h1>
	 * @return tail
	 */
	public Node<E> getTail()
	{
		return tail;
	}
	
	
	/**
	 * <h1> returns the size of the linked list </h1>
	 */
	@Override
	public int size()
	{
		return size;
	}
	
	/**
	 * <h1> add and object </h1>
	 * Appends the specified element to the end of this list
	 * @param e and object to add
	 */
	@Override
	public boolean add(Object e)
	{
		Node<E> newNode = new Node();
		newNode.data = e;
		newNode.next = null;

		if (head == null)
		{
			head = newNode;
			tail = newNode;
			tail.next = null;
			tail.prev = null;
			size ++;
		} 
		else
		{
			Node<E> prev = this.tail;
			tail.next = newNode;
			tail = newNode;
			tail.prev = prev;
			size++;
		}
		return false;
	}

	/**
	 * <h1> add and object </h1>
	 * Inserts the specified element at the specified position in this list
	 * @param e and object to add
	 * @param index position in the list
	 */
	@Override
	public void add(int index, Object e) 
	{
		// check if index is legal
		if(index<0||index>(size))
			throw new IllegalArgumentException();
		
		//prep data
		int center = (int) Math.floor(size/2);
		Node<E> newNode = new Node();
		newNode.setData(e);
		Node<E> position = null;
		
		//add first
		if(index==0)
		{
			if(head==null)
			{
				head = newNode;
				tail = newNode;
				tail.setNext(null);
				tail.setPrev(null);
				size++;
			}
			else
			{
				newNode.setNext(head);
				head.setPrev(newNode);
				head=newNode;
				size++;
			}
		}// end of add first
	
		else if (head != null && index <= size)
		{
			//go from beginning to index (faster)
			if(index < center)
			{
				position = head;
				for(int i=1;i<index;i++)
					position = position.next;
			}
			//go form end to index (faster)
			else
			{
				position = tail;
				for(int i=size; i>index; i--)
					position = position.prev;
			}
			newNode.setNext(position.getNext());
			position.getNext().setPrev(newNode);
			newNode.setPrev(position);
			position.setNext(newNode);
			size ++;
		}// end else if statement		
	}
	
	/**
	 * <h1> clear the linked list </h1>
	 * Removes all of the elements from this list
	 */
	@Override
	public void clear() 
	{
		head = null;
		tail=null;
		size=0;
	}
	
	/**
	 * <h1> checks if an object is in the list </h1>
	 * @param 0 the object for check
	 */
	@Override
	public boolean contains(Object o)
	{
		boolean toReturn=false;
		Node<E> position = head;
		for(int i=0; i<size;i++)
		{
			if(position.getData().equals(o))
				toReturn=true;
			position=position.getNext();
		}
		return toReturn;
	}
	
	/**
	 * <h1> removes from linked list </h1>
	 * Removes the first occurrence of the specified element from this list
	 * @param o object to remove
	 */
	@Override
	public boolean remove(Object o)
	{
		if(!contains(o))
			return false;
		else
		{
			Node<E> position = head;
			//traverse to find desired element's position
			while (!position.getData().equals(o))
				position=position.getNext();
			if(position==head)
			{
				if(head==null)
					return false;
				if(position.getNext()!=null)
				{
					position.getNext().setPrev(null);
					head = position.getNext();
					position=null;//for garbage collection
				}
			}//end if 
			else
			{
				if(position.getNext()!=null)
				{
					position.getPrev().setNext(position.getNext());
					position.getNext().setPrev(position.getPrev());
					//for garbage collection
					position.setNext(null);
					position.setPrev(null);
				}
				else
				{
					position.getPrev().setNext(null);
					tail=position.getPrev();
					//for garbage collection
					position.setNext(null);
					position.setPrev(null);
				}
			}
			size--;
			return false;
		}
	}
	
	/**
	 * <h1> removes from linked list </h1>
	 * Removes the element at the specified position in this list
	 * @param index index
	 */
	@Override
	public Object remove(int index) 
	{
		Object toRemove = null;
		if(index==0)
		{
			size=0;
			return false;
		}
		//to remove at the beginning
		if (index == 1 && index!=size)
		{
			toRemove = head.getData();
			head = head.getNext();
			head.setPrev(null);
			size--;
		}
		
		//to remove at the end
		else if (index == size)
		{
			if (size == 1)
			{
				head = null;
				tail = null;
				size = 0;
				return false;	
			}
			else
			{
			toRemove = tail.getData();
			tail = tail.getPrev();
			tail.setNext(null);
			size--;
			}
		}//end else if
		else 
		{
			Node<E> position = head;
			for (int i = 1; i <= size; i++)
			{
				//to remove at the beginning
				if (index == 1 && index!=size)
				{
					toRemove = head.getData();
					head = head.getNext();
					head.setPrev(null);
					size--;
				}
				
				else if (i == index) 
				{
					position.getPrev().setNext(position.getNext());;
					position.getNext().setPrev(position.getPrev());;
					position=null;
					size--;
					return false;
				}
				position = position.getNext();
			}//end of for loop
			
		}//end else
		return toRemove;
	}
	
	/**
	 * <h1> prints the linked list </h1>
	 */
	public String toString()
	{
		System.out.print("\nDoubly Linked List = ");

		//for empty list
		if (size == 0)
			return"empty.";
		
		//if only head exists
		if (head.getNext() == null)
			return "|"+head +"|.";

		
		Node<E> position = head;
		System.out.print("|"+head +"|"+ " <===> ");
		position = head.getNext();
		while (position.getNext() != null)
		{
			System.out.print("|"+position +"|"+ " <===> ");
			position = position.getNext();
		}
		return "|"+position+"|.";
	
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
	
	
/*//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
											INNER NODE CLASS
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
private class Node<E> {
	protected Object data;
	protected Node next;
	protected Node prev;

	/**
	 * <h1> default constructor </h1>
	 * initializes the node
	 */
	public Node() 
	{
		next = null;
		prev = null;
		data = "";
	}

	/**
	 * <h1> custom constructor </h1>
	 * set the node with specific data 
	 * @param o data
	 * @param n next node
	 * @param p previous node
	 */
	public Node(Object o, Node n, Node p)
	{
		data = o;
		next = n;
		prev = p;
	}
	
	/**
	 * <h1> custom constructor </h1>
	 * set the node with specific data 
	 * @param o data
	 */
	public Node(Object o)
	{
		data = o;
		next = null;
		prev = null;
	}

	/**
	 * <h1> set the next node </h1>
	 * @param n new next node
	 */
	public void setNext(Node n)
	{
		next = n;
	}

	/**
	 * <h1> set the previous node </h1>	
	 * @param p new previous node
	 */
	public void setPrev(Node p)
	{
		prev = p;
	}

	/**
	 * <h1> get the next node </h1>	
	 * @return next node
	 */
	public Node getNext()
	{
		return next;
	}

	/**
	 * <h1> get the previous node </h1>
	 * @return the previous node 
	 */
	public Node getPrev() 
	{
		return prev;
	}


	/**
	 * <h1> set the data </h1>
	 * @param d new data
	 */
	public void setData(Object d)
	{
		data = d;
	}

	/**
	 * <h1> get the data </h1>	
	 * @return the data
	 */
	public Object getData() 
	{
		return data;
	}
	
	/**
	 * <h1> print node </h1>
	 */
	public String toString()
	{
		return data+""; 
	}
}// end of inner class Node

	

}// end of LinkedList
