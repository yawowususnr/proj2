/**
 * Class that holds the world
 */
class BoundingBox
{
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
    public BoundingBox(double xMin, double yMin, double xMax, double yMax)
    {
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
    public double getxMin()
    {
        return xMin;
    }


    /**
     * Gets the minimum y value
     * 
     * @return the minimum y value
     */
    public double getyMin()
    {
        return yMin;
    }


    /**
     * Gets the maximum x value
     * 
     * @return the maximum x value
     */
    public double getxMax()
    {
        return xMax;
    }


    /**
     * Gets the maximum y value
     * 
     * @return the maximum y value
     */
    public double getyMax()
    {
        return yMax;
    }
}




/**
 * Binary tree that holds x and y coordinates
 * 
 * @author Yaw Owusu Snr
 * @version 10/9/23
 */
public class Bintree
{
    private BintreeNode root; // the root node
    private BoundingBox initialBoundingBox; // the starting bounding box

    /**
     * Creates a new Bintree
     * 
     * @param worldSize
     *            the size of the world
     */
    public Bintree(int worldSize)
    {
        initialBoundingBox = new BoundingBox(
            0.0,
            0.0,
            (worldSize - 1),
            (worldSize - 1));
        root = EmptyNode.getInstance();
    }


    /**
     * Gets the bounding box
     * 
     * @return the initial bounding box
     */
    public BoundingBox getWorld()
    {
        return initialBoundingBox;
    }


    /**
     * Insert a new seminar into the Bintree
     * 
     * @param seminar
     *            the seminar to be inserted
     */
    public void insert(Seminar seminar)
    {
        root = root.insert(seminar, 0, initialBoundingBox);
    }


    /**
     * Remove a seminar from the Bintree
     * 
     * @param seminar
     *            the seminar to be removed
     */
    public void remove(Seminar seminar)
    {
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
    public void search(Seminar searchSeminar, double radius)
    {
        int visited =
            searchRecursive(root, searchSeminar, radius, 0, initialBoundingBox);
        System.out.println(visited + " nodes visited in this search");
    }

    public int getHeight(BintreeNode node) {
        if (node.isLeaf()) {
            return 0;
        }
        if (node.isInternal()) {
            InternalNode internal = (InternalNode) node;
            int leftHeight = getHeight(internal.left());
            int rightHeight = getHeight(internal.right());
            return Math.max(leftHeight, rightHeight) + 1; // Height of an internal node
        }
        return 0;
    }
    

    /**a
     * Prints the entire Bintree
     */
    public void print() {
        int treeHeight = getHeight(root);
        // Get the height of the tree
        printRecursive(root, 0, treeHeight); 
    } 

    private void printRecursive(BintreeNode node, int level, int treeHeight) {
        // Calculate the indentation based on the height of the tree minus the level
        String increment = "";
        int distance = (treeHeight - level);
        for (int i = 0; i < distance; i++)
        {
            increment += "    "; 
        }

        if (node.isInternal()) {
            InternalNode internal = (InternalNode) node;
            System.out.println(increment + "(I)");
            
            // Right child first
            printRecursive(internal.right(), level + 1, treeHeight);
            
            // Left child next
            printRecursive(internal.left(), level + 1, treeHeight);
        } 
        else if (node.isLeaf()) {
            LeafNode leaf = (LeafNode) node;
            Seminar[] seminars = leaf.getSeminars();
            StringBuilder string = new StringBuilder("(Leaf with " + leaf.getSize() + " objects:");
            for (int i = 0; i < leaf.getSize(); i++) {
                string.append(" ").append(seminars[i].id());
            }
            System.out.println(increment + string + ')');
        } 
        else {
            System.out.println(increment + "(E)"); 
        }
    } 



    private int searchRecursive(
        BintreeNode node,
        Seminar searchSeminar,
        double radius,
        int level,
        BoundingBox bbox)
    {
        if (node.isInternal())
        {
            InternalNode internalNode = (InternalNode)node;
            double midX = (bbox.getxMin() + bbox.getxMax()) / 2.0;
            double midY = (bbox.getyMin() + bbox.getyMax()) / 2.0;

            if (level % 2 == 0)
            {
                if (searchSeminar.x() - radius < midX
                    && searchSeminar.x() + radius >= midX)
                {
                    return searchRecursive(
                        internalNode.left(),
                        searchSeminar,
                        radius,
                        level + 1,
                        new BoundingBox(
                            bbox.getxMin(),
                            bbox.getyMin(),
                            midX,
                            bbox.getyMax()))
                        + searchRecursive(
                            internalNode.right(),
                            searchSeminar,
                            radius,
                            level + 1,
                            new BoundingBox(
                                midX,
                                bbox.getyMin(),
                                bbox.getxMax(),
                                bbox.getyMax()))
                        + 1;
                }
                else if (searchSeminar.x() - radius < midX)
                {
                    return searchRecursive(
                        internalNode.left(),
                        searchSeminar,
                        radius,
                        level + 1,
                        new BoundingBox(
                            bbox.getxMin(),
                            bbox.getyMin(),
                            midX,
                            bbox.getyMax()))
                        + 1;
                }
                else if (searchSeminar.x() + radius >= midX)
                {
                    return searchRecursive(
                        internalNode.right(),
                        searchSeminar,
                        radius,
                        level + 1,
                        new BoundingBox(
                            midX,
                            bbox.getyMin(),
                            bbox.getxMax(),
                            bbox.getyMax()))
                        + 1;
                }

            }
            else
            {
                if (searchSeminar.y() - radius < midY
                    && searchSeminar.y() + radius >= midY)
                {
                    return searchRecursive(
                        internalNode.left(),
                        searchSeminar,
                        radius,
                        level + 1,
                        new BoundingBox(
                            bbox.getxMin(),
                            bbox.getyMin(),
                            bbox.getxMax(),
                            midY))
                        + searchRecursive(
                            internalNode.right(),
                            searchSeminar,
                            radius,
                            level + 1,
                            new BoundingBox(
                                bbox.getxMin(),
                                midY,
                                bbox.getxMax(),
                                bbox.getyMax()))
                        + 1;
                }
                else if (searchSeminar.y() - radius < midY)
                {
                    return searchRecursive(
                        internalNode.left(),
                        searchSeminar,
                        radius,
                        level + 1,
                        new BoundingBox(
                            bbox.getxMin(),
                            bbox.getyMin(),
                            bbox.getxMax(),
                            midY))
                        + 1;
                }
                else if (searchSeminar.y() + radius >= midY)
                {
                    return searchRecursive(
                        internalNode.right(),
                        searchSeminar,
                        radius,
                        level + 1,
                        new BoundingBox(
                            bbox.getxMin(),
                            midY,
                            bbox.getxMax(),
                            bbox.getyMax()))
                        + 1;
                }

            }
        }
        else if (node.isLeaf())
        {
            LeafNode leafNode = (LeafNode)node;

            for (Seminar seminar : leafNode.getSeminars())
            {
                double distance = Math.sqrt(
                    Math.pow(searchSeminar.x() - seminar.x(), 2)
                        + Math.pow(searchSeminar.y() - seminar.y(), 2));
                if (distance <= radius)
                {
                    System.out.println(
                        "Found a record with key value " + seminar.id() + " at "
                            + seminar.x() + ", " + seminar.y());
                }
            }
        }

        return 1;
    }
    
    /**
     * Place a description of your method here.
     * @return
     */
    public BintreeNode getRoot() {
        return root;
    }
}
