import java.util.Scanner;

/**
 * Machine.java - Interface for the vending machine
 * @author erik miller
 * @version 1.0
 */

public class Machine {

	private Vendor vendor;
	private Scanner scan;
	private String menu;
	private boolean exit;
	private boolean debug;
	
	/**
	 * Returns a valid machine -- has debug flag. Flag will tell the vendor not to fill the machine with items
	 * @param boolean
	 * */
	public Machine(boolean debug){
		this.debug = debug;
		this.vendor = new Vendor(this);
		this.exit = false;
		this.scan = new Scanner(System.in);
	}
	
	/**
	 * Returns the debug flag
	 * @return boolean
	 * */
	public boolean getDebug(){
		return this.debug;
	}
	
	/**
	 * Changes the value of the exit flag, to stop the program
	 * @param boolean
	 * */
	public void setExit(boolean exit){
		this.exit = exit;
	}
	
	/**
	 * Prints the item to the user
	 * @param Item
	 * */
	public void giveItem(Item item){
		System.out.println("\n**********************Received " + item.getName());
	}
	
	/**
	 * Prints the change to the user
	 * @param CoinStack
	 * */
	public void giveChange(CoinStack change){
		System.out.println("\n**********************Returned change: " + change.getAmount());
	}
	
	/**
	 * Prints the balance of the machine to the user
	 * @param double
	 * */
	public void giveMoney(double total){
		System.out.println("\n**********************Total collected: " + total);
	}
	
	/**
	 * Prompts the user for input, and displays a specified message to them.
	 * @param String
	 * */
	public String getInput(String message){
		System.out.print(message);
		return this.scan.nextLine();
	}
	
	/**
	 * Returns an item to the user to use for restocking
	 * @return Item
	 * */
	public Item getItem(){
		String name = this.getInput("Enter item name: ");
		double price = Double.parseDouble(this.getInput("Enter item price: "));
		return new Item(name, price);
	}
	
	/**
	 * Kicks everything off and keeps the machine menus updated
	 * */
	public void start(){
		while(!this.exit){
			this.menu = vendor.getMessage();
			System.out.println(this.menu + "\n");
			System.out.println("Type 'exit' to exit the system\n");
			System.out.println("--------------------------------------------------------------");
			vendor.processCommand(
					this.getInput("Choose an option: ")
			);
		}
		System.out.println("\nThank you and have a good day!\n");
	}
	
	
	
	
}
