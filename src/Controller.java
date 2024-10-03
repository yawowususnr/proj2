import javax.xml.crypto.dsig.keyinfo.KeyValue;

public class Controller {

	private BST<KeyValuePair<Integer, Seminar>> idBST;

	private BST<KeyValuePair<Integer, Seminar>> costBST;

	private BST<KeyValuePair<String, Seminar>> dateBST;

	private BST<KeyValuePair<String, Seminar>> keywordsBST;

	public Controller() {
		this.idBST = new BST<KeyValuePair<Integer, Seminar>>();
		this.costBST = new BST<KeyValuePair<Integer, Seminar>>();
		this.dateBST = new BST<KeyValuePair<String, Seminar>>();
		this.keywordsBST = new BST<KeyValuePair<String, Seminar>>();
	}

	public void insert(int id, String title, String date, int length, short x, short y, int cost, String[] keywords,
			String desc) {

		Seminar seminarNode = new Seminar(id, title, date, length, x, y, cost, keywords, desc);

		this.idBST.insert(new KeyValuePair<Integer, Seminar>(id, seminarNode));
		this.costBST.insert(new KeyValuePair<Integer, Seminar>(cost, seminarNode));
		this.dateBST.insert(new KeyValuePair<String, Seminar>(date, seminarNode));

	}

	public void remove(int id) {
		this.idBST.remove(id);
		
	}
	
	public void searchID(int id) {
		
	}
	
	public void searchCost(int cost) {
		
	}
	
	public void searchDate(String firstDate, String secDate) {
		
	}
	public void print() {

	}
}
