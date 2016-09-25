/**
 * NotEnoughMoneyException.java - specifics here
 * @author erikmiller
 * @version 1.0
 */
public class NotEnoughMoneyException extends Exception {

	private String message;
	
	public NotEnoughMoneyException(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
}
