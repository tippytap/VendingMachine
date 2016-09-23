/**
 * Coin.java - Represents a single coin
 * @author erik miller
 * @version 1.0
 */
public class Coin {
	
	private String name;
	private double value;
	
	/**
	 * Makes a new coin using given value
	 * @param double
	 * */
	public Coin(double value){
		this.value = value;
		setName(value);
	}
	
	/**
	 * Takes a value and determines the name of the coin
	 * @param double
	 * */
	private void setName(double value){
		if(value == 0.01)
			this.name = "Penny";
		else if(value == 0.05)
			this.name = "Nickel";
		else if(value == 0.1)
			this.name = "Dime";
		else
			this.name = "Quarter";
	}
	
	/**
	 * Gives the value of the coin
	 * @return double
	 * */
	public double getValue(){
		return this.value;
	}
	
	/**
	 * Gives the string representation of the coin
	 * @return String
	 * */
	public String toString(){
		return this.name + " -- " + this.value;
	}

}
