/**
 * A class that defines a bounding box in a 2D space, represented by its minimum
 * and maximum x and y coordinates.
 * 
 * <p>
 * This class allows querying the boundaries of the box through getter methods
 * and provides a simple structure for managing a rectangular region in a
 * two-dimensional world.
 * </p>
 * 
 * @author Yaw Agyemang
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
class BoundingBox {
	private double minimumX;
	private double minimumY;
	private double maximumX;
	private double maximumY;

	/**
	 * Constructs a BoundingBox object with the specified minimum and maximum x and
	 * y coordinates.
	 * 
	 * @param minX the minimum x-coordinate
	 * @param minY the minimum y-coordinate
	 * @param maxX the maximum x-coordinate
	 * @param maxY the maximum y-coordinate
	 */
	public BoundingBox(double minX, double minY, double maxX, double maxY) {
		this.minimumX = minX;
		this.minimumY = minY;
		this.maximumX = maxX;
		this.maximumY = maxY;
	}

	/**
	 * Returns the maximum y-coordinate of the bounding box.
	 * 
	 * @return the maximum y-coordinate
	 */
	public double getyMax() {
		return maximumY;
	}

	/**
	 * Returns the maximum x-coordinate of the bounding box.
	 * 
	 * @return the maximum x-coordinate
	 */
	public double getxMax() {
		return maximumX;
	}

	/**
	 * Returns the minimum x-coordinate of the bounding box.
	 * 
	 * @return the minimum x-coordinate
	 */
	public double getxMin() {
		return minimumX;
	}

	/**
	 * Returns the minimum y-coordinate of the bounding box.
	 * 
	 * @return the minimum y-coordinate
	 */
	public double getyMin() {
		return minimumY;
	}

}

