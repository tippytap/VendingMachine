import static org.junit.Assert.*;

import org.junit.Test;

/**
 * ItemTest.java - Tests the Item class
 * @author erikmiller
 * @version 1.0
 */

public class ItemTest {

	private final String FAIL = "FAIL --- ";
	
	@Test
	public void testConstructorAndGetPrice() {
		Item item = new Item("Coca-Cola", 0.50);
		assertTrue(FAIL + "constructor and getPrice", 0.50 == item.getPrice());
	}
	
	@Test
	public void testGetName(){
		Item item = new Item("Sprite", 0.75);
		assertEquals(FAIL + "getName", "Sprite", item.getName());
	}
	
	@Test
	public void testToString(){
		Item item = new Item("Mountain Dew", 1.25);
		assertEquals(FAIL + "toString", "Mountain Dew: 1.25", item.toString());
	}

}
