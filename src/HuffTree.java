
/** HuffTree that holds internal nodes and leaf nodes
 * @author Cameron Bjork
 *
 * @param <E> - Element stored
 */
public class HuffTree<E> implements Comparable<HuffTree<E>> {
	private HuffBaseNode<E> root;
	
	/** Initializes HuffTree as a leaf node
	 * @param el - element stored
	 * @param wt - weight
	 */
	public HuffTree(E el, int wt) {
		root = new HuffLeafNode<E>(el, wt);
	}
	
	/** Initializes HuffTree as an internal node
	 * @param l - left node
	 * @param r - right node
	 * @param wt - weight
	 */
	public HuffTree (HuffBaseNode<E> l, HuffBaseNode<E> r, int wt) {
		root = new HuffInternalNode<E>(l, r, wt);
	}
	
	/** Gets root
	 * @return root
	 */
	public HuffBaseNode<E> root() { return root;}
	
	/** Gets weight of HuffTree
	 * @return weight
	 */
	public int weight() {
		return root.weight();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return root.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(HuffTree<E> that) {
		if (root.weight() < that.weight())
			return -1;
		else if (root.weight() == that.weight()) return 0;
		else return 1;
	}

}
