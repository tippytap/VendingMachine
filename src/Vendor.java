/**
 * Vendor.java - Handles interoperability between classes and carries out user commands
 * @author erik miller
 * @version 1.0
 */
public class Vendor {

	private final int BASIC_MENU = 0,
					  ITEM_SELECT = 1,
					  VENDING = 2,
					  MAINTENANCE_MENU = 3,
					  MAINTENANCE_ITEM_SELECT_MENU = 4;
	
	private int state;
	private Machine machine;
	private Bank bank;
	private Stock stock;
	private String passcode;
	private String basicMenu;
	private String[] messages;
	private String maintenanceMenuMessage;
	private final String ITEM_NOT_IN_STOCK = "Sorry, this item is not in stock. Please Choose another item.\n";
	private final String NOT_ENOUGH_MONEY = "Sorry, the amount you entered is incorrect.\n";
	private String maintenanceItemSelectMenu;
	private String selectedProduct;
	private String currentMessage;
	private CoinStack coinsCollected;
	
	public Vendor(Machine machine){
		this.machine = machine;
		this.bank = new Bank();
		this.stock = new Stock();
		this.state = BASIC_MENU;
		this.coinsCollected = new CoinStack();
//		this.setMessages();
		this.currentMessage = this.basicMenu;
		this.passcode = "admin";
		this.initVendingMachine();
	}
	
	public void initVendingMachine(){
		if(!machine.getDebug()){
			String[] productNames = new String[]{"Sprite", "Coca-Cola", "Mountain Dew", "Dr. Pepper", "Pepsi", "Butt-Cola"};
			double[] productPrices = new double[]{0.50, 0.50, 0.75, 1.00, 0.10, 2.00};
			for(int i = 0; i < 6; i++){
				Item item = new Item(productNames[i], productPrices[i]);
				for(int j = 0; j < 6; j++){
					try {
						this.stock.stockItem(item);
					} 
					catch (CollectionFullException e) {
						e.printStackTrace();
					}
				}
			}
		}
		this.setMessages();
		this.currentMessage = this.basicMenu;
	}
	
	public void setMessages(){
		this.basicMenu = 
				"\n** Coins inserted: " + this.getCoinsCollectedAmount() + " **\n\n"
				+ "Select an item:" + this.stock
				+ "6) Insert a coin\n7) Release coins\n8) Enter Maintenance Mode";
		this.maintenanceItemSelectMenu = 
				"\nItem Stocking"
				+ this.stock
				+ "1) Create item\n2) Go Back\n";
		this.maintenanceMenuMessage = 
				"\nMaintenance Menu\n-----------------------\n"
				+ "Money Collected: " + this.bank.getTotal() + "\n-----------------------\n"
						+"Choose an option:\n"
						+ "1) Stock an item\n"
						+ "2) Collect money\n"
						+ "3) Reset password\n"
						+ "4) Exit Maintenance Menu\n";
		this.messages = new String[6];
		this.messages[0] = this.basicMenu;
		this.messages[1] = "";
		this.messages[2] = "";
		this.messages[3] = this.maintenanceMenuMessage;
		this.messages[4] = this.maintenanceItemSelectMenu;
		this.messages[5] = ITEM_NOT_IN_STOCK;
	}
	
