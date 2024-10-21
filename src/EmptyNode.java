/**
 * 
 * A singleton class representing an empty node in a binary tree,
 * implementing the Flyweight design pattern to conserve memory
 * by sharing a single instance of the empty node.
 * 
 * This class is part of the binary tree structure and is used
 * to represent non-existent nodes. It provides default implementations
 * for the methods defined in the bion tree interface.
 * 
 * @author Yaw Agyemang	
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 * 
 **/
class EmptyNode implements BintreeNode {
    /** The singleton instance of the EmptyNode. */
    private static EmptyNode instance; // the empty node instance

    private EmptyNode() {
    }


    /**
     * Removes a seminar from the binary tree. Since this is an empty node,
     * it will remain unchanged and return itself.
     *
     * @param seminar
     *            the seminar to remove.
     * @param level
     *            the level in the binary tree where the removal occurs.
     * @param bbox
     *            the bounding box for the seminar's location.
     * @return the singleton instance of EmptyNode.
     */
    public BintreeNode remove(Seminar seminar, int level, BoundingBox bbox) {
        return EmptyNode.getInstance();
    }


    /**
     * Indicates whether this node is an internal node.
     *
     * @return false, as this is an empty node.
     */
    public boolean isInternal() {
        return false;
    }


    /**
     * Indicates whether this node is a leaf node.
     *
     * @return false, as this is an empty node.
     */
    public boolean isLeaf() {
        return false;
    }


    /**
     * Inserts a seminar into the binary tree, replacing this empty node
     * with a new leaf node containing the specified seminar.
     *
     * @param seminar
     *            the seminar to insert.
     * @param level
     *            the level in the binary tree where the insertion occurs.
     * @param bbox
     *            the bounding box for the seminar's location.
     * @return a new LeafNode containing the seminar.
     */
    public BintreeNode insert(Seminar seminar, int level, BoundingBox bbox) {
        return new LeafNode(seminar);
    }


    /**
     * Returns the singleton instance of the EmptyNode.
     * If the instance does not exist, it creates one.
     *
     * @return the shared instance of EmptyNode.
     */
    public static EmptyNode getInstance() {
        if (instance == null) {
            instance = new EmptyNode();
        }
        return instance;
    }


    /**
     * Indicates whether this node is an empty node.
     *
     * @return true, as this is an empty node.
     */
    public boolean isEmpty() {
        return true;
    }

}
