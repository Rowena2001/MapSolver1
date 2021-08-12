/**
 *  @author Rowena
 *  This is the class of exceptions that will be thrown by methods
 *  pop and peek when invoked on an empty stack.
 */

public class EmptyStackException extends Exception {
	
	public EmptyStackException(String message) {
		super(message);
	}
}
