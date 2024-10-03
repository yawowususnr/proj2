import javax.xml.crypto.dsig.keyinfo.KeyValue;

public class Controller {

	private BST<Integer, Seminar> idBST;

	private BST<Integer, Seminar> costBST;

	private BST<String, Seminar> dateBST;

	private BST<String, Seminar> keywordsBST;

	public Controller() {
		this.idBST = new BST<Integer, Seminar>();
		this.costBST = new BST<Integer, Seminar>();
		this.dateBST = new BST<String, Seminar>();
		this.keywordsBST = new BST<String, Seminar>();
	}

	public void insert(int id, String title, String date, int length, short x, short y, int cost, String[] keywords, int keywords_length,
			String desc) {

		Seminar seminarNode = new Seminar(id, title, date, length, x, y, cost, keywords, desc);

		this.idBST.insert(new KeyValuePair<Integer, Seminar>(id, seminarNode));
		this.costBST.insert(new KeyValuePair<Integer, Seminar>(cost, seminarNode));
		this.dateBST.insert(new KeyValuePair<String, Seminar>(date, seminarNode));
		
//		for (int i = 0; i < keywords_length; i ++) {
//			
//		}

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

		} else {
			System.out.println("Failed");
		}

	}

	public void searchID(int id) {
		KeyValuePair<Integer, Seminar> KVPair = this.idBST.find(id);

		if (KVPair != null) {
			System.out.println("Found");
		} else {
			System.out.println("Not Found");
		}
	}

	public void searchCost(int firstCost, int secCost) {
	    KeyValuePair<Integer, Seminar> KVPair = this.costBST.

	}

	public void searchDate(String firstDate, String secDate) {

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
