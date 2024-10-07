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

	public boolean checkIfValid(int x, int y) {
		return (x >= 0 && x < this.size && y >= 0 && y < this.size);
	}

	public void insert(int id, String title, String date, int length, short x, short y, int cost, String[] keywords,
			int keywords_length, String desc) {

		KeyValuePair<Integer, Seminar> foundNode = this.idBST.find(id);
		
		if (foundNode != null) {
			System.out.println("There already exists a record with ID " + id);
			return;
		}

		if (this.checkIfValid(x, y) == false) {
			System.out.println("Bad insertion of coordinates");
			return;
		}

		Seminar seminarNode = new Seminar(id, title, date, length, x, y, cost, keywords, desc);

		this.idBST.insert(new KeyValuePair<Integer, Seminar>(id, seminarNode));
		this.costBST.insert(new KeyValuePair<Integer, Seminar>(cost, seminarNode));
		this.dateBST.insert(new KeyValuePair<String, Seminar>(date, seminarNode));

		for (int i = 0; i < keywords_length; i++) {
			this.keywordsBST.insert(new KeyValuePair<String, Seminar>(keywords[i], seminarNode));
			;
		}
		this.bintree.insert(seminarNode);

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
			
			System.out.println("Record with ID " + seminarObject.id() + " successfully deleted");

		} else {
			System.out.println("Failed");
		}

	}

	public void searchId(int id) {
		KeyValuePair<Integer, Seminar> foundNode = this.idBST.find(id);
		if (foundNode != null) {
			System.out.println("Found record with id" + id);
		} else {
			System.out.println("Search failed");
		}

	}

	public void searchCost(int firstCost, int secCost) {
		this.costBST.traverse(firstCost, secCost);

	}

	public void searchDate(String firstDate, String secDate) {
		this.dateBST.traverse(firstDate, secDate);
	}

	public void searchkeyword(String keyword) {
		this.keywordsBST.traverse(keyword, keyword);
	}

	public void print(String s) {
		if (s.equals("ID")) {
			System.out.println("ID TREE");
			this.idBST.print();
		} else if (s.equals("cost")) {
			System.out.println("ID TREE");
			this.costBST.print();
		} else if (s.equals("date")) {
			System.out.println("ID TREE");
			this.dateBST.print();
		} else if (s.equals("keyword")) {
			System.out.println("ID TREE");
			this.keywordBST.print();
		} else {
			System.out.println("Location Tree:");

		}

	}
}
