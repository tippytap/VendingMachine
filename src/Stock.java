/**
 * Stock.java - Represents a collection of all the items currently in stock
 * @author erik miller
 * @version 1.0
 */

public class Stock {
	
	private ItemQueue[] stock;
	private final int MAX = 6;
	private int stockedSoFar;
	private int size;
	
	/**
	 * Creates a Collection of ItemQueues, which serve as the
	 * inventory (stock) of the vending machine
	 * */
	public Stock(){
		this.stockedSoFar = 0;
		this.size = 0;
		createStock();
	}
	
	private void createStock(){
		stock = new ItemQueue[MAX];
		for(int i = 0; i < MAX; i++){
			stock[i] = new ItemQueue();
			this.size++;
		}
	}
	
	/**
	 * Returns the current size of the stock
	 * @return int
	 * */
	public int getSize(){
		return this.size;
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
				if(stock[i].size() == 0)
					throw new CollectionEmptyException("**********************Product needs to be restocked\n");
				productIndex = i;
				found = true;
			}
		}
		
		if(found){
			product = stock[productIndex].removeItem();
		}
		else{
			throw new CollectionEmptyException("**********************Product not found\n");
		}
		
		return product;
	}
	
	
	/**
	 * Takes a new item and stocks it in either a queue of products of
	 * the same name or puts it in an empty queue
	 * @precondition Name cannot be empty string and must match an existing product name 
	 * @precondition price cannot be 0.0 or less and must match an existing product price
	 * @param Item
	 * @throws CollectionFullException 
	 * */
	public void stockItem(Item newItem) throws CollectionFullException{
		String name = newItem.getName();
		int productIndex = 0;
		boolean found = false;
		for(int i = 0; i < stock.length && !found; i++){
			if(name.equals(stock[i].getProductName())){
				if(stock[i].size() == 6)
					throw new CollectionFullException("**********************That item is completely stocked\n");
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
	 * Finds an item and returns its price based on the item name
	 * @param String
	 * @return double
	 * */
	public double checkPrice(String name){
		double price = 0;
		for(ItemQueue queue : this.stock){
			if(name.equals(queue.getProductName()))
				price = queue.getProductPrice();
		}
		return price;
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
	
	/**
	 * Returns the whole stock
	 * @return ItemQueue[]
	 * */
	public ItemQueue[] getStock(){
		return this.stock;
	}
	
	/**
	 * Looks for a product in the stock and returns its name
	 * @param int
	 * @param String
	 * */
	public String findItem(int itemCode){
		int i = 0;
		String name = "";
		for(ItemQueue queue : stock){
			if(i == itemCode)
				name = queue.getProductName();
			i++;
		}
		return name;
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
