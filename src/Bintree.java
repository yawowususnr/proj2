/**
 * Class that holds the world
 */
class BoundingBox {
    private double xMin; // minimum x value
    private double yMin; // minimum y value
    private double xMax; // maximum x value
    private double yMax; // maximum y value

    /**
     * Creates a BoundingBox class
     * 
     * @param xMin
     *            the min x value
     * @param yMin
     *            the min y value
     * @param xMax
     *            the max x value
     * @param yMax
     *            the max y value
     */
    public BoundingBox(double xMin, double yMin, double xMax, double yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }


    /**
     * Gets the minimum x value
     * 
     * @return the minimum x value
     */
    public double getxMin() {
        return xMin;
    }


    /**
     * Gets the minimum y value
     * 
     * @return the minimum y value
     */
    public double getyMin() {
        return yMin;
    }


    /**
     * Gets the maximum x value
     * 
     * @return the maximum x value
     */
    public double getxMax() {
        return xMax;
    }


    /**
     * Gets the maximum y value
     * 
     * @return the maximum y value
     */
    public double getyMax() {
        return yMax;
    }
}




/**
 * Binary tree that holds x and y coordinates
 * 
 * @author Yaw Owusu Snr
 * @version 10/9/23
 */
public class Bintree {
    private BintreeNode root; // the root node
    private BoundingBox initialBoundingBox; // the starting bounding box

    /**
     * Creates a new Bintree
     * 
     * @param worldSize
     *            the size of the world
     */
    public Bintree(int worldSize) {
        initialBoundingBox = new BoundingBox(0.0, 0.0, (worldSize - 1),
            (worldSize - 1));
        root = EmptyNode.getInstance();
    }


    /**
     * Gets the bounding box
     * 
     * @return the initial bounding box
     */
    public BoundingBox getWorld() {
        return initialBoundingBox;
    }


    /**
     * Insert a new seminar into the Bintree
     * 
     * @param seminar
     *            the seminar to be inserted
     */
    public void insert(Seminar seminar) {
        root = root.insert(seminar, 0, initialBoundingBox);
    }


    /**
     * Remove a seminar from the Bintree
     * 
     * @param seminar
     *            the seminar to be removed
     */
    public void remove(Seminar seminar) {
        root = root.remove(seminar, 0, initialBoundingBox);
    }


    /**
     * Search for seminars within a certain distance
     * 
     * @param searchSeminar
     *            the seminar to be found
     * @param radius
     *            the radius around the search seminar to look at
     */
    public void search(Seminar targetSeminar, double searchRadius) {
        int nodesVisited = searchRecursive(root, targetSeminar, searchRadius, 0,
            initialBoundingBox);
        System.out.println(nodesVisited + " nodes visited in this search");
    }


    public int getHeight(BintreeNode node) {
        if (node == null || node.isLeaf()) {
            return 0; // Return 0 for null or leaf nodes
        }
        if (node.isInternal()) {
            InternalNode internalNode = (InternalNode)node;
            int leftHeight = getHeight(internalNode.left());
            int rightHeight = getHeight(internalNode.right());
            return Math.max(leftHeight, rightHeight) + 1; // Height of an
                                                          // internal node
        }
        return 0; // Should not reach here, but added for completeness
    }


    /**
     * a
     * Prints the entire Bintree
     */
    public void print() {
        int treeHeight = getHeight(root);
        // Get the height of the tree
        printRecursive(root, 0, treeHeight);
    }


    private void printRecursive(
        BintreeNode node,
        int currentLevel,
        int totalHeight) {
        // Calculate the indentation based on the current level in relation to
        // the total height
        StringBuilder indentation = new StringBuilder();
        int depth = totalHeight - currentLevel;
        for (int i = 0; i < depth; i++) {
            indentation.append("    ");

        }

        if (node.isInternal()) {
            InternalNode internalNode = (InternalNode)node;
            System.out.println(indentation + "(I)");

            // Recursively print the right child first
            printRecursive(internalNode.right(), currentLevel + 1, totalHeight);

            // Recursively print the left child next
            printRecursive(internalNode.left(), currentLevel + 1, totalHeight);
        }
        else if (node.isLeaf()) {
            LeafNode leafNode = (LeafNode)node;
            Seminar[] seminars = leafNode.getSeminars();
            StringBuilder leafInfo = new StringBuilder("(Leaf with " + leafNode
                .getSize() + " objects:");

            for (int i = 0; i < leafNode.getSize(); i++) {
                leafInfo.append(" ").append(seminars[i].id());
            }

            // Convert leafInfo to String and append ')'
            System.out.println(indentation + leafInfo.toString() + ')');
        }
        else {
            System.out.println(indentation + "(E)"); // Represents an empty node
        }
    }


    private int searchRecursive(
        BintreeNode node,
        Seminar searchSeminar,
        double radius,
        int level,
        BoundingBox bbox) {
        if (node.isInternal()) {
            return handleInternalNode((InternalNode)node, searchSeminar, radius,
                level, bbox);
        }
        else if (node.isLeaf()) {
            return handleLeafNode((LeafNode)node, searchSeminar, radius);
        }
        return 1;
    }


