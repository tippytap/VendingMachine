import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * TestStock.java - tests Stock
 * @author erik miller
 * @version 1.0
 */

public class StockTest {
	
	protected static Stock stock;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		stock = new Stock();
	}

	/**
	 * Test method for {@link Stock#Stock()}.
	 */
	@Test
	public void testStock() {
		assertTrue("stock was created with correct length", stock.getStock().length == 6);
	}

	/**
	 * Test method for {@link Stock#getItem()}.
	 * @throws CollectionEmptyException 
	 * @throws CollectionFullException 
	 */
	@Test
	public void testGetItem() throws CollectionEmptyException, CollectionFullException {
		Item sprite = new Item("Sprite", 0.50);
		stock.stockItem(sprite);
		stock.stockItem(sprite);
		Item gotItem = stock.getItem("Sprite");
		assertTrue("got an item from the stock", 0.50 == gotItem.getPrice());
		assertEquals("item was removed from the stock", "Sprite: 0.5 -- 1", stock.getStock()[0].toString());
	}

	/**
	 * Test method for {@link Stock#getPrice()}.
	 * @throws CollectionFullException 
	 */
	@Test
	public void testGetPrice() throws CollectionFullException {
		Item sprite = new Item("Sprite", 0.50);
		Item coke = new Item("Coca-Cola", 0.75);
		stock.stockItem(sprite);
		stock.stockItem(coke);
		assertTrue("got price from queue", 0.50 == stock.checkPrice("Sprite"));
		assertTrue("got price from different item in queue", 0.75 == stock.checkPrice("Coca-Cola"));
	}

	/**
	 * Test method for {@link Stock#stockItem(Item)}.
	 * @throws CollectionFullException 
	 */
	@Test
	public void testStockItem() throws CollectionFullException {
		Item sprite = new Item("Sprite", 0.50);
		Item coke = new Item("Coke", 0.50);
		Item mtnDew = new Item("Mountain Dew", 0.50);
		stock.stockItem(sprite);
		assertEquals("item was added", "Sprite: 0.5 -- 1", stock.getStock()[0].toString());
		stock.stockItem(coke);
		assertEquals("new item was added", "Coke: 0.5 -- 1", stock.getStock()[1].toString());
		stock.stockItem(sprite);
		assertEquals("another one of the first item was stocked", "Sprite: 0.5 -- 2", stock.getStock()[0].toString());
		stock.stockItem(mtnDew);
		assertEquals("a new item was stocked after the first two", "Mountain Dew: 0.5 -- 1", stock.getStock()[2].toString());
	}

	/**
	 * Test method for {@link Stock#isItemInStock(Item)}.
	 * @throws CollectionFullException 
	 */
	@Test
	public void testIsItemInStock() throws CollectionFullException {
		Item sprite = new Item("Sprite", 0.50);
		Item coke = new Item("Coca-Cola", 0.50);
		stock.stockItem(sprite);
		stock.stockItem(coke);
		assertTrue("item is in stock", stock.isItemInStock(sprite.getName()));
		assertFalse("item is in stock -- false assertion", !stock.isItemInStock(coke.getName()));
	}

	/**
	 * Test method for {@link Stock#toString()}.
	 * @throws CollectionFullException 
	 */
	@Test
	public void testToString() throws CollectionFullException {
		Item sprite = new Item("Sprite", 0.50);
		Item coke = new Item("Coca-Cola", 0.50);
		Item mtnDew = new Item("Mountain Dew", 0.50);
		Item sMist = new Item("Sierra Mist", 0.50);
		Item drPepper = new Item("Dr. Pepper", 0.50);
		Item pepsi = new Item("Pepsi", 0.50);
		stock.stockItem(sprite);
		stock.stockItem(sprite);
		stock.stockItem(coke);
		stock.stockItem(mtnDew);
		stock.stockItem(sMist);
		stock.stockItem(drPepper);
		stock.stockItem(pepsi);
		System.out.println(stock);
	}

}
