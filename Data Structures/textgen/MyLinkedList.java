package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		size = 0;	
		head.next = tail;
		tail.prev = head;
		head.prev = null;
		tail.next = null;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.next = tail;
		newNode.prev = tail.prev;
		tail.prev.next = newNode;
		tail.prev = newNode;
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		LLNode<E> temp = head;
		
		if (size == 0 || index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
			for (int i = 0; i <= index; i++) {
				temp = temp.next;
			}
		
		return temp.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> temp = head;
		
		if (element == null) {
			throw new NullPointerException();
		}
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = 0; i <= index; i++) {
			temp = temp.next;
		}
		
		newNode.next = temp;
		newNode.prev = temp.prev;
		temp.prev.next = newNode;
		temp.prev = newNode;
			
		size++;		
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		LLNode<E> temp = head;
		
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = 0; i <= index; i++) {
			temp = temp.next;
		}
		
		temp.prev.next = temp.next;
		temp.next.prev = temp.prev;
		size--;
		
		return temp.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		LLNode<E> temp = head;
		
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = 0; i <= index; i++) {
			temp = temp.next;
		}
		
		E tempData = temp.data;
		temp.data = element;
		
		return tempData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
