import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * ItemQueueTest.java - testing the ItemQueue class
 * @author erikmiller
 * @version 1.0
 */
public class ItemQueueTest {
	
	protected static ItemQueue items;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		items = new ItemQueue();
	}

	@Test
	public void testAddItemAndSize() throws CollectionFullException {
		items.addItem(new Item("Coca-Cola", 0.50));
		assertTrue("addItem and size", items.size() == 1);
	}
	
	@Test(expected = CollectionFullException.class)
	public void testAddTooManyItems() throws CollectionFullException{
		Item newItem = new Item("Sprite", 0.50);
		items.addItem(newItem);
		items.addItem(newItem);
		items.addItem(newItem);
		items.addItem(newItem);
		items.addItem(newItem);
		items.addItem(newItem);
		items.addItem(newItem);
	}

	@Test
	public void testRemoveItem() throws CollectionFullException, CollectionEmptyException{
		items.addItem(new Item("Sprite", 0.50));
		items.addItem(new Item("Sprite", 0.50));
		Item first = items.removeItem();
		assertTrue("removeItem", items.size() == 1);
	}
	
	@Test(expected = CollectionEmptyException.class)
	public void testRemoveFromEmpty() throws CollectionEmptyException{
		Item soda = items.removeItem();
	}
	
	@Test
	public void testSize() throws CollectionFullException, CollectionEmptyException{
		items.addItem(new Item("Sprite", 0.50));
		items.addItem(new Item("Sprite", 0.50));
		items.addItem(new Item("Sprite", 0.50));
		assertTrue("size after adding 3", items.size() == 3);
		Item first = items.removeItem();
		assertTrue("size after removing 1", items.size() == 2);
		items.addItem(new Item("Sprite", 0.50));
		assertTrue("size after adding 1 after removing 1", items.size() == 3);
	}
	
	@Test
	public void testFirst() throws CollectionFullException, CollectionEmptyException{
		items.addItem(new Item("Sprite", 0.50));
		items.addItem(new Item("Coca-Cola", 0.50));
		Item first = items.first();
		assertTrue("first", first.getName() == "Sprite");
	}

}
