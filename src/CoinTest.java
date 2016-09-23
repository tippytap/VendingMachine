import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CoinTest {

	@Test
	public void coinConstructorAndGetValue() {
		Coin coin = new Coin(0.10);
		assertTrue(0.10 == coin.getValue());
	}
	
	@Test
	public void coinSetNameAndToString(){
		Coin coin = new Coin(0.25);
		assertEquals(coin.toString(), "Quarter -- 0.25");
	}

}
