/**
 * Bintree node interface
 * 
 * @author Yaw Owusu Snr
 * @version 10/9/23
 */
interface BintreeNode {
    /**
     * Checks if the node is internal
     * 
     * @return true if the node is internal
     */
    boolean isInternal();


    /**
     * Checks if the node is a leaf
     * 
     * @return true if the node is a leaf
     */
    boolean isLeaf();
    
    
    boolean isEmpty();


    /**
     * Inserts a seminar into the node
     * 
     * @param seminar
     *            the seminar to be inserted
     * @param level
     *            the level of the Bintree
     * @param bbox
     *            the bounding box at this level
     * @return the node with the seminar in it
     */
    BintreeNode insert(Seminar seminar, int level, BoundingBox bbox);


    /**
     * Removes a seminar from the node
     * 
     * @param seminar
     *            the seminar to be removed
     * @param level
     *            the level of the Bintree
     * @param bbox
     *            the bounding box at this level
     * @return the node with the seminar removed from it
     */
    BintreeNode remove(Seminar seminar, int level, BoundingBox bbox);
}