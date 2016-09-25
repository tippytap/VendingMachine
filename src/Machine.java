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
	
	public Machine(boolean debug){
		this.debug = debug;
		this.vendor = new Vendor(this);
		this.exit = false;
		this.scan = new Scanner(System.in);
	}
	
	public boolean getDebug(){
		return this.debug;
	}
	
	public void setExit(boolean exit){
		this.exit = exit;
	}
	
	public void giveItem(Item item){
		System.out.println("\n**********************Received " + item.getName());
	}
	
	public void giveChange(CoinStack change){
		System.out.println("\n**********************Returned change: " + change.getAmount());
	}
	
	public void giveMoney(double total){
		System.out.println("\n**********************Total collected: " + total);
	}
	
	public String getInput(String message){
		System.out.print(message);
		return this.scan.nextLine();
	}
	
	public Item getItem(){
		String name = this.getInput("Enter item name: ");
		double price = Double.parseDouble(this.getInput("Enter item price: "));
		return new Item(name, price);
	}
	
	public void start(){
		while(!this.exit){
			this.vendor.setMessages();
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
