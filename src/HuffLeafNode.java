
/** Leaf node that has element and weight, no left and right.
 * 
 * @author Cameron Bjork
 *
 * @param <E> - Element stored in leaf node
 */
public class HuffLeafNode<E> implements HuffBaseNode<E> {
	private E element;
	private int weight;
	
	/** Initializes leaf node
	 * @param el - element stored
	 * @param wt - weight of node
	 */
	public HuffLeafNode(E el, int wt) {
		element = el; weight = wt;
	}
	
	/* (non-Javadoc)
	 * @see HuffBaseNode#element()
	 */
	@Override
	public E element() {
		return element;
	}
	
	/* (non-Javadoc)
	 * @see HuffBaseNode#weight()
	 */
	@Override
	public int weight() {
		return weight;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Character= " + element + ", Weight= " + weight;
	}
	
	/* (non-Javadoc)
	 * @see HuffBaseNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return true;
	}

	/* (non-Javadoc)
	 * @see HuffBaseNode#right()
	 */
	@Override
	public HuffBaseNode<E> right() {
		return null;
	}

	/* (non-Javadoc)
	 * @see HuffBaseNode#left()
	 */
	@Override
	public HuffBaseNode<E> left() {
		return null;
	}


}
