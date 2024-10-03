/**
 * 
 */

/**
 * @param <E> 
 * 
 */
public class BSTNode<E extends Comparable<? super E>> {

    private E element;
    private BSTNode<E> left;
    private BSTNode<E> right;
    
    
    public BSTNode(E newElement) {
        this.setElement(newElement);
    }


    public BSTNode<E> getRight()
    {
        return right;
    }


    public void setRight(BSTNode<E> right)
    {
        this.right = right;
    }


    public BSTNode<E> getLeft()
    {
        return left;
    }


    public void setLeft(BSTNode<E> left)
    {
        this.left = left;
    }


    public E getElement()
    {
        return element;
    }


    public void setElement(E element)
    {
        this.element = element;
    }

}
