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

	public void insert(int id, String title, String date, int length, short x, short y, int cost, String[] keywords,
			String desc) {

		Seminar seminarNode = new Seminar(id, title, date, length, x, y, cost, keywords, desc);

		this.idBST.insert(new KeyValuePair<Integer, Seminar>(id, seminarNode));
		this.costBST.insert(new KeyValuePair<Integer, Seminar>(cost, seminarNode));
		this.dateBST.insert(new KeyValuePair<String, Seminar>(date, seminarNode));

	}

	public void remove(int id) {
		KeyValuePair<Integer, Seminar> removedNode = this.idBST.remove(id);
		
		if (removedNode != null) {
			Seminar seminarObject = removedNode.getValue();
			
			this.costBST.remove(seminarObject.cost());
			
			this.dateBST.remove(seminarObject.date());
			
		}
		else {
			System.out.println("Failed");
		}
		
		
		
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
