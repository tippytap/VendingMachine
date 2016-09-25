/**
 * BankEmptyException.java - specifics here
 * @author erikmiller
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
