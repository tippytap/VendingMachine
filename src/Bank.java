/**
 * Bank.java - Represents the totla amount of money the machine has collected
 * @author erik miller
 * @version 1.0
 */
public class Bank {

	private double total;
	
	public Bank(){
		this.total = 0;
	}
	
	public void addMoney(double newMoney){
		this.total += newMoney;
	}
	
	public double removeMoney() throws BankEmptyException{
		if(this.total == 0)
			throw new BankEmptyException();
		double money = this.total;
		this.total = 0;
		return money;
	}
	
	public double getTotal(){
		return this.total;
	}
	
}
