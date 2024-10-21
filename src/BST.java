/**
 * A generic implementation of a Binary Search Tree (BST).
 * 
 * @param <K> The type of keys maintained by this BST, which must be comparable.
 * @param <V> The type of values maintained by this BST.
 * 
 * @author Yaw Agyemang
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
class BST<K extends Comparable<K>, V> {
	private BSTNode<KeyValuePair<K, V>> root; // Root of the BST
	private int totalNodes; // Number of nodes in the BST

	/**
	 * Constructs an empty Binary Search Tree.
	 */
	public BST() {
		root = null;
		totalNodes = 0;
	}

	/**
	 * Clears the tree, resetting it to be empty.
	 */
	public void clear() {
		root = null;
		totalNodes = 0;
	}

	/**
	 * Returns the total number of records (nodes) present in the BST.
	 * 
	 * @return The count of records in the Binary Search Tree.
	 */
	public int size() {
		return totalNodes;
	}

	/**
	 * Searches for a KeyValuePair with the specified key in the BST.
	 * 
	 * @param key The key value to search for.
	 * @return The KeyValuePair associated with the key, or null if none exists.
	 */
	public KeyValuePair<K, V> find(K key) {
		return findhelp(root, key);
	}

	/**
	 * Helper method to locate a KeyValuePair with the specified key starting from a
	 * given node.
	 * 
	 * @param rt  The current node to check.
	 * @param key The key value to search for.
	 * @return The KeyValuePair associated with the key, or null if none exists.
	 */
	private KeyValuePair<K, V> findhelp(BSTNode<KeyValuePair<K, V>> rt, K key) {
		if (rt == null) {
			return null;
		}
		if (rt.getElement().compareTo(key) > 0) {
			return findhelp(rt.getLeft(), key);
		} else if (rt.getElement().compareTo(key) == 0) {
			return rt.getElement();
		} else {
			return findhelp(rt.getRight(), key);
		}
	}

	/**
	 * Inserts a new KeyValuePair into the BST.
	 * 
	 * @param newKVP The KeyValuePair to insert.
	 */
	public void insert(KeyValuePair<K, V> newKVP) {
		root = inserthelp(root, newKVP);
		totalNodes++;
	}

	/**
	 * Helper method to insert a KeyValuePair starting from a given node.
	 * 
	 * @param rt     The current node where the insertion occurs.
	 * @param newKVP The KeyValuePair to insert.
	 * @return The new root of the subtree after insertion.
	 */
	private BSTNode<KeyValuePair<K, V>> inserthelp(BSTNode<KeyValuePair<K, V>> rt, KeyValuePair<K, V> newKVP) {
		if (rt == null) {
			return new BSTNode<KeyValuePair<K, V>>(newKVP);
		}
		if (rt.getElement().compareTo(newKVP) >= 0) {
			rt.setLeft(inserthelp(rt.getLeft(), newKVP));
		} else {
			rt.setRight(inserthelp(rt.getRight(), newKVP));
		}
		return rt;
	}

	/**
	 * Removes a KeyValuePair from the BST using the specified key.
	 * 
	 * @param key The key value of the KeyValuePair to remove.
	 * @return The KeyValuePair removed, or null if none exists.
	 */
	public KeyValuePair<K, V> remove(K key) {
		KeyValuePair<K, V> temp = findhelp(root, key); // First find it
		if (temp != null) {
			root = removehelp(root, key); // Now remove it
			totalNodes--;
		}
		return temp;
	}

	/**
	 * Removes a specified KeyValuePair from the BST.
	 * 
	 * @param pair The KeyValuePair to remove.
	 */
	public void remove(KeyValuePair<K, V> pair) {
		root = removehelp(root, pair);
		totalNodes--;
	}

	/**
	 * Helper method to remove a KeyValuePair starting from a given node.
	 * 
	 * @param rt   The current node where removal occurs.
	 * @param pair The KeyValuePair to remove.
	 * @return The new root of the subtree after removal.
	 */
	public BSTNode<KeyValuePair<K, V>> removehelp(BSTNode<KeyValuePair<K, V>> rt, KeyValuePair<K, V> pair) {
		if (rt == null) {
			return null;
		}

		if (rt.getElement().compareTo(pair.getKey()) > 0) {
			rt.setLeft(removehelp(rt.getLeft(), pair));
		} else if (rt.getElement().compareTo(pair.getKey()) < 0) {
			rt.setRight(removehelp(rt.getRight(), pair));
		} else if (((Seminar) pair.getValue()).id() != ((Seminar) rt.getElement().getValue()).id()) {
			rt.setLeft(removehelp(rt.getLeft(), pair));
		} else { // Found the node to remove
			if (rt.getLeft() == null) {
				return rt.getRight();
			} else if (rt.getRight() == null) {
				return rt.getLeft();
			} else { // Two children
				BSTNode<KeyValuePair<K, V>> temp = getMax(rt.getLeft());
				rt.setElement(temp.getElement());
				rt.setLeft(deleteMax(rt.getLeft()));
			}
		}
		return rt;
	}

	/**
	 * Helper method to remove a record using its key starting from a given node.
	 * 
	 * @param rt  The current node.
	 * @param key The key value of the record to remove.
	 * @return The new root of the subtree.
	 */
	private BSTNode<KeyValuePair<K, V>> removehelp(BSTNode<KeyValuePair<K, V>> rt, K key) {
		if (rt == null) {
			return null;
		}

		if (rt.getElement().compareTo(key) > 0) {
			rt.setLeft(removehelp(rt.getLeft(), key));
		} else if (rt.getElement().compareTo(key) < 0) {
			rt.setRight(removehelp(rt.getRight(), key));
		} else { // Found the node
			if (rt.getLeft() == null) {
				return rt.getRight();
			} else if (rt.getRight() == null) {
				return rt.getLeft();
			} else { // Two children
				BSTNode<KeyValuePair<K, V>> temp = getMax(rt.getLeft());
				rt.setElement(temp.getElement());
				rt.setLeft(deleteMax(rt.getLeft()));
			}
		}

		return rt;
	}

	/**
	 * Deletes the maximum node from a subtree.
	 * 
	 * @param rt The root of the subtree.
	 * @return The new root of the subtree.
	 */
	private BSTNode<KeyValuePair<K, V>> deleteMax(BSTNode<KeyValuePair<K, V>> rt) {
		if (rt.getRight() == null) {
			return rt.getLeft();
		}
		rt.setRight(deleteMax(rt.getRight()));
		return rt;
	}

	/**
	 * Retrieves the maximum node from a subtree.
	 * 
	 * @param rt The root of the subtree.
	 * @return The maximum node.
	 */
	private BSTNode<KeyValuePair<K, V>> getMax(BSTNode<KeyValuePair<K, V>> rt) {
		if (rt.getRight() == null) {
			return rt;
		}
		return getMax(rt.getRight());
	}

	/**
	 * Traverses the entire tree and prints all records that fall within the
	 * specified range.
	 * 
	 * @param lo The lower bound of the range.
	 * @param hi The upper bound of the range.
	 * @return The total number of records printed within the specified range.
	 */
	public int traverse(K lo, K hi) {
		return traverseHelp(root, lo, hi);
	}

	/**
	 * Helper method that traverses the tree to count the number of records within a
	 * specified range.
	 * 
	 * @param rt The current node being examined.
	 * @param lo The lower bound of the range.
	 * @param hi The upper bound of the range.
	 * @return The total number of records found within the specified range.
	 */
	private int traverseHelp(BSTNode<KeyValuePair<K, V>> rt, K lo, K hi) {
		if (rt == null) {
			return 1;
		}

		if (rt.getElement().compareTo(lo) < 0) {
			return traverseHelp(rt.getRight(), lo, hi) + 1;
		} else if (rt.getElement().compareTo(hi) > 0) {
			return traverseHelp(rt.getLeft(), lo, hi) + 1;
		} else {
			int traversed = traverseHelp(rt.getLeft(), lo, hi);
			System.out.println(rt.getElement().getValue().toString());
			if (rt.getElement().compareTo(hi) != 0) {
				traversed += traverseHelp(rt.getRight(), lo, hi);
			}
			return traversed + 1;
		}
	}

	/**
	 * Prints the entire structure of the BST. If the tree is empty, a message will
	 * indicate that.
	 */
	public void print() {
		if (size() == 0) {
			System.out.println("This tree is empty");
		} else {
			int heightOfTree = getHeight(root);
			printhelp(root, 0, heightOfTree);
			System.out.println("Number of records: " + size());
		}
	}

	/**
	 * Computes the height of the tree starting from a specified node.
	 * 
	 * @param node The current node from which to calculate height.
	 * @return The height of the subtree rooted at the specified node.
	 */
	private int getHeight(BSTNode<KeyValuePair<K, V>> node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = getHeight(node.getLeft());
		int rightHeight = getHeight(node.getRight());
		return 1 + Math.max(leftHeight, rightHeight); // Height is max of left or right subtree + 1
	}

	/**
	 * Helper method that recursively prints the structure of the BST.
	 * 
	 * @param rt     The current node being printed.
	 * @param level  The current depth level in the tree.
	 * @param height The overall height of the tree.
	 */
	private void printhelp(BSTNode<KeyValuePair<K, V>> rt, int level, int height) {
		String space = "";
		int distance = (height - level);

		for (int i = 0; i < distance; i++) {
			space += "    ";
		}
		if (rt == null) {
			// Print null nodes with the corresponding indentation.
			System.out.println(space + "(" + "null" + ")");
			space += " ";
			return;
		}
		printhelp(rt.getLeft(), level + 1, height);
		printVisit(space, rt.getElement());
		printhelp(rt.getRight(), level + 1, height);
	}

	/**
	 * Prints a KeyValuePair node in a structured format.
	 * 
	 * @param space The indentation used for printing.
	 * @param node  The KeyValuePair node to be printed.
	 */
	private void printVisit(String space, KeyValuePair<K, V> node) {
		K key = node.getKey();

		System.out.println(space + '\\');
		System.out.println(space + "(" + String.valueOf(key) + ")");
		System.out.println(space + '/');
	}
}