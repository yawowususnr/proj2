/**
 * A singleton class representing an empty node in a binary tree.
 * This class implements the Flyweight design pattern, ensuring that
 * there is only one instance of the EmptyNode shared across the tree,
 * thus conserving memory.
 * 
 * The EmptyNode class is used to signify the absence of data within the 
 * binary tree and acts as a placeholder for non-existent nodes. It provides
 * default implementations for the methods in the BintreeNode interface,
 * allowing seamless integration with the binary tree structure.
 * 
 * Since this node represents an empty space, its methods are designed
 * to return neutral results, such as no modifications or indicating
 * the node's emptiness.
 * 
 * @author Yaw Agyemang
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
class EmptyNode implements BintreeNode {
    /** Singleton instance of the EmptyNode. */
    private static EmptyNode instance; // Singleton empty node instance

    /**
     * Private constructor to prevent external instantiation.
     * This enforces the singleton design pattern.
     */
    private EmptyNode() {
    }

    /**
     * Attempts to remove a seminar from the binary tree.
     * As this is an empty node, no removal is performed, and
     * the empty node remains unchanged.
     * 
     * @param seminar
     *            The seminar to remove (ignored).
     * @param level
     *            The level in the binary tree where the removal is attempted.
     * @param bbox
     *            The bounding box for the seminar's location (ignored).
     * @return The unchanged instance of EmptyNode.
     */
    public BintreeNode remove(Seminar seminar, int level, BoundingBox bbox) {
        return EmptyNode.getInstance();
    }

    /**
     * Determines if this node is an internal node.
     * Since this is an empty node, it is not considered an internal node.
     * 
     * @return false, as this is an empty node.
     */
    public boolean isInternal() {
        return false;
    }

    /**
     * Determines if this node is a leaf node.
     * Since this is an empty node, it is not considered a leaf node.
     * 
     * @return false, as this is an empty node.
     */
    public boolean isLeaf() {
        return false;
    }

    /**
     * Inserts a seminar into the binary tree. Since this is an empty node,
     * it gets replaced by a new LeafNode that contains the provided seminar.
     * 
     * @param seminar
     *            The seminar to insert into the tree.
     * @param level
     *            The level in the binary tree where the insertion occurs.
     * @param bbox
     *            The bounding box for the seminar's location.
     * @return A new LeafNode containing the inserted seminar.
     */
    public BintreeNode insert(Seminar seminar, int level, BoundingBox bbox) {
        return new LeafNode(seminar);
    }

    /**
     * Provides the singleton instance of the EmptyNode class.
     * If no instance exists, one is created. Otherwise, the existing instance
     * is returned.
     * 
     * @return The singleton instance of EmptyNode.
     */
    public static EmptyNode getInstance() {
        if (instance == null) {
            instance = new EmptyNode();
        }
        return instance;
    }

    /**
     * Determines if this node is an empty node.
     * 
     * @return true, as this is an empty node.
     */
    public boolean isEmpty() {
        return true;
    }

}
