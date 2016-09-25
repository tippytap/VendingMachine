/**
 * ProductNotFoundException.java - If the system can't find a product throw this exception
 * @author erik miller
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
