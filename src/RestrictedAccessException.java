/**
 * RestrictedAccessException.java - if the user can't enter the correct passcode, throw this exception
 * @author erikmiller
 * @version 1.0
 */

public class RestrictedAccessException extends Exception {
	
	private String message;
	
	public RestrictedAccessException(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}

}
