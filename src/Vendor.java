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
	//private Machine machine;
	private Bank bank;
	private Stock stock;
	private String passcode;
	private String basicMenu;
	private String[] messages;
	private final String MAINTENANCE_MENU_MESSAGE = 
			"Choose an option:\n"
			+ "1) Stock an item\n"
			+ "2) Collect money\n"
			+ "3) Reset password\n"
			+ "4) Exit Maintenance Menu\n";
	private final String ITEM_NOT_IN_STOCK = "Sorry, this item is not in stock. Please Choose another item.\n";
	private final String NOT_ENOUGH_MONEY = "Sorry, the amount you entered is incorrect.\n";
	private String maintenanceItemSelectMenu;
	private String selectedProduct;
	private String currentMessage;
	private CoinStack coinsCollected;
	
	public Vendor(){
		this.bank = new Bank();
		this.stock = new Stock();
		this.state = BASIC_MENU;
		this.coinsCollected = new CoinStack();
		this.setMessages();
		this.currentMessage = this.basicMenu;
	}
	
	private void setMessages(){
		this.passcode = "admin";
		this.basicMenu = "Select an item:" + stock.toString() + "7) Enter Maintenance Mode\n";
		this.maintenanceItemSelectMenu = "Choose an item to stock:" + stock.toString() + "7) Go Back\n";
		this.messages = new String[6];
		this.messages[0] = this.basicMenu;
		this.messages[1] = "";
		this.messages[2] = "";
		this.messages[3] = MAINTENANCE_MENU_MESSAGE;
		this.messages[4] = this.maintenanceItemSelectMenu;
		this.messages[5] = ITEM_NOT_IN_STOCK;
	}
	
	/**
	 * Pushes coin onto the stack
	 * @param Coin
	 * */
	public void addCoin(Coin coin){
		this.coinsCollected.pushCoin(coin);
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
			this.coinsCollected = new CoinStack();
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
		return coinReturn;
	}
	
	public Item selectItem(String name) throws NotEnoughMoneyException{
		Item item = null;
		try {
			item = stock.getItem(name);
			if(item.getPrice() > this.coinsCollected.getAmount())
				throw new NotEnoughMoneyException();
			this.state = VENDING;
			
		} 
		catch (CollectionEmptyException e) {
			this.currentMessage = e.getMessage();
		}
		return item;
	}
	
	public boolean requestAccess(String passcode, int level){
		boolean success = false;
		if(passcode == this.passcode){
			this.state = level;
			this.currentMessage = this.messages[level];
			success = true;
		}
		return success;
	}
	
	public void updatePasscode(String newCode) throws RestrictedAccessException{
		if(this.state != MAINTENANCE_MENU)
			throw new RestrictedAccessException();
		this.passcode = newCode;
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
	
	public void stockItem(Item newItem) throws RestrictedAccessException{
		if(this.state != MAINTENANCE_ITEM_SELECT_MENU)
			throw new RestrictedAccessException();
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
	
	public String getMessage(){
		return this.currentMessage;
	}
	
	
}









