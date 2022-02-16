import java.util.ArrayList;

/**
 * Program for a min heap
 * @author Cameron Bjork
 * 
 * @param <E> - Comparable element
 */
public class MinHeap <E extends Comparable<? super E>> {
	private ArrayList<E> heap;
	private int n;
	
	/**
	 * Constructor for min heap that takes in any sort of ArrayList
	 * 
	 * @param h - ArrayList being inputed
	 */
	public MinHeap(ArrayList<E> h) { 
		heap = h; 
		n = heap.size(); 
		buildHeap();
	}

	/**
	 * Default constructor for min heap
	 */
	public MinHeap() {
		heap = new ArrayList<E>();
		n = heap.size();
	}
	
	/**
	 * Builds the heap and its contents
	 */
	public void buildHeap() {
		for (int i=n/2 -1; i>=0; i--) {
			siftDown(i);
		}
	}
	
	/**
	 * Sifts everything in the heap down
	 * 
	 * @param pos - Current position of heap
	 */
	public void siftDown(int pos) {
		assert (pos >= 0) && (pos < n) : "Illegal heap position";
		while(!isLeaf(pos)) {
			int j = leftChild(pos);
			if(j < (n-1) && heap.get(j).compareTo(heap.get(j + 1)) > 0) {
				j++;
			}
			
			if (heap.get(pos).compareTo(heap.get(j)) <= 0) {
				return;
			}
			
			this.swap(pos, j);
			pos = j;
		}
	}
	
	/**
	 * Gets the size of the heap
	 * 
	 * @return size of heap
	 */
	public int heapSize() {
		return n;
	}
	
	/**
	 * Checks if current position is a leaf
	 * 
	 * @param pos - Position of leaf to be checked
	 * @return true or false on leaf position
	 */
	public boolean isLeaf(int pos) {
		return (pos >= n/2) && (pos < n);
	}
	
	/**
	 * Gets the left child of the current leaf/position
	 * 
	 * @param pos - Position of current leaf
	 * @return left child of current position
	 */
	public int leftChild(int pos) {
		assert pos < n/2 : "Position has no left child";
		return 2*pos + 1;
	}
	
	/**
	 * Gets the right child of the current leaf/position
	 * 
	 * @param pos - Position of current leaf
	 * @return right child of current position
	 */
	public int rightChild(int pos) {
		assert pos < (n-1)/2 : "Position has no right child";
		return 2*pos + 2;
	}
	
	/**
	 * Gets the parent of the current leaf/position
	 * 
	 * @param pos - Position of current leaf
	 * @return parent of current position
	 */
	public int parent(int pos) {
		assert pos > 0 : "Position has no parent";
		return (pos-1)/2;
	}
	
	/**
	 * Inserts a value into the ordered position
	 * 
	 * @param val - Value to be inputted
	 */
	public void insert(E val) {
		int current = n++;
		heap.add(current, val);
		
		while (current != 0 && heap.get(current).compareTo(heap.get(this.parent(current))) < 0) {
			this.swap(current, this.parent(current));
			current = this.parent(current);
		}
	}
	
	/**
	 * Swaps the two items at their respective positions
	 * 
	 * @param current - Position that needs to be swapped with parent
	 * @param parent - Position that needs to be swapped with current
	 */
	public void swap(int current, int parent) {
		E temp;
		E tempParent;
		temp = heap.get(current);
		tempParent = heap.get(parent);
		heap.set(current, tempParent);
		heap.set(parent, temp);
	}
	
	/**
	 * Removes the min element in the heap
	 * 
	 * @return Min element in heap
	 */
	public E removemin() {
		assert n > 0 : "Removing from empty heap";
		this.swap(0, --n);
		if(n != 0) {
			siftDown(0);
		}
		return heap.get(n);
	}
	
	/**
	 * Removes from heap at a position
	 * 
	 * @param pos - position to remove from
	 * @return element in heap
	 */
	public E remove(int pos) {
		assert (pos >= 0) && (pos < n) : "Illegal heap position";
		if (pos == n-1) {
			n--;
		}
		else {
			swap(pos, --n);
			while(pos > 0 && heap.get(pos).compareTo(heap.get(parent(pos))) < 0) {
				swap(pos, parent(pos));
				pos = parent(pos);
			}
			if (n != 0)
				siftDown(pos);
		}
	
		return heap.get(n);
		
	}
	
	/**
	 * Checks if heap is empty
	 * 
	 * @return true or false
	 */
	public boolean isEmpty() {
		return n == 0;
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < n; i++) {
			
			str+= "[ "  + heap.get(i).toString() + " ], ";
		}
		return str;
	}
	
	
}
