import java.util.ArrayList;
import java.util.Stack;

/**
 * CoinStack.java - Collection of coins in a stack
 * @author erik miller
 * @version 1.0
 */
public class CoinStack {
	
	private Stack<Coin> coins;
	private double total;
	private int size;
	
	/**
	 * Returns a valid CoinStack
	 * */
	public CoinStack(){
		this.size = 0;
		coins = new Stack<Coin>();
	}
	
	/**
	 * Pushes a new coin onto the stack
	 * @param Coin
	 * */
	public void pushCoin(Coin newCoin){
		this.total += newCoin.getValue();
		this.size++;
		coins.push(newCoin);
	}
	
	/**
	 * Returns the first coin in the stack but does not pop it
	 * @return Coin
	 * */
	public Coin peek(){
		return coins.peek();
	}
	
	/**
	 * Returns the first coin on the stack and pops it
	 * @return Coin
	 * @throws CollectionEmptyException
	 * */
	public Coin popCoin() throws CollectionEmptyException{
		if(this.size <= 0)
			throw new CollectionEmptyException("**********************No coins have been enetered\n");
		Coin top = coins.pop();
		this.total -= top.getValue();
		this.size--;
		return top;
	}
	
	/**
	 * Returns the total amount of this stack
	 * @return double
	 * */
	public double getAmount(){
		return this.total;
	}
	
	/**
	 * Returns the size of this stack
	 * @return int
	 * */
	public int size(){
		return this.size;
	}
	
	public String toString(){
		return "Amount Entered: " + this.total;
	}
}