    private int handleInternalNode(
        InternalNode internalNode,
        Seminar searchSeminar,
        double radius,
        int level,
        BoundingBox bbox) {
        double midX = (bbox.getxMin() + bbox.getxMax()) / 2.0;
        double midY = (bbox.getyMin() + bbox.getyMax()) / 2.0;

        if (level % 2 == 0) {
            return handleXAxisSplit(internalNode, searchSeminar, radius, level,
                bbox, midX);
        }
        else {
            return handleYAxisSplit(internalNode, searchSeminar, radius, level,
                bbox, midY);
        }
    }


    private int handleXAxisSplit(
        InternalNode internalNode,
        Seminar searchSeminar,
        double radius,
        int level,
        BoundingBox bbox,
        double midX) {
        if (searchSeminar.x() - radius < midX && searchSeminar.x()
            + radius >= midX) {
            return searchBothChildren(internalNode, searchSeminar, radius,
                level, bbox, midX, true);
        }
        else if (searchSeminar.x() - radius < midX) {
            return searchLeftChild(internalNode, searchSeminar, radius, level,
                bbox, midX, true);
        }
        else if (searchSeminar.x() + radius >= midX) {
            return searchRightChild(internalNode, searchSeminar, radius, level,
                bbox, midX, true);
        }
        return 1;
    }


    private int handleYAxisSplit(
        InternalNode internalNode,
        Seminar searchSeminar,
        double radius,
        int level,
        BoundingBox bbox,
        double midY) {
        if (searchSeminar.y() - radius < midY && searchSeminar.y()
            + radius >= midY) {
            return searchBothChildren(internalNode, searchSeminar, radius,
                level, bbox, midY, false);
        }
        else if (searchSeminar.y() - radius < midY) {
            return searchLeftChild(internalNode, searchSeminar, radius, level,
                bbox, midY, false);
        }
        else if (searchSeminar.y() + radius >= midY) {
            return searchRightChild(internalNode, searchSeminar, radius, level,
                bbox, midY, false);
        }
        return 1;
    }


    private int searchBothChildren(
        InternalNode internalNode,
        Seminar searchSeminar,
        double radius,
        int level,
        BoundingBox bbox,
        double mid,
        boolean isXAxis) {
        BoundingBox leftBox, rightBox;
        if (isXAxis) {
            leftBox = new BoundingBox(bbox.getxMin(), bbox.getyMin(), mid, bbox
                .getyMax());
            rightBox = new BoundingBox(mid, bbox.getyMin(), bbox.getxMax(), bbox
                .getyMax());
        }
        else {
            leftBox = new BoundingBox(bbox.getxMin(), bbox.getyMin(), bbox
                .getxMax(), mid);
            rightBox = new BoundingBox(bbox.getxMin(), mid, bbox.getxMax(), bbox
                .getyMax());
        }
        return searchRecursive(internalNode.left(), searchSeminar, radius, level
            + 1, leftBox) + searchRecursive(internalNode.right(), searchSeminar,
                radius, level + 1, rightBox) + 1;
    }


    private int searchLeftChild(
        InternalNode internalNode,
        Seminar searchSeminar,
        double radius,
        int level,
        BoundingBox bbox,
        double mid,
        boolean isXAxis) {
        BoundingBox newBox = isXAxis
            ? new BoundingBox(bbox.getxMin(), bbox.getyMin(), mid, bbox
                .getyMax())
            : new BoundingBox(bbox.getxMin(), bbox.getyMin(), bbox.getxMax(),
                mid);
        return searchRecursive(internalNode.left(), searchSeminar, radius, level
            + 1, newBox) + 1;
    }


    private int searchRightChild(
        InternalNode internalNode,
        Seminar searchSeminar,
        double radius,
        int level,
        BoundingBox bbox,
        double mid,
        boolean isXAxis) {
        BoundingBox newBox = isXAxis
            ? new BoundingBox(mid, bbox.getyMin(), bbox.getxMax(), bbox
                .getyMax())
            : new BoundingBox(bbox.getxMin(), mid, bbox.getxMax(), bbox
                .getyMax());
        return searchRecursive(internalNode.right(), searchSeminar, radius,
            level + 1, newBox) + 1;
    }


    private int handleLeafNode(
        LeafNode leafNode,
        Seminar searchSeminar,
        double radius) {
        for (Seminar seminar : leafNode.getSeminars()) {
            double distance = Math.sqrt(Math.pow(searchSeminar.x() - seminar
                .x(), 2) + Math.pow(searchSeminar.y() - seminar.y(), 2));
            if (distance <= radius) {
                System.out.println("Found a record with key value " + seminar
                    .id() + " at " + seminar.x() + ", " + seminar.y());
            }
        }
        return 1;
    }


    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public BintreeNode getRoot() {
        return root;
    }
}
