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
	
	public ItemQueue(){
		this.size = 0;
		items = new LinkedList<Item>();
	}
	
	public void addItem(Item newItem) throws CollectionFullException{
		if(size == MAX)
			throw new CollectionFullException();
		items.add(newItem);
		this.productName = newItem.getName();
		this.productPrice = newItem.getPrice();
		this.size++;
	}
	
	public double getProductPrice(){
		return this.productPrice;
	}
	
	public Item first(){
		return items.getFirst();
	}
	
	public Item removeItem() throws CollectionEmptyException{
		if(this.size <= 0)
			throw new CollectionEmptyException();
		this.size--;
		return items.removeFirst();
	}
	
	public String getProductName(){
		return this.productName;
	}
	
	public int size(){
		return this.size;
	}
	
	public String toString(){
		return this.productName + ": " + this.productPrice + " -- " + this.size;
	}
	

}
