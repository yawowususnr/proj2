/**
 * Represents an internal node in a binary space partitioning tree (bintree).
 * 
 * Internal nodes divide the 2D space alternately by x and y coordinates as the tree
 * descends, with even levels partitioning on x-coordinate and odd levels on y-coordinate.
 * Each internal node maintains two children: left/top and right/bottom, depending on
 * the current partitioning dimension.
 * 
 * @author Yaw Agyemang 
 * @author Yaw Owusu Jnr
 * @version 10/20/24
 */
class InternalNode implements BintreeNode {
    private BintreeNode lNode; // Left/top child node
    private BintreeNode rNode; // Right/bottom child node

    /**
     * Constructs a new internal node with empty children.
     * Both children are initialized as empty nodes using the singleton pattern.
     */
    public InternalNode() {
        this.lNode = EmptyNode.getInstance();
        this.rNode = EmptyNode.getInstance();
    }

    @Override
    public boolean isInternal() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Retrieves the left/top child node.
     * 
     * @return the left or top child node depending on the level
     */
    public BintreeNode left() {
        return lNode;
    }

    /**
     * Retrieves the right/bottom child node.
     * 
     * @return the right or bottom child node depending on the level
     */
    public BintreeNode right() {
        return rNode;
    }

    @Override
    /**
     * Inserts a seminar into the appropriate subtree based on spatial partitioning.
     * 
     * @param seminar  the seminar to be inserted
     * @param level    current tree level (0-based) determining partition dimension
     * @param boundry  the spatial boundary for this node's region
     * @return the current node after insertion
     */
    public BintreeNode insert(Seminar seminar, int level, BoundingBox boundry) {
        boolean isXPartition = (level % 2 == 0);
        double mid = isXPartition 
            ? (boundry.getxMin() + boundry.getxMax()) * 0.5
            : (boundry.getyMin() + boundry.getyMax()) * 0.5;
        
        BoundingBox leftBox = createLeftBoundingBox(boundry, mid, isXPartition);
        BoundingBox rightBox = createRightBoundingBox(boundry, mid, isXPartition);

        double compareValue = isXPartition ? seminar.x() : seminar.y();
        if (compareValue < mid) {
            lNode = lNode.insert(seminar, level + 1, leftBox);
        }
        else {
            rNode = rNode.insert(seminar, level + 1, rightBox);
        }

        return this;
    }

    @Override
    /**
     * Removes a seminar from the appropriate subtree based on spatial partitioning.
     * After removal, consolidates the tree if possible by removing unnecessary internal nodes.
     * 
     * @param seminar  the seminar to be removed
     * @param level    current tree level (0-based) determining partition dimension
     * @param bbox     the spatial boundary for this node's region
     * @return the modified node or its replacement after removal and consolidation
     */
    public BintreeNode remove(Seminar seminar, int level, BoundingBox bbox) {
        boolean isXPartition = (level % 2 == 0);
        double mid = isXPartition 
            ? (bbox.getxMin() + bbox.getxMax()) * 0.5
            : (bbox.getyMin() + bbox.getyMax()) * 0.5;

        BoundingBox leftBox = createLeftBoundingBox(bbox, mid, isXPartition);
        BoundingBox rightBox = createRightBoundingBox(bbox, mid, isXPartition);

        double compareValue = isXPartition ? seminar.x() : seminar.y();
        if (compareValue < mid) {
            lNode = lNode.remove(seminar, level + 1, leftBox);
        }
        else {
            rNode = rNode.remove(seminar, level + 1, rightBox);
        }

        return consolidateNode();
    }

    /**
     * Creates a bounding box for the left/top child node.
     */
    private BoundingBox createLeftBoundingBox(
        BoundingBox parent, 
        double mid, 
        boolean isXPartition) {
        return isXPartition
            ? new BoundingBox(parent.getxMin(), parent.getyMin(), 
                mid, parent.getyMax())
            : new BoundingBox(parent.getxMin(), parent.getyMin(), 
                parent.getxMax(), mid);
    }

    /**
     * Creates a bounding box for the right/bottom child node.
     */
    private BoundingBox createRightBoundingBox(
        BoundingBox parent, 
        double mid, 
        boolean isXPartition) {
        return isXPartition
            ? new BoundingBox(mid, parent.getyMin(), 
                parent.getxMax(), parent.getyMax())
            : new BoundingBox(parent.getxMin(), mid, 
                parent.getxMax(), parent.getyMax());
    }

    /**
     * Consolidates the node by removing unnecessary internal nodes.
     * 
     * @return the consolidated node or its replacement
     */
    private BintreeNode consolidateNode() {
        if (!lNode.isInternal() && !lNode.isLeaf() && rNode.isLeaf()) {
            return rNode;
        }
        if (!rNode.isInternal() && !rNode.isLeaf() && lNode.isLeaf()) {
            return lNode;
        }
        return this;
    }
}