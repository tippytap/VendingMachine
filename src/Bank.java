/**
 * Bank.java - Represents the totla amount of money the machine has collected
 * @author erik miller
 * @version 1.0
 */
public class Bank {

	private double total;
	
	/**
	 * Returns a valid Bank
	 * */
	public Bank(){
		this.total = 0;
	}
	
	/**
	 * Takes money and adds it to the bank total
	 * @param double
	 * */
	public void addMoney(double newMoney){
		this.total += newMoney;
	}
	
	/**
	 * Returns the total of the money in the bank and sets the total to 0
	 * @return double
	 * @throws BankEmptyException
	 * */
	public double removeMoney() throws BankEmptyException{
		if(this.total == 0)
			throw new BankEmptyException("**********************No money has been collected\n");
		double money = this.total;
		this.total = 0;
		return money;
	}
	
	/**
	 * Returns the total of the bank
	 * @return double
	 * */
	public double getTotal(){
		return this.total;
	}
	
}
