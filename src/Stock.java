/**
 * Stock.java - Represents a collection of all the items currently in stock
 * @author erik miller
 * @version 1.0
 */

public class Stock {
	
	private ItemQueue[] stock;
	private final int MAX = 6;
	private int stockedSoFar;
	
	/**
	 * Creates a Collection of ItemQueues, which serve as the
	 * inventory (stock) of the vending machine
	 * */
	public Stock(){
		this.stockedSoFar = 0;
		createStock();
	}
	
	private void createStock(){
		stock = new ItemQueue[MAX];
		for(int i = 0; i < MAX; i++){
			stock[i] = new ItemQueue();
		}
	}

	/**
	 * Removes an Item from the ItemQueue if the given name
	 * matches the name of the Item
	 * @param String
	 * @return Item
	 * */
	public Item getItem(String name) throws CollectionEmptyException{
		Item product = null;
		boolean found = false;
		int productIndex = 0;
		
		for(int i = 0; i < stock.length && !found; i++){
			if(name == stock[i].getProductName()){
				productIndex = i;
				found = true;
			}
		}
		
		if(found){
			product = stock[productIndex].removeItem();
		}
		
		return product;
	}
	
	public double getPrice(String name){
		double price = 0;
		for(ItemQueue product : stock){
			if(name == product.getProductName())
				price = product.getProductPrice();
		}
		return price;
	}
	
	/**
	 * Takes a new item and stocks it in either a queue of products of
	 * the same name or puts it in an empty queue
	 * @precondition item must be valid. name cannot be empty string, price cannot be 0.0 or less
	 * @param Item
	 * */
	public void stockItem(Item newItem) throws CollectionFullException{
		String name = newItem.getName();
		int productIndex = 0;
		boolean found = false;
		for(int i = 0; i < stock.length && !found; i++){
			if(stock[i].getProductName() == name){
				found = true;
				productIndex = i;
			}
		}
		if(!found){
			stock[stockedSoFar].addItem(newItem);
			this.stockedSoFar++;
		}
		else{
			stock[productIndex].addItem(newItem);
		}
	}
	
	/**
	 * Answers whether the Item is found in the stock or not
	 * @param String
	 * */
	public boolean isItemInStock(String name){
		boolean found = false;
		for(int i = 0; i < stock.length && !found; i++){
			if(name == stock[i].getProductName())
				found = true;
		}
		return found;
	}
	
	public ItemQueue[] getStock(){
		return this.stock;
	}
	
	public String toString(){
		int productNumber = 0;
		String currentStock = "\n------------------------\n";
		for(ItemQueue products : this.stock){
			currentStock += productNumber + ") " + products + "\n------------------------\n";
			productNumber++;
		}
		return currentStock;
	}
	
	
	
}
