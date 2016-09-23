import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * BankTest.java - specifics here
 * @author erikmiller
 * @version 1.0
 */

public class BankTest {
	
	protected static Bank bank;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bank = new Bank();
	}

	/**
	 * Test method for {@link Bank#addMoney()}.
	 */
	@Test
	public void testAddMoneyAndGetTotal() {
		bank.addMoney(0.50);
		assertTrue("money was added", bank.getTotal() == 0.50);
		bank.addMoney(1.00);
		assertTrue("money was added again", bank.getTotal() == 1.50);
	}

	/**
	 * Test method for {@link Bank#removeMoney()}.
	 * @throws BankEmptyException 
	 */
	@Test
	public void testRemoveMoney() throws BankEmptyException {
		bank.addMoney(1.00);
		bank.addMoney(1.00);
		double money = bank.removeMoney();
		assertTrue("exact money was given", money == 2.00);
		assertTrue("total was reset", bank.getTotal() == 0.0);
	}
	
	@Test(expected=BankEmptyException.class)
	public void testRemoveMoneyButNoMoney() throws BankEmptyException{
		bank.removeMoney();
	}

}
