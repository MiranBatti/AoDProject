package util;

/**
 * Exception class that signals if an operation that is not allowed has been
 * done on an empty queue.
 * 
 * @author Miran Batti
 * @author Fredrik Lindorf
 * 
 * @version 2015-12-01
 *
 */
@SuppressWarnings("serial")
public class QueueEmptyException extends RuntimeException {
	public QueueEmptyException(String message) {
		super(message);
	}
}
