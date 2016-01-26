package util;
import java.lang.Comparable;

/**
 * 
 * @author Miran Batti
 * @author Fredrik Lindorf
 * 
 * @version 2015-12-08
 *
 * @param <T>
 */
public interface PriorityQueue<T extends Comparable<? super T>> {
	
	/**
	 * Removes elements from this queue.
	 */
	public void clear();
	
	/**
	 * Checks if this queue is empty.
	 * @return true if empty, otherwise false.
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the number of elements in this queue.
	 * @return the number of elements in this queue.
	 */
	public int size();
	
	/**
	 * Insert an element in this queue.
	 * Element needs to be a type that extends <tt>Comparable<tt>
	 * @param the element to insert
	 */
	public void enqueue(T element);
	
	/**
	 * Remove an element from the list. The lowest value is prioritized.
	 * @return the lowest value in the queue.
	 * @throws QueueEmptyException
	 */
	public T dequeue() throws QueueEmptyException; 
	
	/**
	 * Return lowest value in the queue without removing it.
	 * @return the first element in queue.
	 * @throws QueueEmptyException
	 */
	public T getFront() throws QueueEmptyException;
	
	/**
	 * Always returns false. Queue cannot be filled.
	 * @return false
	 */
	public boolean isFull();
}
