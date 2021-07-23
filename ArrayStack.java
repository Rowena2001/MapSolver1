/**
 *  @Rowena CS1027 | 251073629
 *  This class implements a stack using an array.
 */

public class ArrayStack<T> implements ArrayStackADT<T> {
    
    private T[] stack;
    private int top;
    private int initialCapacity;
    private int sizeIncrease;
    private int sizeDecrease;

    /**
     * Creates an empty stack using an array of length equal to the value of 
     * the first parameter. The values of the parameters will be stored in 
     * initialCapacity, sizeIncrease and sizeDecrease, respectively.
     */
	public ArrayStack(int initialCap, int sizeInc, int sizeDec) {
        top = -1;
        stack = (T[]) (new Object[initialCap]);
        initialCapacity = initialCap;
        sizeIncrease = sizeInc;
        sizeDecrease = sizeDec;
    }
	
    /**
     * Adds dataItem to the top of the stack and updates the value of top.
     */
    public void push (T dataItem) {
    	top++;
    	if (stack.length == top + 1) {
            stack = java.util.Arrays.copyOf(stack, stack.length + sizeIncrease); // returns new array of new size
        }
        stack[top] = dataItem;
    }
    
    /**
     * Removes and returns the data item at the top of the stack and updates the value of top.
     * An EmptyStackException is thrown if the stack is empty.
     */
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("stack is empty. cannot pop any items.");
        }
        T result = stack[top];
        stack[top] = null;
        top--;
        if (size() < stack.length/4 & stack.length > initialCapacity) {
            stack = java.util.Arrays.copyOf(stack, stack.length - sizeDecrease);
        }
        return result;
        
    }
    
    /**
     * Returns the data item at the top of the stack without removing it.
     * An EmptyStackException is thrown if the stack is empty.
     */
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("stack is empty. cannot pop any items.");
        }
        return stack[top];
    }
    
    /**
     * Returns true if the stack is empty and it returns false otherwise.
     */
    public boolean isEmpty() {
        return (top+1 == 0);
    }
    
    /**
     * Returns the number of data items in the stack.
     */
    public int size() {
    	return top+1;
    }
    
    /**
     * Returns the length or capacity of the array stack.
     */
    public int length() {
        return stack.length;
    }
    
    /**
     * Returns a String representation of the stack of the form:
     * “Stack: elem1, elem2, ...”
     */
    public String toString() {
        String result = "Stack: ";
        for (int index=0; index < top; index++) {
            result = result + stack[index].toString() + ", ";
        }
        for (int i=top; i<top+1; i++) { // does not add comma after last element
        	result = result + stack[i].toString();
        }
        return result;
    
    }
    
    
}