/**
 * A binary tree that stores nodes containing x and y coordinates. This tree is
 * designed for efficient spatial queries, using a bounding box to define the
 * search area.
 * 
 * @author Yaw Agyemang
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class Bintree {
	private BintreeNode root;
	private BoundingBox rootBbox;

	/**
	 * Initialize a new Bintree object
	 * 
	 * @param size of the world
	 */
	public Bintree(int size) {
		rootBbox = new BoundingBox(0.0, 0.0, (size - 1), (size - 1));
		root = EmptyNode.getInstance();
	}

	/**
	 * Returns the instantiated bounding box object
	 * 
	 * @return the initial bounding box
	 */
	public BoundingBox getWorld() {
		return rootBbox;
	}

	/**
	 * Add a new seminar object in the Bintree
	 * 
	 * @param semObject the seminar to be added
	 */
	public void insert(Seminar semObject) {
		root = root.insert(semObject, 0, rootBbox);
	}

	/**
	 * Remove a seminar object from the Bintree
	 * 
	 * @param semObject the seminar to be removed
	 */
	public void remove(Seminar semObject) {
		root = root.remove(semObject, 0, rootBbox);
	}

	/**
	 * Finds seminar objects that fall within a range
	 * 
	 * @param targetSemObject the seminar find
	 * @param radius          the radius around the seminar object to find
	 */
	public void search(Seminar targetSemObject, double radius) {
		int nodesVisited = searchRecursive(root, targetSemObject, radius, 0, rootBbox);
		System.out.println(nodesVisited + " nodes visited in this search");
	}

	/**
	 * Returns the height of the bintree
	 * 
	 * @param root root of the bintree
	 * @return the height of the bintree
	 */
	public int getHeight(BintreeNode root) {
		if (root == null || root.isLeaf()) {
			return 0;
		}
		if (root.isInternal()) {
			InternalNode internalNode = (InternalNode) root;
			int heightOfLeft = getHeight(internalNode.left());
			int heightOfRight = getHeight(internalNode.right());
			return Math.max(heightOfLeft, heightOfRight) + 1;
		}
		return 0;
	}

	/**
	 * Prints the reverse InOrder traversal of the Bintree
	 */
	public void print() {
		int heightOfTree = getHeight(root);
		printRecursive(root, 0, heightOfTree);
	}

	/**
	 * Recursively print seminarObjects in the bintree in a reverse inOrder manner
	 * 
	 * @param node        current node being visited
	 * @param nodeDepth   current depth of the node being visited
	 * @param totalHeight height of the bintree
	 */
	private void printRecursive(BintreeNode node, int nodeDepth, int heightOfTree) {

		StringBuilder spacing = new StringBuilder();
		for (int i = 0; i < heightOfTree - nodeDepth; i++) {
			spacing.append("    ");

		}

		if (node.isInternal()) {
			InternalNode internalNode = (InternalNode) node;
			System.out.println(spacing + "(I)");

			printRecursive(internalNode.right(), nodeDepth + 1, heightOfTree);

			printRecursive(internalNode.left(), nodeDepth + 1, heightOfTree);
		} else if (node.isLeaf()) {
			LeafNode leafNode = (LeafNode) node;
			Seminar[] seminarObjects = leafNode.getSeminars();
			StringBuilder leafStringBuilder = new StringBuilder("(Leaf with " + leafNode.getSize() + " objects:");

			for (int i = 0; i < leafNode.getSize(); i++) {
				leafStringBuilder.append(" ").append(seminarObjects[i].id());
			}

			System.out.println(spacing + leafStringBuilder.toString() + ')');
		} else {
			System.out.println(spacing + "(E)");
		}
	}

	/**
	 * Recursively searches the bintree based on seminar Object and search radius
	 *
	 * @param node          The current seminar object being visited.
	 * @param targetSeminar The target seminar object being searched for.
	 * @param radius        Search radius.
	 * @param depth         Current depth of seminar object being visited.
	 * @param bbox          Bounding box of the current search area.
	 * @return number of nodes visited during the search
	 */
	private int searchRecursive(BintreeNode node, Seminar targetSeminar, double radius, int depth, BoundingBox bbox) {
		if (node.isInternal()) {
			return handleInternalNode((InternalNode) node, targetSeminar, radius, depth, bbox);
		} else if (node.isLeaf()) {
			return handleLeafNode((LeafNode) node, targetSeminar, radius);
		}
		return 1;
	}

	/**
	 * Processes an internal node during the search operation.
	 *
	 * @param currentNode   The current internal node being traversed.
	 * @param targetSeminar The target seminar object being searched for.
	 * @param searchRadius  The radius within which to search for the target.
	 * @param depthLevel    The current depth level in the binary tree.
	 * @param currentBox    The bounding box representing the search area at the
	 *                      current depth.
	 * @return The number of nodes examined during the traversal of this internal
	 *         node.
	 */
	private int handleInternalNode(InternalNode currentNode, Seminar targetSeminar, double searchRadius, int depthLevel,
			BoundingBox currentBox) {
		double centerX = (currentBox.getxMin() + currentBox.getxMax()) / 2.0;
		double centerY = (currentBox.getyMin() + currentBox.getyMax()) / 2.0;

		if (depthLevel % 2 == 0) {
			return handleXAxisSplit(currentNode, targetSeminar, searchRadius, depthLevel, currentBox, centerX);
		} else {
			return handleYAxisSplit(currentNode, targetSeminar, searchRadius, depthLevel, currentBox, centerY);
		}
	}

	/**
	 * Handles the traversal of an internal node when the split is along the X-axis
	 * during the search operation.
	 *
	 * @param internalNode  The internal node currently being processed.
	 * @param targetSeminar The seminar being searched for.
	 * @param searchRadius  The radius within which to search for the target
	 *                      seminar.
	 * @param treeDepth     The current depth of the node in the tree.
	 * @param boundingBox   The bounding box defining the current search area.
	 * @param midX          The midpoint along the X-axis where the split occurs.
	 * @return The number of nodes processed during this traversal along the X-axis.
	 */
	private int handleXAxisSplit(InternalNode internalNode, Seminar targetSeminar, double searchRadius, int treeDepth,
			BoundingBox boundingBox, double midX) {
		if (targetSeminar.x() - searchRadius < midX && targetSeminar.x() + searchRadius >= midX) {
			return searchBothChildren(internalNode, targetSeminar, searchRadius, treeDepth, boundingBox, midX, true);
		} else if (targetSeminar.x() - searchRadius < midX) {
			return searchLeftChild(internalNode, targetSeminar, searchRadius, treeDepth, boundingBox, midX, true);
		} else if (targetSeminar.x() + searchRadius >= midX) {
			return searchRightChild(internalNode, targetSeminar, searchRadius, treeDepth, boundingBox, midX, true);
		}
		return 1;
	}

	/**
	 * Handles the traversal of an internal node when the split is along the Y-axis
	 * during the search operation.
	 *
	 * @param internalNode  The internal node currently being processed.
	 * @param targetSeminar The seminar being searched for.
	 * @param searchRadius  The radius within which to search for the target
	 *                      seminar.
	 * @param treeDepth     The current depth of the node in the tree.
	 * @param boundingBox   The bounding box defining the current search area.
	 * @param midY          The midpoint along the Y-axis where the split occurs.
	 * @return The number of nodes processed during this traversal along the Y-axis.
	 */
	private int handleYAxisSplit(InternalNode internalNode, Seminar targetSeminar, double searchRadius, int treeDepth,
			BoundingBox boundingBox, double midY) {
		if (targetSeminar.y() - searchRadius < midY && targetSeminar.y() + searchRadius >= midY) {
			return searchBothChildren(internalNode, targetSeminar, searchRadius, treeDepth, boundingBox, midY, false);
		} else if (targetSeminar.y() - searchRadius < midY) {
			return searchLeftChild(internalNode, targetSeminar, searchRadius, treeDepth, boundingBox, midY, false);
		} else if (targetSeminar.y() + searchRadius >= midY) {
			return searchRightChild(internalNode, targetSeminar, searchRadius, treeDepth, boundingBox, midY, false);
		}
		return 1;
	}

	/**
	 * Searches both left and right children of the internal node during the search.
	 *
	 * @param internalNode  The internal node currently being processed.
	 * @param targetSeminar The seminar being searched for.
	 * @param searchRadius  The radius within which to search for the target
	 *                      seminar.
	 * @param treeDepth     The current depth of the node in the tree.
	 * @param boundingBox   The bounding box defining the current search area.
	 * @param mid           The midpoint (either along X-axis or Y-axis) where the
	 *                      split occurs.
	 * @param isXAxis       Boolean indicating if the split is along the X-axis.
	 * @return The total number of nodes processed when both child nodes are
	 *         traversed.
	 */
	private int searchBothChildren(InternalNode internalNode, Seminar targetSeminar, double searchRadius, int treeDepth,
			BoundingBox boundingBox, double mid, boolean isXAxis) {
		BoundingBox leftBox;
		BoundingBox rightBox;

		if (isXAxis) {
			leftBox = new BoundingBox(boundingBox.getxMin(), boundingBox.getyMin(), mid, boundingBox.getyMax());
			rightBox = new BoundingBox(mid, boundingBox.getyMin(), boundingBox.getxMax(), boundingBox.getyMax());
		} else {
			leftBox = new BoundingBox(boundingBox.getxMin(), boundingBox.getyMin(), boundingBox.getxMax(), mid);
			rightBox = new BoundingBox(boundingBox.getxMin(), mid, boundingBox.getxMax(), boundingBox.getyMax());
		}
		return searchRecursive(internalNode.left(), targetSeminar, searchRadius, treeDepth + 1, leftBox)
				+ searchRecursive(internalNode.right(), targetSeminar, searchRadius, treeDepth + 1, rightBox) + 1;
	}

	/**
	 * Searches only the left child of the internal node during the search
	 * operation.
	 *
	 * @param internalNode  The internal node currently being processed.
	 * @param targetSeminar The seminar being searched for.
	 * @param searchRadius  The radius within which to search for the target
	 *                      seminar.
	 * @param treeDepth     The current depth of the node in the tree.
	 * @param boundingBox   The bounding box defining the current search area.
	 * @param mid           The midpoint (either along X-axis or Y-axis) where the
	 *                      split occurs.
	 * @param isXAxis       Boolean indicating if the split is along the X-axis.
	 * @return The number of nodes processed when only the left child is traversed.
	 */
	private int searchLeftChild(InternalNode internalNode, Seminar targetSeminar, double searchRadius, int treeDepth,
			BoundingBox boundingBox, double mid, boolean isXAxis) {
		BoundingBox leftBoundingBox = isXAxis
				? new BoundingBox(boundingBox.getxMin(), boundingBox.getyMin(), mid, boundingBox.getyMax())
				: new BoundingBox(boundingBox.getxMin(), boundingBox.getyMin(), boundingBox.getxMax(), mid);
		return searchRecursive(internalNode.left(), targetSeminar, searchRadius, treeDepth + 1, leftBoundingBox) + 1;
	}

	/**
	 * Searches only the right child of the internal node during the search
	 * operation.
	 *
	 * @param internalNode  The internal node currently being processed.
	 * @param targetSeminar The seminar being searched for.
	 * @param searchRadius  The radius within which to search for the target
	 *                      seminar.
	 * @param treeDepth     The current depth of the node in the tree.
	 * @param boundingBox   The bounding box defining the current search area.
	 * @param mid           The midpoint (either along X-axis or Y-axis) where the
	 *                      split occurs.
	 * @param isXAxis       Boolean indicating if the split is along the X-axis.
	 * @return The number of nodes processed when only the right child is traversed.
	 */
	private int searchRightChild(InternalNode internalNode, Seminar targetSeminar, double searchRadius, int treeDepth,
			BoundingBox boundingBox, double mid, boolean isXAxis) {
		BoundingBox rightBoundingBox = isXAxis
				? new BoundingBox(mid, boundingBox.getyMin(), boundingBox.getxMax(), boundingBox.getyMax())
				: new BoundingBox(boundingBox.getxMin(), mid, boundingBox.getxMax(), boundingBox.getyMax());
		return searchRecursive(internalNode.right(), targetSeminar, searchRadius, treeDepth + 1, rightBoundingBox) + 1;
	}

	/**
	 * Processes the leaf node during the search and checks for seminars within the
	 * search radius.
	 *
	 * @param leafNode      The leaf node currently being processed.
	 * @param targetSeminar The seminar being searched for.
	 * @param searchRadius  The radius within which to search for the target
	 *                      seminar.
	 * @return The number of nodes processed at this leaf node.
	 */
	private int handleLeafNode(LeafNode leafNode, Seminar targetSeminar, double radius) {
		for (Seminar semObjecet : leafNode.getSeminars()) {
			double searchDistance = Math.sqrt(
					Math.pow(targetSeminar.x() - semObjecet.x(), 2) + Math.pow(targetSeminar.y() - semObjecet.y(), 2));
			if (searchDistance <= radius) {
				System.out.println("Found a record with key value " + semObjecet.id() + " at " + semObjecet.x() + ", "
						+ semObjecet.y());
			}
		}
		return 1;
	}

	/**
	 * Returns the root of the Bintree.
	 * 
	 * @return Root node of the Bintree.
	 */
	public BintreeNode getRoot() {
		return root;
	}
}
