/**
 */

/**
 * Bst ndoe class
 * 
 * @author Yaw Owusu Snr
 * @author Chris Nicoue-Beglah
 * @version 10/9/23
 * @param <E>
 *            generic tyupe
 * 
 */
public class BSTNode<E extends Comparable<? super E>> {

    private E element;
    private BSTNode<E> left;
    private BSTNode<E> right;

    // ----------------------------------------------------------
    /**
     * Create a new BSTNode object.
     * 
     * @param newElement
     *            KVPair object in our case
     */
    public BSTNode(E newElement) {
        this.setElement(newElement);
    }


    // ----------------------------------------------------------
    /**
     * gets BST node to the right
     * 
     * @return right node
     */
    public BSTNode<E> getRight() {
        return right;
    }


    // ----------------------------------------------------------
    /**
     * Assigns BST node to the right child of current node
     * 
     * @param right
     *            BST Node
     */
    public void setRight(BSTNode<E> right) {
        this.right = right;
    }


    // ----------------------------------------------------------
    /**
     * gets BST node to the left
     * 
     * @return left node
     */
    public BSTNode<E> getLeft() {
        return left;
    }


    // ----------------------------------------------------------
    /**
     * Assigns BST node to the right child of current node
     * 
     * @param left
     *            bst node to add
     */
    public void setLeft(BSTNode<E> left) {
        this.left = left;
    }


    // ----------------------------------------------------------
    /**
     * Gets KeyValuePair object
     * 
     * @return KVPair object
     */
    public E getElement() {
        return element;
    }


    // ----------------------------------------------------------
    /**
     * Sets KVPair object
     * 
     * @param element
     *            KVPair object
     */
    public void setElement(E element) {
        this.element = element;
    }

}
