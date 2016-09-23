/**
 * Item.java - Represents a single product in the vending machine
 * @author erikmiller
 * @version 1.0
 */
public class Item {

	private String name;
	private double price;
	
	/**
	 * Takes a name and a price and creates a item to be put in stock
	 * @precondition name can't be empty string
	 * @precondition price cannot be 0.0 or less
	 * @param String
	 * @param double
	 * */
	public Item(String name, double price){
		this.name = name;
		this.price = price;
	}
	
	/**
	 * Returns the price of this item
	 * @return double
	 * */
	public double getPrice(){
		return this.price;
	}
	
	/**
	 * Returns the name of this item
	 * @return String
	 * */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Returns the item in string form
	 * @return String
	 * */
	public String toString(){
		return this.name + ": " + this.price;
	}
}
