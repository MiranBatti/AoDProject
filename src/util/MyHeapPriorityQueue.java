package util;

import java.lang.Comparable;

/**
 * Heap based implementation of a priority queue.
 * 
 * @author Miran Batti
 * @author Fredrik Lindorf
 * 
 * @version 2015-12-08
 *
 * @param <T>
 */
public class MyHeapPriorityQueue<T extends Comparable<? super T>> implements PriorityQueue<T>{

	private T[] queue;
	private int nbrOfElements, capacity;
	private final static int STANDARD_CAPACITY = 10000;
	
	/**
	 * Initialize priority queue with given maximum capacity.
	 * @param the capacity of this queue.
	 */
	@SuppressWarnings("unchecked")
	public MyHeapPriorityQueue(int capacity) {
		queue = (T[]) new Comparable[capacity + 1]; // börjar på 1 istället för 0
		nbrOfElements = 0;
		this.capacity = capacity;
	}
	
	/**
	 * Initialize priority queue with the standard capacity of 1000.
	 */
	public MyHeapPriorityQueue() {
		this(STANDARD_CAPACITY);
	}
	
	/**
	 * Clears and empties this queue.
	 */
	@Override
	public void clear() {
		nbrOfElements = 0;
		if(!isEmpty())
			queue = null;
	}

	/**
	 * Checks if this queue is empty.
	 * 
	 * @return <tt>true<tt> if empty
	 *         <tt>false<tt> otherwise
	 */
	@Override
	public boolean isEmpty() {
		return (nbrOfElements == 0);
	}

	/**
	 * Returns the number of elements in this queue.
	 * @return the number of elements in this queue.
	 */
	@Override
	public int size() {
		return nbrOfElements;
	}

	/**
	 * Insert a new element to this queue.
	 * 
	 * @param the element to add to this queue.
	 */
	@Override
	public void enqueue(T element) { // Arraybaserad: O(lg n+n)
		if(nbrOfElements == queue.length - 1)
			resizeArray(); // förstorar våran queue array.
		if(element == null)
			throw new NullPointerException();
		
		int currentIndex = size() + 1;
		queue[currentIndex] = element;

		nbrOfElements++;

		// kod lånat och ändrat från http://www.cs.dartmouth.edu/~gevorg/cs10/notes/14/HeapMinPriorityQueue.java
		for(int i  = nbrOfElements; i >= 2 && element.compareTo(queue[getParent(i)]) < 0; i /= 2) // skickar upp det minsta elementet till först i arrayen.
			swap(i, getParent(i));
	}

	/**
	 * Removes the smallest element in this queue.
	 * 
	 * @return the element removed from this queue.
	 * @throws QueueEmptyException if this queue is empty.
	 */
	@Override
	public T dequeue() throws QueueEmptyException{ // deleteMin, Arraybaserad: O(n)
		if(isEmpty()) 
			throw new QueueEmptyException("The Queue is empty.");
		
		T tmp = getFront(); 
//		int last = nbrOfElements--;
		queue[1] = queue[nbrOfElements];
		queue[nbrOfElements] = null;
		nbrOfElements--;
		trickleDown(1); // fix the queue array
		
		return tmp;
	}

	/**
	 * Returns the smallest element from this queue.
	 * 
	 * @return the smallest element from this queue.
	 * @throws QueueIsEmptyException if this queue is empty.
	 */
	@Override
	public T getFront() throws QueueEmptyException{ // getMin, minsta elements har störst prioritet.
		if(this.isEmpty())
			throw new QueueEmptyException("The Queue is empty.");
		return queue[1];
	}

	/**
	 * Always returns <tt>false<tt>. Queue cannot be full.
	 * 
	 * @return <tt>false<tt>
	 */
	@Override
	public boolean isFull() {
		return (nbrOfElements == capacity);
	}
	
	//private method to retrieve parent index of given child index
	private int getParent(int index) {
		if(index <= 0) 
			return -1;
		return (index) / 2;
	}

	//simple algorithm for swaping elements in the queue array
	private void swap(int i, int j) {
		T element = queue[i];
		queue[i] = queue[j];
		queue[j] = element;
	}
	
	// expands array when max capacity has been reached
	private void resizeArray() {
		@SuppressWarnings("unchecked")
		T[] tmp = (T[]) new Comparable[queue.length * 2];
		for (int i = 0; i < nbrOfElements; i++) {
			tmp[i+1] = queue[i+1];
		}
		
		queue = tmp;
	}

	// method for moving an element up when needed. Not used in the final version
	@SuppressWarnings("unused")
	private void bubbleUp(int position) {
		while(position > 1 && queue[position].compareTo(queue[getParent(position)]) < 0) {
			swap(position, position/2);
            position /= 2;			
		}
	}
	
	// method for fixing the queue when an element is dequeued
	private void trickleDown(int parentIndex) { // kod lånat från http://stackoverflow.com/questions/714796/priorityqueue-heap-update, ändringar har gjorts för att anpassa programmet.
		int left = 2 * parentIndex;
		int right = (2 * parentIndex) + 1;
		int min = parentIndex;

		if (nbrOfElements >= left && queue[left].compareTo(queue[min]) < 0)
			min = left;
		if (nbrOfElements >= right && queue[right].compareTo(queue[min]) < 0)
			min = right;

		if (min != parentIndex) {
			swap(parentIndex, min);
			this.trickleDown(min);
		}
	}	
	
}

