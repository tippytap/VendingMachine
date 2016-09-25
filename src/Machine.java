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
	
	private final int EXIT = 8;
	private final int MAINTENANCE_MENU = 7;
	
	public Machine(){
		this.vendor = new Vendor(this);
		this.scan = new Scanner(System.in);
	}
	
	public Item giveItem(){
		return new Item("Sprite", 0.50);
	}
	
	public String getInput(String message){
		System.out.print(message);
		return this.scan.next();
	}
	
	public String getPasscode(){
		String passcode = "";
		System.out.println("Enter passcode:");
		passcode = this.scan.next();
		return passcode.trim();
	}
	
	public void start(){
		boolean exit = false;
		while(!exit){
			System.out.println("state = " + vendor.getState());
			this.menu = vendor.getMessage();
			System.out.println(this.menu + "\n");
			vendor.processCommand(
					Integer.parseInt(this.getInput("Choose an option: "))
			);
		}
	}
	
	
	
	
}
