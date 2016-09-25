/**
 * RestrictedAccessException.java - specifics here
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
