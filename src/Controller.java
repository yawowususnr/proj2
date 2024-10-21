import javax.xml.crypto.dsig.keyinfo.KeyValue;

/**
 * Controller class to handle inputs and manage seminar data structures.
 * Coordinates operations across multiple BSTs and a Bintree for efficient
 * seminar record management and retrieval.
 * 
 * @author Yaw Agyemang
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class Controller {
    private BST<Integer, Seminar> idBST;
    private BST<Integer, Seminar> costBST;
    private BST<String, Seminar> dateBST;
    private BST<String, Seminar> keywordsBST;
    private Bintree bintree;
    private int size;

    /**
     * Create a new Controller object.
     * 
     * @param size the size of controller grid
     */
    public Controller(int size) {
        initializeDataStructures(size);
    }

    /**
     * Initializes all data structures used by the controller.
     * 
     * @param size the size of the location grid
     */
    private void initializeDataStructures(int size) {
        this.idBST = new BST<Integer, Seminar>();
        this.costBST = new BST<Integer, Seminar>();
        this.dateBST = new BST<String, Seminar>();
        this.keywordsBST = new BST<String, Seminar>();
        this.size = size;
        this.bintree = new Bintree(this.size);
    }

    /**
     * Retrieves the current size of the record collection.
     *
     * @return the number of records in the collection.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Retrieves the binary search tree (BST) used for storing seminars by cost.
     *
     * @return the BST of seminars organized by cost.
     */
    public BST<Integer, Seminar> getcostBSTree() {
        return this.costBST;
    }

    /**
     * Retrieves the binary search tree (BST) used for storing seminars by date.
     *
     * @return the BST of seminars organized by date.
     */
    public BST<String, Seminar> getdateBSTree() {
        return this.dateBST;
    }

    /**
     * Retrieves the binary search tree (BST) used for storing seminars by keywords.
     *
     * @return the BST of seminars organized by keywords.
     */
    public BST<String, Seminar> getkeyWordsBSTree() {
        return this.keywordsBST;
    }

    /**
     * Retrieves the binary search tree (BST) used for storing seminars by ID.
     *
     * @return the BST of seminars organized by ID.
     */
    public BST<Integer, Seminar> getidBSTree() {
        return this.idBST;
    }


    /**
     * Validates if given coordinates are within grid boundaries.
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if coordinates are valid
     */
    public boolean checkIfValid(int x, int y) {
        return (x >= 0 && x < this.size && y >= 0 && y < this.size);
    }

    /**
     * Inserts a new record into the system with the specified details.
     *
     * @param id           the unique identifier for the record (must be greater than 0).
     * @param title        the title of the event (must not be null or empty).
     * @param date         the date of the event in the format "YYYY-MM-DD" (must be valid).
     * @param length       the duration of the event in minutes (must be greater than 0).
     * @param x            the x-coordinate for the event location (must be within valid bounds).
     * @param y            the y-coordinate for the event location (must be within valid bounds).
     * @param cost         the cost of attending the event (must be greater than or equal to 0).
     * @param keywords     an array of keywords associated with the event (must not be null).
     * @param keywordsLength the number of keywords in the array (must match the array length).
     * @param desc         a description of the event (must not be null or empty).
     *
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
        int keywordsLength,
        String desc) {

        if (!validateInsertParameters(id, x, y)) {
            return;
        }

        Seminar seminarNode = new Seminar(id, title, date, length, x, y, 
            cost, keywords, desc);
        insertIntoDataStructures(seminarNode, keywords, keywordsLength);
        printInsertionSuccess(id, seminarNode);
    }

    /**
     * Validates parameters for insertion.
     * 
     * @return true if parameters are valid
     */
    private boolean validateInsertParameters(int id, short x, short y) {
        if (isDuplicateId(id)) {
            System.out.println(
                "Insert FAILED - There is already a record with ID " + id);
            return false;
        }

        if (!checkIfValid(x, y)) {
            System.out.println("Insert FAILED - Bad x, y coordinates: " + 
                String.valueOf(x) + ", " + String.valueOf(y));
            return false;
        }

        return true;
    }

    /**
     * Checks if a seminar with given ID already exists.
     */
    private boolean isDuplicateId(int id) {
        return this.idBST.find(id) != null;
    }

    /**
     * Inserts seminar into all data structures.
     */
    private void insertIntoDataStructures(
        Seminar seminar, 
        String[] keywords,
        int keywordsLength) {
        
        insertIntoBSTs(seminar);
        insertKeywords(seminar, keywords, keywordsLength);
        this.bintree.insert(seminar);
    }

    /**
     * Inserts seminar into main BSTs.
     */
    private void insertIntoBSTs(Seminar seminar) {
        this.idBST.insert(new KVPair<Integer, Seminar>(
            seminar.id(), seminar));
        this.costBST.insert(new KVPair<Integer, Seminar>(
            seminar.cost(), seminar));
        this.dateBST.insert(new KVPair<String, Seminar>(
            seminar.date(), seminar));
    }

    /**
     * Inserts keywords for a seminar.
     */
    private void insertKeywords(
        Seminar seminar, 
        String[] keywords,
        int keywordsLength) {
        
        for (int i = 0; i < keywordsLength; i++) {
            this.keywordsBST.insert(new KVPair<String, Seminar>(
                keywords[i], seminar));
        }
    }

    /**
     * Prints success message after insertion.
     */
    private void printInsertionSuccess(int id, Seminar seminar) {
        System.out.println("Successfully inserted record with ID " + id);
        System.out.println(seminar.toString());
    }

    /**
     * Delete a seminar record by ID.
     */
    public void delete(int id) {
        KVPair<Integer, Seminar> removedNode = removeFromIdBST(id);
        
        if (removedNode == null) {
            printDeletionFailure(id);
            return;
        }
        
        Seminar seminarObject = removedNode.getValue();
        removeFromAllStructures(seminarObject);
        printDeletionSuccess(seminarObject.id());
    }

    private KVPair<Integer, Seminar> removeFromIdBST(int id) {
        return this.idBST.remove(id);
    }

    private void removeFromAllStructures(Seminar seminarObject) {
        removeFromCostBST(seminarObject);
        removeFromDateBST(seminarObject);
        removeFromKeywordsBST(seminarObject);
        removeFromBintree(seminarObject);
    }

    private void removeFromCostBST(Seminar seminarObject) {
        KVPair<Integer, Seminar> costPair = 
            new KVPair<>(seminarObject.cost(), seminarObject);
        this.costBST.remove(costPair);
    }

    private void removeFromDateBST(Seminar seminarObject) {
        KVPair<String, Seminar> datePair = 
            new KVPair<>(seminarObject.date(), seminarObject);
        this.dateBST.remove(datePair);
    }

    private void removeFromKeywordsBST(Seminar seminarObject) {
        for (String keyword : seminarObject.keywords()) {
            KVPair<String, Seminar> keywordPair = 
                new KVPair<>(keyword, seminarObject);
            this.keywordsBST.remove(keywordPair);
        }
    }

    private void removeFromBintree(Seminar seminarObject) {
        this.bintree.remove(seminarObject);
    }

    private void printDeletionSuccess(int id) {
        System.out.println(
            "Record with ID " + id + 
            " successfully deleted from the database");
    }

    private void printDeletionFailure(int id) {
        System.out.println(
            "Delete FAILED -- There is no record with ID " + id);
    }

    /**
     * Search for a Seminar by ID.
     */
    public void searchId(int id) {
        KVPair<Integer, Seminar> foundNode = this.idBST.find(id);
        printSearchIdResult(foundNode, id);
    }

    /**
     * Prints the result of an ID search.
     */
    private void printSearchIdResult(
        KVPair<Integer, Seminar> foundNode, 
        int id) {
        
        if (foundNode != null) {
            System.out.println("Found record with ID " + id + ":");
            System.out.println(foundNode.getValue().toString());
        }
        else {
            System.out.println(
                "Search FAILED -- There is no record with ID " + id);
        }
    }

    /**
     * Search for Seminars within a cost range.
     */
    public void searchCost(int firstCost, int secCost) {
        printCostRangeHeader(firstCost, secCost);
        int visitedNodes = this.costBST.traverse(firstCost, secCost);
        printVisitedNodesCount(visitedNodes);
    }

    private void printCostRangeHeader(int firstCost, int secCost) {
        System.out.println("Seminars with costs in range " + 
            String.valueOf(firstCost) + " to " + 
            String.valueOf(secCost) + ":");
    }

    private void printVisitedNodesCount(int count) {
        System.out.println(String.valueOf(count) + 
            " nodes visited in this search");
    }

    /**
     * Search for Seminars within a date range.
     */
    public void searchDate(String firstDate, String secDate) {
        printDateRangeHeader(firstDate, secDate);
        int visitedNodes = this.dateBST.traverse(firstDate, secDate);
        printVisitedNodesCount(visitedNodes);
    }

    private void printDateRangeHeader(String firstDate, String secDate) {
        System.out.println("Seminars with dates in range " + 
            firstDate + " to " + secDate + ":");
    }

    /**
     * Search for Seminars by keyword.
     */
    public void searchkeyword(String keyword) {
        System.out.println("Seminars matching keyword " + keyword + ":");
        this.keywordsBST.traverse(keyword, keyword);
    }

    /**
     * Search for Seminars within a radius of coordinates.
     */
    public void searchLocation(int x, int y, int rad) {
        printLocationSearchHeader(x, y, rad);
        Seminar searchPoint = createSearchPoint(x, y);
        bintree.search(searchPoint, rad);
    }

    private void printLocationSearchHeader(int x, int y, int rad) {
        System.out.println("Seminars within " + rad + 
            " units of " + x + ", " + y + ":");
    }

    private Seminar createSearchPoint(int x, int y) {
        return new Seminar(0, "", "", 0, (short)x, (short)y, 0,
            new String[] {}, "");
    }

    /**
     * Print specified tree structure.
     */
    public void print(String s) {
        switch (s) {
            case "ID":
                printTree("ID Tree:", this.idBST);
                break;
            case "cost":
                printTree("Cost Tree:", this.costBST);
                break;
            case "date":
                printTree("Date Tree:", this.dateBST);
                break;
            case "keyword":
                printTree("Keyword Tree:", this.keywordsBST);
                break;
            case "location":
                printLocationTree();
                break;
            default:
                System.out.println("Invalid tree type.");
                break;
        }
    }

    /**
     * Helper method to print any BST.
     */
    private void printTree(String header, BST<?, ?> tree) {
        System.out.println(header);
        tree.print();
    }

    /**
     * Prints the location tree (Bintree).
     */
    private void printLocationTree() {
        System.out.println("Location Tree:");
        if (idBST.size() == 0) {
            System.out.println("E");
        }
        else {
            this.bintree.print();
        }
    }
}