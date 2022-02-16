
/**
 * @author Cameron Bjork
 *
 * @param <E> - Element of base node
 */
public interface HuffBaseNode<E> {
	
	/** Finds if is leaf that has nothing below it
	 * 
	 * @return - true if is leaf, false if not
	 */
	public boolean isLeaf();
	
	
	/** Gets weight of leaf
	 * 
	 * @return int - weight
	 */
	public int weight();
	
	
	/** Element stored in tree node
	 * 
	 * @return E - element stored
	 */
	public E element();
	
	
	/** Gets node to the right
	 * 
	 * @return HuffBaseNode<E> - Node to the right
	 */
	public HuffBaseNode<E> right();
	
	
	/** Gets node to the left
	 * 
	 * @return HuffBaseNode<E> - Node to the left
	 */
	public HuffBaseNode<E> left();
}
