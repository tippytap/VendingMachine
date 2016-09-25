import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * VendorTest.java - tests Vendor class
 * @author erik miller
 * @version 1.0
 */
public class VendorTest {
	
	protected static Vendor vendor;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vendor = new Vendor(new Machine(true));
	}

	/**
	 * Test method for {@link Vendor#Vendor()}.
	 */
	@Test
	public void testVendor() {
		assertTrue("vendor was created successfully", vendor.getState() == 0);
	}

	/**
	 * Test method for {@link Vendor#addCoin()}.
	 */
	@Test
	public void testAddCoin(){
		Coin coin = new Coin(0.50);
		vendor.addCoin(coin);
		vendor.addCoin(coin);
		assertTrue("coin was taken", vendor.getCoinsCollectedAmount() == 1.00);
	}

	/**
	 * Test method for {@link Vendor#requestAccess(java.lang.String)}.
	 * @throws RestrictedAccessException 
	 */
	@Test
	public void testRequestAccess() throws RestrictedAccessException {
		assertTrue("passcode correct", vendor.requestAccess("admin", 3));
		assertTrue("access elevated to maintenance menu", vendor.getState() == 3);
	}
	/**
	 * Test method for {@link Vendor#requestAccess(java.lang.String)}.
	 * @throws RestrictedAccessException 
	 */
	@Test
	public void testRequestAccessFail() throws RestrictedAccessException {
		assertFalse("passcode correct", vendor.requestAccess("boop", 3));
		assertTrue("access elevated to maintenance menu", vendor.getState() == 0);
	}
	
	@Test
	public void testExitMenu() throws RestrictedAccessException{
		vendor.requestAccess("admin", 3);
		vendor.exitToMenu(0);
		assertTrue("access demoted successfully", vendor.getState() == 0);
	}
	
	@Test
	public void testUpdatePasscode() throws RestrictedAccessException{
		String newCode = "bananas";
		boolean accessGranted = vendor.requestAccess("admin", 3);
		vendor.updatePasscode(newCode);
		vendor.exitToMenu(0);
		accessGranted = vendor.requestAccess(newCode, 3);
		assertTrue("passcode was updated", vendor.getState() == 3);
	}
	
	@Test(expected = RestrictedAccessException.class)
	public void testUpdatePasswordFail() throws RestrictedAccessException{
		vendor.updatePasscode("bananas");
	}

	/**
	 * Test method for {@link Vendor#stockItem()}.
	 * @throws RestrictedAccessException 
	 * @throws NotEnoughMoneyException 
	 */
	@Test
	public void testBuyItem() throws RestrictedAccessException, NotEnoughMoneyException {
		Item item = new Item("Sprite", 0.50);
		vendor.requestAccess("admin", 3);
		vendor.requestAccess("admin", 4);
		vendor.stockItem(item);
		vendor.exitToMenu(0);
		vendor.addCoin(new Coin(0.50));
		Item gotItem = vendor.selectItem("Sprite");
		assertEquals("item was bought", gotItem.getName(), "Sprite");
	}
	
	@Test(expected = NotEnoughMoneyException.class)
	public void testNotEnoughMoney() throws RestrictedAccessException, NotEnoughMoneyException{
		Item item = new Item("Sprite", 1.00);
		vendor.requestAccess("admin", 3);
		vendor.requestAccess("admin", 4);
		vendor.stockItem(item);
		vendor.exitToMenu(0);
		vendor.addCoin(new Coin(0.50));
		Item gotItem = vendor.selectItem("Sprite");
	}
	
	@Test
	public void testReturnWholeCoinStack(){
		Coin coin = new Coin(0.25);
		vendor.addCoin(coin);
		vendor.addCoin(coin);
		vendor.addCoin(coin);
		vendor.addCoin(coin);
		CoinStack coinReturn = vendor.releaseCoins(0);
		assertTrue("total amount was given", coinReturn.getAmount() == 1.00);
		assertTrue("coins were removed from machine", vendor.getCoinsCollectedAmount() == 0);
	}
	
	@Test
	public void testReturnChange() throws NotEnoughMoneyException, RestrictedAccessException{
		// enter 1.16, but product cost only 0.50
		Coin quarter = new Coin(0.25);
		Coin dime = new Coin(0.10);
		Coin nickel = new Coin(0.05);
		Coin penny = new Coin(0.01);
		Item sprite = new Item("Sprite", 0.50);
		vendor.requestAccess("admin", 3);
		vendor.requestAccess("admin", 4);
		vendor.stockItem(sprite);
		vendor.stockItem(sprite);
		vendor.exitToMenu(0);
		vendor.addCoin(quarter);
		vendor.addCoin(quarter); 
		vendor.addCoin(quarter); 
		vendor.addCoin(quarter);
		vendor.addCoin(dime); 
		vendor.addCoin(nickel);
		vendor.addCoin(penny); // 1.16
		Item gotItem = vendor.selectItem(sprite.getName());
		CoinStack coinReturn = vendor.releaseCoins(sprite.getPrice());
		assertTrue("exact change was given", coinReturn.getAmount() == 0.66);
	}

	/**
	 * Test method for {@link Vendor#getMessage()}.
	 * @throws RestrictedAccessException 
	 */
	@Test
	public void testGetMessage() throws RestrictedAccessException {
		Item sprite = new Item("Sprite", 0.50);
		vendor.requestAccess("admin", 3);
		System.out.println(vendor.getMessage());
		vendor.requestAccess("admin", 4);
		System.out.println(vendor.getMessage());
		vendor.stockItem(sprite);
		vendor.stockItem(sprite);
		vendor.stockItem(sprite);
		vendor.stockItem(sprite);
		vendor.exitToMenu(0);
		System.out.println(vendor.getMessage());
	}

}
