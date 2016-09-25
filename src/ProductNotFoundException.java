/**
 * ProductNotFoundException.java - specifics here
 * @author erikmiller
 * @version 1.0
 */

/**
 * @author erikmiller
 *
 */
public class ProductNotFoundException extends Exception {
	
	private String message;
	
	public ProductNotFoundException(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}

}
