import javax.xml.crypto.dsig.keyinfo.KeyValue;

// -------------------------------------------------------------------------
/**
 * Controller class to handle inputs
 * 
 * @author Yaw Owusu Snr & Chris Nicoue-Beglah
 * @version Oct 18, 2024
 */
public class Controller
{

    // Binary Search Trees (BSTs) to store Seminar objects based on different
    // attributes
    private BST<Integer, Seminar> idBST; // BST for storing Seminars by ID
    private BST<Integer, Seminar> costBST; // BST for storing Seminars by cost
    private BST<String, Seminar> dateBST; // BST for storing Seminars by date
    private BST<String, Seminar> keywordsBST; // BST for storing Seminars by
                                              // keywords

    private Bintree bintree; // Bintree to handle location-based searches (by x,
                             // y coordinates)
    private int size; // The size of the grid for seminar locations

    /**
     * Create a new Controller object.
     * 
     * @param size
     */
    public Controller(int size)
    {
        this.idBST = new BST<Integer, Seminar>();
        this.costBST = new BST<Integer, Seminar>();
        this.dateBST = new BST<String, Seminar>();
        this.keywordsBST = new BST<String, Seminar>();
        this.size = size; // Set the size of the location grid
        this.bintree = new Bintree(this.size); // Initialize Bintree with grid
                                               // size
    }


    // ----------------------------------------------------------
    /**
     * Returns the size of the location grid
     * 
     * @return size of grid
     */
    public int getSize()
    {
        return this.size;
    }


    /**
     * Getter methods to retrieve the cost BST for different attributes
     * 
     * @return costbst
     */
    public BST<Integer, Seminar> getcostBSTree()
    {
        return this.costBST;
    }


    /**
     * Getter methods to retrieve the date BST for different attributes
     * 
     * @return costbst
     */
    public BST<String, Seminar> getdateBSTree()
    {
        return this.dateBST;
    }


    /**
     * Getter methods to retrieve the keyword BST for different attributes
     * 
     * @return keyword BST
     */
    public BST<String, Seminar> getkeyWordsBSTree()
    {
        return this.keywordsBST;
    }


    /**
     * Getter methods to retrieve the id BST for different attributes
     * 
     * @return id bst
     */
    public BST<Integer, Seminar> getidBSTree()
    {
        return this.idBST;
    }


    /**
     * Check if the given x, y coordinates are within the grid boundaries Place
     * a description of your method here.
     * 
     * @param x
     *            dimension
     * @param y
     *            dimension
     * @return true if valid
     */
    public boolean checkIfValid(int x, int y)
    {
        return (x >= 0 && x < this.size && y >= 0 && y < this.size);
    }


    /**
     * Insert a Seminar record into the system. It checks for duplicate ID and
     * valid x, y coordinates. If valid, it inserts the Seminar into all
     * relevant BSTs and the Bintree.
     * 
     * @param id
     *            Seminar ID
     * @param title
     *            Seminar title
     * @param date
     *            Seminar date
     * @param length
     *            Seminar length
     * @param x
     *            Seminar x-coordinate
     * @param y
     *            Seminar y-coordinate
     * @param cost
     *            Seminar cost
     * @param keywords
     *            Array of keywords related to the Seminar
     * @param keywords_length
     *            Number of keywords
     * @param desc
     *            Seminar description
     */
    public void insert(
        int id,
        String title,
        String date,
        int length,
        short x,
        short y,
        int cost,
        String[] keywords,
        int keywords_length,
        String desc)
    {

        // Check if a Seminar with the same ID already exists
        KeyValuePair<Integer, Seminar> foundNode = this.idBST.find(id);

        if (foundNode != null)
        {
            System.out.println(
                "Insert FAILED - There is already a record with ID " + id);
            return;
        }

        // Validate the x, y coordinates
        if (this.checkIfValid(x, y) == false)
        {
            System.out.println(
                "Insert FAILED - Bad x, y coordinates: " + String.valueOf(x)
                    + ", " + String.valueOf(y));
            return;
        }

        // Create a new Seminar object with the given data
        Seminar seminarNode =
            new Seminar(id, title, date, length, x, y, cost, keywords, desc);

        // Insert the Seminar into all the BSTs based on different attributes
        // (id, cost, date, keywords)
        this.idBST.insert(new KeyValuePair<Integer, Seminar>(id, seminarNode));
        this.costBST
            .insert(new KeyValuePair<Integer, Seminar>(cost, seminarNode));
        this.dateBST
            .insert(new KeyValuePair<String, Seminar>(date, seminarNode));

        // Insert each keyword associated with the Seminar into the keywords BST
        for (int i = 0; i < keywords_length; i++)
        {
            this.keywordsBST.insert(
                new KeyValuePair<String, Seminar>(keywords[i], seminarNode));
        }

        // Insert the Seminar into the Bintree for location-based search
        this.bintree.insert(seminarNode);

        // Confirm successful insertion
        System.out.println("Successfully inserted record with ID " + id);
        System.out.println(seminarNode.toString());
    }


