import javax.xml.crypto.dsig.keyinfo.KeyValue;

public class Controller {

    private BST<Integer, Seminar> idBST;

    private BST<Integer, Seminar> costBST;

    private BST<String, Seminar> dateBST;

    private BST<String, Seminar> keywordsBST;

    private Bintree bintree;

    private int size;

    public Controller(int size) {
        this.idBST = new BST<Integer, Seminar>();
        this.costBST = new BST<Integer, Seminar>();
        this.dateBST = new BST<String, Seminar>();
        this.keywordsBST = new BST<String, Seminar>();
        this.size = size;
        this.bintree = new Bintree(this.size);
    }
    
    public int getSize() {
        return this.size;
    }
    public BST<Integer, Seminar> getcostBSTree(){
        return this.costBST;
    }
    public BST<String, Seminar> getdateBSTree(){
        return this.dateBST;
    }
    public BST<String, Seminar> getkeyWordsBSTree(){
        return this.keywordsBST;
    }
    
    public BST<Integer, Seminar> getidBSTree(){
        return this.idBST;
    }


    public boolean checkIfValid(int x, int y) {
        return (x >= 0 && x < this.size && y >= 0 && y < this.size);
    }


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
        String desc) {

        KeyValuePair<Integer, Seminar> foundNode = this.idBST.find(id);

        if (foundNode != null) {
            System.out.println(
                "Insert FAILED - There is already a record with ID " + id);
            return ;
        }

        if (this.checkIfValid(x, y) == false) {
            System.out.println("Insert FAILED - Bad x, y coordinates: " + String
                .valueOf(x) + ", " + String.valueOf(y));
            return;
        }

        Seminar seminarNode = new Seminar(id, title, date, length, x, y, cost,
            keywords, desc);

        this.idBST.insert(new KeyValuePair<Integer, Seminar>(id, seminarNode));
        this.costBST.insert(new KeyValuePair<Integer, Seminar>(cost,
            seminarNode));
        this.dateBST.insert(new KeyValuePair<String, Seminar>(date,
            seminarNode));

        for (int i = 0; i < keywords_length; i++) {
            this.keywordsBST.insert(new KeyValuePair<String, Seminar>(
                keywords[i], seminarNode));

        }
        this.bintree.insert(seminarNode);

        System.out.println("Successfully inserted record with ID " + id);
        System.out.println(seminarNode.toString());

    }


    public void delete(int id) {
        KeyValuePair<Integer, Seminar> removedNode = this.idBST.remove(id);

        if (removedNode != null) {
            Seminar seminarObject = removedNode.getValue();

            this.costBST.remove(seminarObject.cost());

            this.dateBST.remove(seminarObject.date());

            for (String word : seminarObject.keywords()) {
                this.keywordsBST.remove(word);
            }

            this.bintree.remove(seminarObject);

            System.out.println("Record with ID " + seminarObject.id()
                + " successfully deleted from the database");

        }
        else {
            System.out.println("Search FAILED -- There is no record with ID "
                + id);
        }

    }


    public void searchId(int id) {
        KeyValuePair<Integer, Seminar> foundNode = this.idBST.find(id);
        if (foundNode != null) {
            System.out.println("Found record with ID " + id + ":");
            Seminar foundNodeSeminar = foundNode.getValue();
            System.out.println(foundNodeSeminar.toString());

        }
        else {
            System.out.println("Search FAILED -- There is no record with ID "
                + id);
        }

    }


    public void searchCost(int firstCost, int secCost) {
        System.out.println("Seminars with costs in range " + String.valueOf(
            firstCost) + " " + "to " + String.valueOf(secCost) + ":");
        int count = this.costBST.traverse(firstCost, secCost);
        System.out.println(String.valueOf(count)
            + " nodes visited in this search");
    }


    public void searchDate(String firstDate, String secDate) {
        System.out.println("Seminars with dates in range " + firstDate + " to "
            + secDate + ":");
        int count = this.dateBST.traverse(firstDate, secDate);
        System.out.println(String.valueOf(count)
            + " nodes visited in this search");
    }


    public void searchkeyword(String keyword) {
        System.out.println("Seminars matching keyword " + keyword + ":");
        int count = this.keywordsBST.traverse(keyword, keyword);
    }


    public void searchLocation(int x, int y, int radius) {
        System.out.println("Seminars within " + radius + " units of " + x + ", "
            + y + ":");
        bintree.search(new Seminar(0, "", "", 0, (short)x, (short)y, 0,
            new String[] {}, ""), radius);
    }


    public void print(String s) {
        if (s.equals("ID")) {
            System.out.println("ID Tree:");
            this.idBST.print();
        }
        else if (s.equals("cost")) {
            System.out.println("Cost Tree:");
            this.costBST.print();
        }
        else if (s.equals("date")) {
            System.out.println("Date Tree:");
            this.dateBST.print();
        }
        else if (s.equals("keyword")) {
            System.out.println("Keyword Tree:");
            this.keywordsBST.print();
        }
        else if (s.equals("location")) {
            System.out.println("Location Tree:");
            this.bintree.print(); 

        }

    }
}
