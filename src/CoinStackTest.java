import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * CoinStackTest.java - Tests CoinStack Class
 * @author erik miller
 * @version 1.0
 */
public class CoinStackTest {

	public static CoinStack coins;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		coins = new CoinStack();
	}

	/**
	 * Test method for {@link CoinStack#pushCoin(Coin)}.
	 */
	@Test
	public void testPushCoinAndSize() {
		coins.pushCoin(new Coin(0.05));
		assertTrue("pushCoin", coins.size() == 1);
	}
	
	@Test
	public void testSize() throws CollectionEmptyException{
		coins.pushCoin(new Coin(0.10));
		coins.pushCoin(new Coin(0.10));
		coins.pushCoin(new Coin(0.10));
		assertTrue("size after adding 3", coins.size() == 3);
		Coin top = coins.popCoin();
		top = coins.popCoin();
		assertTrue("size after removing 2", coins.size() == 1);
		coins.pushCoin(new Coin(0.05));
		assertTrue("size after adding 1 after removing 2", coins.size() == 2);
	}

	/**
	 * Test method for {@link CoinStack#peek()}.
	 */
	@Test
	public void testPeek() {
		coins.pushCoin(new Coin(0.25));
		coins.pushCoin(new Coin(0.01));
		Coin top = coins.peek();
		assertTrue("peek", top.getValue() == 0.01);
	}

	/**
	 * Test method for {@link CoinStack#popCoin()}.
	 */
	@Test
	public void testPopCoin() {
		coins.pushCoin(new Coin(0.10));
		coins.pushCoin(new Coin(0.25));
		coins.pushCoin(new Coin(0.05));
		Coin top;
		try {
			top = coins.popCoin();
			assertFalse("popCoin", top.getValue() != 0.05);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = CollectionEmptyException.class)
	public void testPopCoinButStackEmpty() throws CollectionEmptyException{
		coins.popCoin();
	}

	/**
	 * Test method for {@link CoinStack#getAmount()}.
	 */
	@Test
	public void testGetAmount() {
		coins.pushCoin(new Coin(0.10));
		coins.pushCoin(new Coin(0.01));
		coins.pushCoin(new Coin(0.05));
		assertTrue("getAmount", coins.getAmount() == 0.16);
	}

	/**
	 * Test method for {@link CoinStack#toString()}.
	 */
	@Test
	public void testToString() {
		coins.pushCoin(new Coin(0.10));
		coins.pushCoin(new Coin(0.25));
		assertEquals("toString", "Amount Entered: 0.35", coins.toString());
	}

}