    /**
     * Deletes a Seminar record by ID. Removes the corresponding entry from all
     * BSTs and the Bintree.
     * 
     * @param id
     *            The ID of the Seminar to be deleted
     */
    public void delete(int id)
    {
        // Remove the Seminar from the idBST based on ID
        KeyValuePair<Integer, Seminar> removedNode = this.idBST.remove(id);

        if (removedNode != null)
        {
            Seminar seminarObject = removedNode.getValue();

            // Remove the Seminar from the other BSTs (cost, date, keywords)
            this.costBST.remove(
                new KeyValuePair<Integer, Seminar>(
                    seminarObject.cost(),
                    seminarObject));
            this.dateBST.remove(
                new KeyValuePair<String, Seminar>(
                    seminarObject.date(),
                    seminarObject));

            for (String word : seminarObject.keywords())
            {
                this.keywordsBST.remove(
                    new KeyValuePair<String, Seminar>(word, seminarObject));
            }

            // Remove the Seminar from the Bintree
            this.bintree.remove(seminarObject);

            // Confirm successful deletion
            System.out.println(
                "Record with ID " + seminarObject.id()
                    + " successfully deleted from the database");

        }
        else
        {
            System.out
                .println("Delete FAILED -- There is no record with ID " + id);
        }
    }


    /**
     * Search for a Seminar by ID and display its details if found.
     * 
     * @param id
     *            The ID to search for
     */
    public void searchId(int id)
    {
        // Search the idBST for a Seminar with the given ID
        KeyValuePair<Integer, Seminar> foundNode = this.idBST.find(id);
        if (foundNode != null)
        {
            System.out.println("Found record with ID " + id + ":");
            Seminar foundNodeSeminar = foundNode.getValue();
            System.out.println(foundNodeSeminar.toString());
        }
        else
        {
            System.out
                .println("Search FAILED -- There is no record with ID " + id);
        }
    }


    /**
     * Search for Seminars within a given cost range.
     * 
     * @param firstCost
     *            Lower bound of the cost range
     * @param secCost
     *            Upper bound of the cost range
     */
    public void searchCost(int firstCost, int secCost)
    {
        System.out.println(
            "Seminars with costs in range " + String.valueOf(firstCost) + " to "
                + String.valueOf(secCost) + ":");
        int count = this.costBST.traverse(firstCost, secCost); // Traverse
                                                               // costBST for
                                                               // Seminars in
                                                               // cost range
        System.out
            .println(String.valueOf(count) + " nodes visited in this search");
    }


    /**
     * Search for Seminars within a given date range.
     * 
     * @param firstDate
     *            Starting date range
     * @param secDate
     *            Ending date range
     */
    public void searchDate(String firstDate, String secDate)
    {
        System.out.println(
            "Seminars with dates in range " + firstDate + " to " + secDate
                + ":");
        int count = this.dateBST.traverse(firstDate, secDate); // Traverse
                                                               // dateBST for
                                                               // Seminars in
                                                               // date range
        System.out
            .println(String.valueOf(count) + " nodes visited in this search");
    }


    /**
     * Search for Seminars by a specific keyword.
     * 
     * @param keyword
     *            The keyword to search for
     */
    public void searchkeyword(String keyword)
    {
        System.out.println("Seminars matching keyword " + keyword + ":");

    }


    /**
     * Search for Seminars within a given radius of the x, y coordinates.
     * 
     * @param x
     *            X-coordinate
     * @param y
     *            Y-coordinate
     * @param radius
     *            The radius to search within
     */
    public void searchLocation(int x, int y, int radius)
    {
        System.out.println(
            "Seminars within " + radius + " units of " + x + ", " + y + ":");
        bintree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)x,
                (short)y,
                0,
                new String[] {},
                ""),
            radius); // Perform Bintree search based on location
    }

    /**
     * Print the specified BST or Bintree (based on ID, cost, date, keyword, or location).
     * @param s The type of tree to print ("ID", "cost", "date", "keyword", or "location")
     */
    public void print(String s)
    {
        if (s.equals("ID"))
        {
            System.out.println("ID Tree:");
            this.idBST.print();
        }
        else if (s.equals("cost"))
        {
            System.out.println("Cost Tree:");
            this.costBST.print();
        }
        else if (s.equals("date"))
        {
            System.out.println("Date Tree:");
            this.dateBST.print();
        }
        else if (s.equals("keyword"))
        {
            System.out.println("Keyword Tree:");
            this.keywordsBST.print();
        }
        else if (s.equals("location"))
        {
            System.out.println("Location Tree:");
            if (idBST.size() == 0)
                System.out.println("E");
            else
            {
                this.bintree.print();
            }
        }
    }
}
