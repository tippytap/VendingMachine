/**
 * StackEmptyException.java - Exception to be thrown if stack is empty
 * @author erik miller
 * @version 1.0
 */
public class CollectionEmptyException extends Exception {

	
	private String message;
	
	public CollectionEmptyException(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
}
