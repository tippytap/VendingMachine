import java.util.LinkedList;
import java.util.Deque;

/**
 * ItemQueue.java - Represents a list of items managed in FIFO order
 * @author erik miller
 * @version 1.0
 */

public class ItemQueue {
	
	private Deque<Item> items;
	private int size;
	private final int MAX = 6;
	private String productName;
	private double productPrice;
	
	/**
	 * Returns a valid ItemQueue
	 * */
	public ItemQueue(){
		this.size = 0;
		items = new LinkedList<Item>();
	}
	
	/**
	 * Enqueues an item
	 * @param Item
	 * @throws CollectionFullException
	 * */
	public void addItem(Item newItem) throws CollectionFullException{
		if(size == MAX)
			throw new CollectionFullException("**********************That item is completely stocked\n");
		items.add(newItem);
		this.productName = newItem.getName();
		this.productPrice = newItem.getPrice();
		this.size++;
	}
	
	/**
	 * Returns the price of the product
	 * @return double
	 * */
	public double getProductPrice(){
		return this.productPrice;
	}
	
	/**
	 * Returns the first item in the queue but does not remove it
	 * @return Item
	 * */
	public Item first(){
		return items.getFirst();
	}
	
	/**
	 * Returns the first item in the queue and removes it
	 * @return Item
	 * @throws CollectionEmptyException
	 * */
	public Item removeItem() throws CollectionEmptyException{
		if(this.size <= 0)
			throw new CollectionEmptyException("**********************Product not found\n");
		this.size--;
		return items.removeFirst();
	}
	
	/**
	 * Returns the name of the product
	 * @return String
	 * */
	public String getProductName(){
		return this.productName;
	}
	
	/**
	 * Returns the size of the queue
	 * @return int
	 * */
	public int size(){
		return this.size;
	}
	
	public String toString(){
		return this.productName + ": " + this.productPrice + " -- " + this.size;
	}
	

}
