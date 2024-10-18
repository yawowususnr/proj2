/**
 * A modified node class for leaf nodes
 * 
 * @author Yaw Owusu Snr
 * @version 10/9/23
 */
class LeafNode implements BintreeNode {
    private int size; // size of the array holding seminars
    private Seminar[] seminars; // array of seminars

    /**
     * Creates a new leaf node
     * 
     * @param seminar
     *            the first seminar to hold
     */
    public LeafNode(Seminar seminar) {
        size = 0;
        seminars = new Seminar[1];
        addSeminar(seminar);
    }


    private void expandArray() {
        Seminar[] newArray = new Seminar[seminars.length + 1];

        for (int i = 0; i < seminars.length; i++) {
            newArray[i] = seminars[i];
        }

        seminars = newArray; 
    }


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
     * Adds a new seminar to the array
     * 
     * @param seminar
     *            the seminar to add
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
     * Removes a seminar from the array
     * 
     * @param seminar
     *            the seminar to remove
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
     * Checks if the node is an internal node
     * 
     * @return true if the node is internal
     */
    public boolean isInternal() {
        return false;
    }


    @Override
    /**
     * Checks if the node is a leaf node
     * 
     * @return true if the node is a leaf
     */
    public boolean isLeaf() {
        return true;
    }
    
    public boolean isEmpty() {
        return false;
    }


    @Override
    /**
     * Inserts a seminar into the node
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
        // add to the leaf node if the coordinates are the same
        if (seminars[0].x() == seminar.x() && seminars[0].y() == seminar.y()) {
            addSeminar(seminar);
            return this;
        }

        // Replace the leaf node with an internal node and insert the new
        // seminar
        InternalNode internalNode = new InternalNode();
        for (Seminar sem : seminars) {
            internalNode.insert(sem, level, bbox);
        }
        internalNode.insert(seminar, level, bbox);
        return internalNode;
    }


    @Override
    /**
     * Removes a seminar into the node
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