	public void processCommand(String input){
		if(input.equals("exit"))
			machine.setExit(true);
		else{
			int command = Integer.parseInt(input);
			switch(this.state){
			case BASIC_MENU:
				switch(command){
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
					String name = this.stock.findItem(command);
					try {
						Item gotItem = this.selectItem(name);
						CoinStack change = this.releaseCoins(gotItem.getPrice());
						machine.giveItem(gotItem);
						machine.giveChange(change);
						this.state = BASIC_MENU;
						this.setMessages();
						this.currentMessage = this.getSpecificMessage(BASIC_MENU);
					} 
					catch (Exception e) {
						this.state = BASIC_MENU;
						this.currentMessage = "\n" + e.getMessage() + this.getSpecificMessage(this.state);
					}
					break;
					
				case 6:
					this.addCoin(
							new Coin(
									Double.parseDouble(machine.getInput("enter 0.01, 0.05, 0.10, 0.25: "))
							)
					);
					this.setMessages();
					this.currentMessage = this.getSpecificMessage(BASIC_MENU);
					break;
					
				case 7:
					CoinStack change = this.releaseCoins(0.0);
					machine.giveChange(change);
					this.setMessages();
					this.currentMessage = this.getSpecificMessage(BASIC_MENU);
					break;
					
				case 8:
					try {
						this.requestAccess(
								machine.getInput("Enter passcode: "), 3
						);
					} 
					catch (RestrictedAccessException e) {
						this.state = BASIC_MENU;
						this.currentMessage = "\n" + e.getMessage() + this.getSpecificMessage(this.state);
					}
					break;
				}
				
				break;
				
			case MAINTENANCE_MENU:
				switch(command){
				
				case 1:
					try {
						this.requestAccess(machine.getInput("Enter passcode: "), 4);
					} 
					catch (RestrictedAccessException e) {
						this.state = MAINTENANCE_MENU;
						this.currentMessage = "\n" + e.getMessage() + this.getSpecificMessage(this.state);
					}
					break;
					
				case 2:
					try {
						machine.giveMoney(this.bank.removeMoney());
					} 
					catch (BankEmptyException e) {
						this.currentMessage = "\n" + e.getMessage() + this.getSpecificMessage(this.state);
					}
					break;
					
				case 3:
					this.updatePasscode(machine.getInput("Enter new passcode: "));
					break;
					
				case 4:
					this.exitToMenu(BASIC_MENU);
					break;
					
				}
				break;
			case MAINTENANCE_ITEM_SELECT_MENU:
				switch(command){
					case 1:
					try {
						this.stock.stockItem(machine.getItem());
					} 
					catch (CollectionFullException e) {
						this.currentMessage = "\n" + e.getMessage() + this.getSpecificMessage(this.state);
					}
					this.setMessages();
					this.currentMessage = this.getSpecificMessage(MAINTENANCE_ITEM_SELECT_MENU);
					break;
					
					case 2:
						this.exitToMenu(MAINTENANCE_MENU);
						break;
				}
				break;
			}
		}
		this.setMessages();
	}
	
	/**
	 * Pushes coin onto the stack
	 * @param Coin
	 * */
	public void addCoin(Coin coin){
		this.coinsCollected.pushCoin(coin);
		this.setMessages();
	}
	
	public double getCoinsCollectedAmount(){
		return this.coinsCollected.getAmount();
	}
	
	/**
	 * Returns the coin stack of the total amount entered 
	 * or the change leftover from the puchase
	 * @param double
	 * @return CoinStack
	 * */
	public CoinStack releaseCoins(double price){
		CoinStack coinReturn;
		if(this.state != VENDING){
			coinReturn = this.coinsCollected;
		}
		else{
			coinReturn = new CoinStack();
			int difference = (int)((this.coinsCollected.getAmount() - price) * 100);
			while(difference != 0){
				if(difference >= 25){
					coinReturn.pushCoin(new Coin(0.25));
					difference = difference - 25;
				}
				else if(difference >= 10){
					coinReturn.pushCoin(new Coin(0.10));
					difference = difference - 10;
				}
				else if(difference >= 5){
					coinReturn.pushCoin(new Coin(0.05));
					difference = difference - 5;
				}
				else if(difference >= 1){
					coinReturn.pushCoin(new Coin(0.01));
					difference = difference - 1;
					
				}
			}
		}
		this.coinsCollected = new CoinStack();
		return coinReturn;
	}
	
	public Item selectItem(String name) throws NotEnoughMoneyException{
		Item item = null;
		try {
			double price = stock.checkPrice(name);
			if(price > this.coinsCollected.getAmount())
				throw new NotEnoughMoneyException("**********************You have not entered enough money\n");
			item = stock.getItem(name);
			this.bank.addMoney(item.getPrice());
			this.state = VENDING;
			
		} 
		catch (CollectionEmptyException e) {
			this.currentMessage = "\n" + e.getMessage() + this.getSpecificMessage(this.state);
		}
		return item;
	}
	
	public boolean requestAccess(String passcode, int level) throws RestrictedAccessException{
		boolean success = false;
		String pass = passcode.trim();
		if(!pass.equals(this.passcode)){
			throw new RestrictedAccessException("**********************Incorrect password\n");
		}
		this.state = level;
		this.currentMessage = this.messages[level];
		success = true;
		return success;
	}
	
	public void updatePasscode(String newCode){
//		System.out.println("boop");
		this.passcode = newCode;
//		System.out.println(this.passcode);
	}
	
	/**
	 * Returns state to previous menu
	 * @param int
	 * @precondition passed state must be a menu state
	 * */
	public void exitToMenu(int previousMenu){
		this.state = previousMenu;
		this.currentMessage = this.messages[previousMenu];
	}
	
	public void stockItem(Item newItem){
		try{
			this.stock.stockItem(newItem);
			this.setMessages();
		}
		catch(Exception e){
			this.currentMessage = e.getMessage();
		}
	}
	
	public int getState(){
		return this.state;
	}
	
	public String getSpecificMessage(int message){
		return this.messages[message];
	}
	
	public String getMessage(){
		return this.currentMessage;
	}
	
	
}









