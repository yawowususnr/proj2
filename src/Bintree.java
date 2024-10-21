/**
 * Class that holds the world
 * 
 * @author Yaw Agyemang	
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
class BoundingBox {
    private double minX; // minimum x value
    private double minY; // minimum y value
    private double maxX; // maximum x value
    private double maxY; // maximum y value

    /**
     * Creates a BoundingBox class
     * 
     * @param minX
     *            the min x value
     * @param minY
     *            the min y value
     * @param maxX
     *            the max x value
     * @param maxY
     *            the max y value
     */
    public BoundingBox(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }


    /**
     * Gets the minimum y value
     * 
     * @return the minimum y value
     */
    public double getyMin() {
        return minY;
    }


    /**
     * Gets the minimum x value
     * 
     * @return the minimum x value
     */
    public double getxMin() {
        return minX;
    }


    /**
     * Gets the maximum x value
     * 
     * @return the maximum x value
     */
    public double getxMax() {
        return maxX;
    }


    /**
     * Gets the maximum y value
     * 
     * @return the maximum y value
     */
    public double getyMax() {
        return maxY;
    }
}




/**
 * Binary tree that holds x and y coordinates
 * 
 * @author Yaw Owusu Snr
 * @author Chris Nicoue-Beglah
 * @version 10/9/23
 */
public class Bintree {
    private BintreeNode root; // the root node
    private BoundingBox initialBoundingBox; // the starting bounding box

    /**
     * Creates a new Bintree
     * 
     * @param size
     *            the size of the world
     */
    public Bintree(int size) {
        initialBoundingBox = new BoundingBox(0.0, 0.0, (size - 1), (size - 1));
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
     * @param targetSeminar
     *            the seminar to be found
     * @param searchRadius
     *            the radius around the search seminar to look at
     */
    public void search(Seminar targetSeminar, double searchRadius) {
        int nodesVisited = searchRecursive(root, targetSeminar, searchRadius, 0,
            initialBoundingBox);
        System.out.println(nodesVisited + " nodes visited in this search");
    }


    /**
     * gets the height of the tree
     * 
     * @param node
     *            take in a bintree
     * @return the height of the tree
     */
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
     * 
     * Prints the entire Bintree
     */
    public void print() {
        int treeHeight = getHeight(root);
        // Get the height of the tree
        printRecursive(root, 0, treeHeight);
    }


    /**
     * Recursively print nodoes in the bin tree
     * 
     * @param node
     *            the current node
     * @param currentLevel
     *            current level
     * @param totalHeight
     *            height of tree
     */
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


    /**
     * Performs a recursive search within a binary tree structure.
     *
     * @param node
     *            The current {@code BintreeNode} being processed.
     * @param searchSeminar
     *            The {@code Seminar} being searched for.
     * @param radius
     *            The search radius.
     * @param level
     *            The current depth level in the tree.
     * @param bbox
     *            The bounding box that defines the current search area.
     * @return The number of nodes processed during the search.
     */
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


    /**
     * Handles the case when the current node is an internal node during the
     * search.
     *
     * @param internalNode
     *            The internal node being processed.
     * @param searchSeminar
     *            The {@code Seminar} being searched for.
     * @param radius
     *            The search radius.
     * @param level
     *            The current depth level in the tree.
     * @param bbox
     *            The bounding box defining the search area.
     * @return The number of nodes processed during this internal node's
     *         traversal.
     */
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


    /**
     * Handles an internal node split along the X-axis during the search.
     *
     * @param internalNode
     *            The internal node being processed.
     * @param searchSeminar
     *            The {@code Seminar} being searched for.
     * @param radius
     *            The search radius.
     * @param level
     *            The current depth level in the tree.
     * @param bbox
     *            The bounding box defining the search area.
     * @param midX
     *            The midpoint along the X-axis.
     * @return The number of nodes processed during this X-axis split traversal.
     */
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


    /**
     * Handles an internal node split along the Y-axis during the search.
     *
     * @param internalNode
     *            The internal node being processed.
     * @param searchSeminar
     *            The {@code Seminar} being searched for.
     * @param radius
     *            The search radius.
     * @param level
     *            The current depth level in the tree.
     * @param bbox
     *            The bounding box defining the search area.
     * @param midY
     *            The midpoint along the Y-axis.
     * @return The number of nodes processed during this Y-axis split traversal.
     */
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


    /**
     * Searches both the left and right children of an internal node.
     *
     * @param internalNode
     *            The internal node being processed.
     * @param searchSeminar
     *            The {@code Seminar} being searched for.
     * @param radius
     *            The search radius.
     * @param level
     *            The current depth level in the tree.
     * @param bbox
     *            The bounding box defining the search area.
     * @param mid
     *            The midpoint for splitting.
     * @param isXAxis
     *            Whether the split is along the X-axis.
     * @return The number of nodes processed when both children are searched.
     */
    private int searchBothChildren(
        InternalNode internalNode,
        Seminar searchSeminar,
        double radius,
        int level,
        BoundingBox bbox,
        double mid,
        boolean isXAxis) {
        BoundingBox leftBox;
        BoundingBox rightBox;

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


    /**
     * Searches only the left child of an internal node.
     *
     * @param internalNode
     *            The internal node being processed.
     * @param searchSeminar
     *            The {@code Seminar} being searched for.
     * @param radius
     *            The search radius.
     * @param level
     *            The current depth level in the tree.
     * @param bbox
     *            The bounding box defining the search area.
     * @param mid
     *            The midpoint for splitting.
     * @param isXAxis
     *            Whether the split is along the X-axis.
     * @return The number of nodes processed when only the left child is
     *         searched.
     */
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


    /**
     * Searches only the right child of an internal node.
     *
     * @param internalNode
     *            The internal node being processed.
     * @param searchSeminar
     *            The {@code Seminar} being searched for.
     * @param radius
     *            The search radius.
     * @param level
     *            The current depth level in the tree.
     * @param bbox
     *            The bounding box defining the search area.
     * @param mid
     *            The midpoint for splitting.
     * @param isXAxis
     *            Whether the split is along the X-axis.
     * @return The number of nodes processed when only the right child is
     *         searched.
     */
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


    /**
     * Handles the case when the current node is a leaf node during the search.
     *
     * @param leafNode
     *            The leaf node being processed.
     * @param searchSeminar
     *            The {@code Seminar} being searched for.
     * @param radius
     *            The search radius.
     * @return The number of nodes processed at this leaf node.
     */
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
     * return the root node
     * 
     * @return the root node
     */
    public BintreeNode getRoot() {
        return root;
    }
}
