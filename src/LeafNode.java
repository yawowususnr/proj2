/**
 * A modified node class for leaf nodes in a binary tree structure.
 * This class manages an array of seminars, allowing for the addition
 * and removal of seminars while maintaining the order of the seminars
 * based on their IDs.
 * 
 * @author Yaw Agyemang    
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
class LeafNode implements BintreeNode {
    private int seminarCount; // size of the array holding seminars
    private Seminar[] seminarList; // array of seminars

    /**
     * Creates a new leaf node
     * 
     * @param seminar
     *            the first seminar to hold in the node
     */
    public LeafNode(Seminar seminar) {
        seminarCount = 0;
        seminarList = new Seminar[1];
        addSeminar(seminar);
    }

    /**
     * Expands the array of seminars to accommodate additional seminars.
     */
    private void growSeminarArray() {
        Seminar[] expandedArray = new Seminar[seminarList.length + 1];
        for (int index = 0; index < seminarList.length; index++) {
            expandedArray[index] = seminarList[index];
        }
        seminarList = expandedArray;
    }

    /**
     * Sorts the array of seminars in ascending order based on their IDs.
     */
    private void orderSeminarArray() {
        for (int firstIndex = 0; firstIndex < seminarCount; firstIndex++) {
            for (int secondIndex = 0; secondIndex < seminarCount; secondIndex++) {
                if (seminarList[firstIndex].id() < seminarList[secondIndex].id()) {
                    Seminar tempSeminar = seminarList[secondIndex];
                    seminarList[secondIndex] = seminarList[firstIndex];
                    seminarList[firstIndex] = tempSeminar;
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
        if (seminarCount == seminarList.length) {
            growSeminarArray();
        }
        seminarList[seminarCount] = seminar;
        seminarCount++;
        orderSeminarArray();
    }

    /**
     * Removes a seminar from the array. If the seminar is found, it
     * shifts the subsequent seminars in the array and decreases the count.
     * 
     * @param seminar
     *            the seminar to remove from the node
     */
    public void removeSeminar(Seminar seminar) {
        for (int index = 0; index < seminarCount; index++) {
            if (seminarList[index].id() == seminar.id()) {
                for (int shiftIndex = index; shiftIndex < seminarCount - 1; shiftIndex++) {
                    seminarList[shiftIndex] = seminarList[shiftIndex + 1];
                }
                seminarCount--;
                break;
            }
        }
        orderSeminarArray();
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
        return seminarCount == 0;
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
        if (seminarList[0].x() == seminar.x() && seminarList[0].y() == seminar.y()) {
            addSeminar(seminar);
            return this;
        }

        InternalNode internalNode = new InternalNode();
        for (int index = 0; index < seminarList.length; index++) {
            internalNode.insert(seminarList[index], level, bbox);
        }
        internalNode.insert(seminar, level, bbox);
        return internalNode;
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
        if (seminarCount == 0) {
            return EmptyNode.getInstance();
        }
        return this;
    }

    /**
     * Gets the array of seminars.
     * 
     * @return the array of seminars
     */
    public Seminar[] getSeminars() {
        return seminarList;
    }

    /**
     * Gets the count of seminars in the array.
     * 
     * @return the number of seminars in the node
     */
    public int getSize() {
        return seminarCount;
    }
}
