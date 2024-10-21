/**
 * A modified node class for internal nodes in a binary tree.
 * 
 * This class represents an internal node that can contain left and right child
 * nodes,
 * as well as perform insertions and removals of seminars based on their spatial
 * coordinates.
 * 
 * @author Yaw Agyemang	
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
class InternalNode implements BintreeNode {
    private BintreeNode lNode; // the right node
    private BintreeNode rNode; // the left node

    /**
     * Creates a new internal node initialized to empty nodes for both children.
     */
    public InternalNode() {
        this.lNode = EmptyNode.getInstance();
        this.rNode = EmptyNode.getInstance();
    }


    @Override
    /**
     * Checks if the node is an internal node.
     * 
     * @return true since this is an internal node.
     */
    public boolean isInternal() {
        return true;
    }


    @Override
    /**
     * Checks if the node is a leaf node.
     * 
     * @return false since this is not a leaf node.
     */
    public boolean isLeaf() {
        return false;
    }


    /**
     * Checks if the node is empty.
     * 
     * @return false since this is an internal node.
     */
    public boolean isEmpty() {
        return false;
    }


    /**
     * Returns the left child node.
     * 
     * @return the left child node.
     */
    public BintreeNode left() {
        return lNode;
    }


    /**
     * Returns the right child node.
     * 
     * @return the right child node.
     */
    public BintreeNode right() {
        return rNode;
    }


    @Override
    /**
     * Inserts a seminar into the node based on its spatial coordinates.
     * 
     * The method determines the appropriate child node based on the level
     * of the node and the seminar's x or y coordinate.
     * 
     * @param seminar
     *            the seminar to insert.
     * @param level
     *            the current level in the binary tree.
     * @param boundry
     *            the bounding box at this level.
     * @return the node holding the data after insertion.
     */
    public BintreeNode insert(Seminar seminar, int level, BoundingBox boundry) {
        // Calculate midpoints for x and y dimensions based on the bounding box
        double midX = (boundry.getxMin() + boundry.getxMax()) * 0.5;
        double midY = (boundry.getyMin() + boundry.getyMax()) * 0.5;

        // Determine which dimension to use for insertion based on the level
        if (level % 2 == 0) { // Even level: use x dimension
            BoundingBox leftBBox = new BoundingBox(boundry.getxMin(), boundry
                .getyMin(), midX, boundry.getyMax());
            BoundingBox rightBBox = new BoundingBox(midX, boundry.getyMin(),
                boundry.getxMax(), boundry.getyMax());

            if (seminar.x() < midX) {
                lNode = lNode.insert(seminar, level + 1, leftBBox);
            }
            else {
                rNode = rNode.insert(seminar, level + 1, rightBBox);
            }
        }
        else { // Odd level: use y dimension
            BoundingBox topBBox = new BoundingBox(boundry.getxMin(), boundry
                .getyMin(), boundry.getxMax(), midY);
            BoundingBox bottomBBox = new BoundingBox(boundry.getxMin(), midY,
                boundry.getxMax(), boundry.getyMax());

            if (seminar.y() < midY) {
                lNode = lNode.insert(seminar, level + 1, topBBox);
            }
            else {
                rNode = rNode.insert(seminar, level + 1, bottomBBox);
            }
        }

        return this;
    }


    @Override
    /**
     * Removes a seminar from the node based on its spatial coordinates.
     * 
     * The method determines the appropriate child node for removal based on the
     * level
     * of the node and the seminar's x or y coordinate.
     * 
     * @param seminar
     *            the seminar to remove.
     * @param level
     *            the current level in the binary tree.
     * @param bbox
     *            the bounding box at this level.
     * @return the node without the seminar after removal.
     */
    public BintreeNode remove(Seminar seminar, int level, BoundingBox bbox) {
        // Calculate midpoints for x and y dimensions based on the bounding box
        double midX = (bbox.getxMin() + bbox.getxMax()) * 0.5;
        double midY = (bbox.getyMin() + bbox.getyMax()) * 0.5;

        // Determine which dimension to use for removal based on the level
        if (level % 2 == 0) { // Even level: x dimension
            BoundingBox leftBBox = new BoundingBox(bbox.getxMin(), bbox
                .getyMin(), midX, bbox.getyMax());
            BoundingBox rightBBox = new BoundingBox(midX, bbox.getyMin(), bbox
                .getxMax(), bbox.getyMax());

            if (seminar.x() < midX) {
                lNode = lNode.remove(seminar, level + 1, leftBBox);
            }
            else {
                rNode = rNode.remove(seminar, level + 1, rightBBox);
            }
        }
        else { // Odd level: y dimension
            BoundingBox upperBBox = new BoundingBox(bbox.getxMin(), bbox
                .getyMin(), bbox.getxMax(), midY);
            BoundingBox lowerBBox = new BoundingBox(bbox.getxMin(), midY, bbox
                .getxMax(), bbox.getyMax());

            if (seminar.y() < midY) {
                lNode = lNode.remove(seminar, level + 1, upperBBox);
            }
            else {
                rNode = rNode.remove(seminar, level + 1, lowerBBox);
            }
        }

        // Check for cases where one child node can be returned directly
        if (!lNode.isInternal() && !lNode.isLeaf() && rNode.isLeaf()) {
            return rNode; // Return right node if left is not valid
        }
        else if (!rNode.isInternal() && !rNode.isLeaf() && lNode.isLeaf()) {
            return lNode; // Return left node if right is not valid
        }

        return this; // Default case: return the current node
    }
}
