/**
 * A modified node class for leaf nodes in a binary tree structure.
 * This class manages an array of seminars, allowing for the addition
 * and removal of seminars while maintaining the order of the seminars
 * based on their IDs.
 * 
 * @author Yaw Owusu Jnr
 * @author Chris Nicoue-Beglah
 * @version 10/9/23
 */
class LeafNode implements BintreeNode {
    private int size; // size of the array holding seminars
    private Seminar[] seminars; // array of seminars

    /**
     * Creates a new leaf node
     * 
     * @param seminar
     *            the first seminar to hold in the node
     */
    public LeafNode(Seminar seminar) {
        size = 0;
        seminars = new Seminar[1];
        addSeminar(seminar);
    }


    /**
     * Expands the array of seminars to accommodate additional seminars.
     */
    private void expandArray() {
        Seminar[] newArray = new Seminar[seminars.length + 1];

        for (int i = 0; i < seminars.length; i++) {
            newArray[i] = seminars[i];
        }

        seminars = newArray;
    }


    /**
     * Sorts the array of seminars in ascending order based on their IDs.
     */
    private void sortArray() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (seminars[i].id() < seminars[j].id()) {
                    Seminar tempValue = seminars[j];
                    seminars[j] = seminars[i];
                    seminars[i] = tempValue;
                }
            }
        }
    }


    /**
     * Adds a new seminar to the array. If the array is full, it expands
     * the array and then adds the seminar, followed by sorting the array.
     * 
     * @param seminar
     *            the seminar to add to the node
     */
    public void addSeminar(Seminar seminar) {
        if (size == seminars.length) {
            expandArray();
        }

        seminars[size] = seminar;
        size++;

        sortArray();
    }


    /**
     * Removes a seminar from the array. If the seminar is found, it
     * shifts the subsequent seminars in the array and decreases the size.
     * 
     * @param seminar
     *            the seminar to remove from the node
     */
    public void removeSeminar(Seminar seminar) {
        for (int i = 0; i < size; i++) {
            if (seminars[i].id() == seminar.id()) {
                for (int j = i; j < size - 1; j++) {
                    seminars[j] = seminars[j + 1];
                }
                size--;
            }
        }

        sortArray();
    }


    @Override
    /**
     * Checks if the node is an internal node.
     * 
     * @return false since this is a leaf node
     */
    public boolean isInternal() {
        return false;
    }


    @Override
    /**
     * Checks if the node is a leaf node.
     * 
     * @return true since this is a leaf node
     */
    public boolean isLeaf() {
        return true;
    }


    /**
     * Checks if the node is empty (contains no seminars).
     * 
     * @return true if there are no seminars in the node
     */
    public boolean isEmpty() {
        return false;
    }


    @Override
    /**
     * Inserts a seminar into the node. If the node already contains
     * seminars at the same coordinates, the seminar is added to this leaf.
     * If not, the leaf is replaced by an internal node.
     * 
     * @param seminar
     *            the seminar to insert
     * @param level
     *            the current level in the Bintree
     * @param bbox
     *            the bounding box at this level
     * @return a node holding the data
     */
    public BintreeNode insert(Seminar seminar, int level, BoundingBox bbox) {
        // If the coordinates of the first seminar match the new seminar's
        // coordinates
        if (seminars[0].x() == seminar.x() && seminars[0].y() == seminar.y()) {
            addSeminar(seminar); // Add the new seminar to the current leaf node
            return this; // Return the current leaf node
        }

        // Create a new internal node to replace the current leaf node
        InternalNode newInternalNode = new InternalNode();

        // Transfer all existing seminars to the new internal node using a
        // numeric for loop
        for (int i = 0; i < seminars.length; i++) {
            newInternalNode.insert(seminars[i], level, bbox);
        }

        // Insert the new seminar into the internal node
        newInternalNode.insert(seminar, level, bbox);

        // Return the newly created internal node
        return newInternalNode;
    }


    @Override
    /**
     * Removes a seminar from the node. If the node becomes empty,
     * it returns an instance of EmptyNode.
     * 
     * @param seminar
     *            the seminar to remove
     * @param level
     *            the current level in the Bintree
     * @param bbox
     *            the bounding box at this level
     * @return a node without the seminar
     */
    public BintreeNode remove(Seminar seminar, int level, BoundingBox bbox) {
        removeSeminar(seminar);

        if (size == 0) {
            return EmptyNode.getInstance();
        }

        return this;
    }


    /**
     * Gets the array
     * 
     * @return the array of seminar
     */
    public Seminar[] getSeminars() {
        return seminars;
    }


    /**
     * Gets the size of the array
     * 
     * @return the size of the array
     */
    public int getSize() {
        return size;
    }
}
