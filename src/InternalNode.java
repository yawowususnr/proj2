/**
 * A modified node class for internal nodes
 * 
 * @author Yaw Owusu Snr
 * @version 10/9/23
 */
class InternalNode implements BintreeNode {
    private BintreeNode left; // the right node
    private BintreeNode right; // the left node

    /**
     * Creates a new internal node
     */
    public InternalNode() {
        this.left = EmptyNode.getInstance();
        this.right = EmptyNode.getInstance();
    }


    @Override
    /**
     * Checks if the node is an internal node
     * 
     * @return true if the node is internal
     */
    public boolean isInternal() {
        return true;
    }


    @Override
    /**
     * Checks if the node is a leaf node
     * 
     * @return true if the node is a leaf
     */
    public boolean isLeaf() {
        return false;
    }
    
    public boolean isEmpty() {
        return false;
    }


    /**
     * Returns the left node
     * 
     * @return the left node
     */
    public BintreeNode left() {
        return left;
    }


    /**
     * Returns the right node
     * 
     * @return the right node
     */
    public BintreeNode right() {
        return right;
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
        double midX = (bbox.getxMin() + bbox.getxMax()) / 2.0;
        double midY = (bbox.getyMin() + bbox.getyMax()) / 2.0;

        if (level % 2 == 0) { // Alternating between x and y dimensions
            if (seminar.x() < midX) {
                left = left.insert(seminar, level + 1, new BoundingBox(bbox
                    .getxMin(), bbox.getyMin(), midX, bbox.getyMax()));
            }
            else {
                right = right.insert(seminar, level + 1, new BoundingBox(midX,
                    bbox.getyMin(), bbox.getxMax(), bbox.getyMax()));
            }
        }
        else {
            if (seminar.y() < midY) {
                left = left.insert(seminar, level + 1, new BoundingBox(bbox
                    .getxMin(), bbox.getyMin(), bbox.getxMax(), midY));
            }
            else {
                right = right.insert(seminar, level + 1, new BoundingBox(bbox
                    .getxMin(), midY, bbox.getxMax(), bbox.getyMax()));
            }
        }

        return this;
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
        double midX = (bbox.getxMin() + bbox.getxMax()) / 2.0;
        double midY = (bbox.getyMin() + bbox.getyMax()) / 2.0;

        if (level % 2 == 0) { // Alternating between x and y dimensions
            if (seminar.x() < midX) {
                left = left.remove(seminar, level + 1, new BoundingBox(bbox
                    .getxMin(), bbox.getyMin(), midX, bbox.getyMax()));
            }
            else {
                right = right.remove(seminar, level + 1, new BoundingBox(midX,
                    bbox.getyMin(), bbox.getxMax(), bbox.getyMax()));
            }
        }
        else {
            if (seminar.y() < midY) {
                left = left.remove(seminar, level + 1, new BoundingBox(bbox
                    .getxMin(), bbox.getyMin(), bbox.getxMax(), midY));
            }
            else {
                right = right.remove(seminar, level + 1, new BoundingBox(bbox
                    .getxMin(), midY, bbox.getxMax(), bbox.getyMax()));
            }
        }

        if ((!left.isInternal() && !left.isLeaf()) && right.isLeaf()) {
            return right;
        }
        else if ((!right.isInternal() && !right.isLeaf()) && left.isLeaf()) {
            return left;
        }

        return this;
    }
}