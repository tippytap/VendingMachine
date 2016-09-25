/**
 * CollectionFullException.java - when a collection is full, don't add more
 * @author erikmiller
 * @version 1.0
 */
public class CollectionFullException extends Exception {
	
	private String message;
	
	public CollectionFullException(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}

}
