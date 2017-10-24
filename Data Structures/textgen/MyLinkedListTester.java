/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =100000; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> myList;
	MyLinkedList<Integer> myList2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		myList = new MyLinkedList<Integer>();
		myList2 = new MyLinkedList<Integer>();
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		
		int b = longerList.remove(LONG_LIST_LENGTH - 1);
		assertEquals("Remove: Check that b is correct", LONG_LIST_LENGTH-1, b);
		assertEquals("Remove: check size is correct ", LONG_LIST_LENGTH-1, longerList.size());
		try {
			longerList.get(-1);
			fail("we are out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        for (int i = 1; i <= 1000; i++) {
        	myList.add(i);
        }
        assertEquals("We should get 1", (Integer)1, myList.get(0));
        assertEquals("We should get 65", (Integer)65, myList.get(64));
        assertEquals("We should get 100", (Integer)100, myList.get(99));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("We should get 2", 2, shortList.size());
		assertEquals("We should get 0", 0, emptyList.size());
		assertEquals("We should get: " + LONG_LIST_LENGTH , LONG_LIST_LENGTH, longerList.size());
		assertEquals("We should get 3", 3, list1.size());
		
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		myList2.add(0,0);
		myList2.add(0,1);
		myList2.add(2,1);
		
		assertEquals("Size should be 3", 3, myList2.size());
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    int a = longerList.set(0, 1000);
	    int b = longerList.set(1, 1000);
	    int c = longerList.set(LONG_LIST_LENGTH - 1, 1000);
	    
	    assertEquals("We should get 1000", (Integer)1000, longerList.get(0));
	    assertEquals("We should get 1000", (Integer)1000, longerList.get(1));
	    assertEquals("We should get 1000", (Integer)1000, longerList.get(LONG_LIST_LENGTH-1));
	    assertEquals("We should get 0", 0, a);
	    assertEquals("We should get 1", 1, b);
	    assertEquals("We should get 1", LONG_LIST_LENGTH-1, c);
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
