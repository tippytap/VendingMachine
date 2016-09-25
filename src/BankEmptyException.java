/**
 * BankEmptyException.java - If the bank is empty, throw this exception
 * @author erik miller
 * @version 1.0
 */

public class BankEmptyException extends Exception {
	
	private String message;

	BankEmptyException(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
}
