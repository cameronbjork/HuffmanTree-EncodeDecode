
/** Internal Node that has weight and left & right node.
 * 
 * @author Cameron Bjork
 *
 * @param <E> - Element stored
 */
public class HuffInternalNode<E> implements HuffBaseNode<E>{
	private int weight;
	private HuffBaseNode<E> left;
	private HuffBaseNode<E> right;
	
	/** Initializes the Internal node
	 * @param l - left node
	 * @param r - right node
	 * @param wt - weight of nodes combined
	 */
	public HuffInternalNode(HuffBaseNode<E> l, HuffBaseNode<E> r, int wt) {
		left = l; right = r; weight = wt;
	}
	
	/* (non-Javadoc)
	 * @see HuffBaseNode#left()
	 */
	public HuffBaseNode<E> left() { return left;}
	
	/* (non-Javadoc)
	 * @see HuffBaseNode#right()
	 */
	public HuffBaseNode<E> right() {return right;}
	
	/* (non-Javadoc)
	 * @see HuffBaseNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Weight= " + weight + "\n0: " + left.toString() + " ------ 1: " + right.toString();
	}

	/* (non-Javadoc)
	 * @see HuffBaseNode#weight()
	 */
	@Override
	public int weight() {
		return weight;
	}

	/* (non-Javadoc)
	 * @see HuffBaseNode#element()
	 */
	@Override
	public E element() {
		return null;
	}

}
