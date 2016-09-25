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
	
	public CoinStack(){
		this.size = 0;
		coins = new Stack<Coin>();
	}
	
	public void pushCoin(Coin newCoin){
		this.total += newCoin.getValue();
		this.size++;
		coins.push(newCoin);
	}
	
	public Coin peek(){
		return coins.peek();
	}
	
	public Coin popCoin() throws CollectionEmptyException{
		if(this.size <= 0)
			throw new CollectionEmptyException("**********************No coins have been enetered\n");
		Coin top = coins.pop();
		this.total -= top.getValue();
		this.size--;
		return top;
	}
	
	public double getAmount(){
		return this.total;
	}
	
	public int size(){
		return this.size;
	}
	
	public String toString(){
		return "Amount Entered: " + this.total;
	}
}
