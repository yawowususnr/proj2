
/**
 * 
 */

import java.lang.Object;

class BST<K extends Comparable<K>, V>
{
    private BSTNode<KeyValuePair<K, V>> root; // Root of the BST
    private int nodecount; // Number of nodes in the BST

    // constructor
    public BST()
    {
        root = null;
        nodecount = 0;
    }


    // Reinitialize tree
    public void clear()
    {
        root = null;
        nodecount = 0;
    }

    // -------------------------------------------------------------------


    // Return the record with key value k, null if none exists
    // key: The key value to find
    public KeyValuePair<K, V> find(K key)
    {
        return findhelp(root, key);
    }


    private KeyValuePair<K, V> findhelp(BSTNode<KeyValuePair<K, V>> root, K key)
    {
        if (root == null)
        {
            return null;
        }
        if (root.getElement().compareTo(key) > 0)
        {
            return findhelp(root.getLeft(), key);
        }
        else if (root.getElement().compareTo(key) == 0)
        {
            return root.getElement();
        }
        else
        {
            return findhelp(root.getRight(), key);
        }

    }


    // Insert a record into the tree.
    // Records can be anything, but they must be Comparable
    // e: The record to insert.
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param newKVP
     */
    public void insert(KeyValuePair<K, V> newKVP)
    {
        root = inserthelp(root, newKVP);
        nodecount++;
    }


    private
        BSTNode<KeyValuePair<K, V>>
        inserthelp(BSTNode<KeyValuePair<K, V>> root, KeyValuePair<K, V> newKVP)
    {
        if (root == null)
        {
            return new BSTNode<KeyValuePair<K, V>>(newKVP);
        }
        if (root.getElement().compareTo(newKVP) >= 0)
        {
            root.setLeft(inserthelp(root.getLeft(), newKVP));
        }
        else
        {
            root.setRight(inserthelp(root.getRight(), newKVP));
        }
        return root;
    }

    // ----------------------------------------------------------


    /*
     * Remove a record from the tree key: The key value of record to remove
     * Returns the record removed, null if there is none.
     */
    public KeyValuePair<K, V> remove(K key)
    {
        KeyValuePair<K, V> temp = findhelp(root, key); // First find it
        if (temp != null)
        {
            root = removehelp(root, key); // Now remove it
            nodecount--;
        }
        return temp;
    }


    private BSTNode<KeyValuePair<K, V>> deleteMax(
        BSTNode<KeyValuePair<K, V>> root)
    {
        if (root.getRight() == null)
        {
            return root.getLeft();
        }
        root.setRight(deleteMax(root.getRight()));
        return root;
    }


    private BSTNode<KeyValuePair<K, V>> getMax(BSTNode<KeyValuePair<K, V>> root)
    {
        if (root.getRight() == null)
        {
            return root;
        }
        return getMax(root.getRight());
    }

    // ----------------------------------------------------------


    private
        BSTNode<KeyValuePair<K, V>>
        removehelp(BSTNode<KeyValuePair<K, V>> rt, K key)
    {
        if (rt == null)
        {
            return null;
        }

        if (rt.getElement().compareTo(key) > 0)
        {
            rt.setLeft(removehelp(rt.getLeft(), key));
        }
        else if (rt.getElement().compareTo(key) < 0)
        {
            rt.setRight(removehelp(rt.getRight(), key));
        }
        else
        { // Found the node
            if (rt.getLeft() == null)
            {
                return rt.getRight();
            }
            else if (rt.getRight() == null)
            {
                return rt.getLeft();
            }
            else
            { // Two children
                BSTNode<KeyValuePair<K, V>> temp = getMax(rt.getLeft());
                rt.setElement(temp.getElement());
                rt.setLeft(deleteMax(rt.getLeft()));
            }
        }

        return rt;
    }


    private int traverseHelp(BSTNode<KeyValuePair<K, V>> root, K lo, K hi)
    {

        if (root == null)
        {
            return 1;
        }

        if (root.getElement().compareTo(lo) < 0)
        {
            return traverseHelp(root.getRight(), lo, hi) + 1;
        }
        else if (root.getElement().compareTo(hi) > 0)
        {
            return traverseHelp(root.getLeft(), lo, hi) + 1;
        }
        else
        {
            int traversed = traverseHelp(root.getLeft(), lo, hi);
            System.out.println(root.getElement().getValue().toString());
            if (root.getElement().compareTo(hi) != 0)
            {
                traversed += traverseHelp(root.getRight(), lo, hi);
            }
            return traversed + 1;
        }
    }


    public int traverse(K lo, K hi)
    {
        return traverseHelp(root, lo, hi);
    }


    private int getHeight(BSTNode<KeyValuePair<K, V>> node)
    {
        if (node == null)
        {
            return 0;
        }
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return 1 + Math.max(leftHeight, rightHeight); // Height is max of left
                                                      // or right subtree + 1
    }


    private
        void
        printhelp(BSTNode<KeyValuePair<K, V>> rt, int level, int height)
    {
        String space = "";
        int distance = (height - level);

        for (int i = 0; i < distance; i++)
        {
            space += "    ";
        }
        if (rt == null)
        {
            // Print null nodes with the corresponding indentation.
            System.out.println(space + "(" + "null" + ")");
            space += " ";

            return;
        }
        printhelp(rt.getLeft(), level + 1, height);

        printVisit(space, rt.getElement());

        printhelp(rt.getRight(), level + 1, height);

    }


    private void printVisit(String space, KeyValuePair<K, V> node)
    {

        K key = node.getKey();

        System.out.println(space + '\\');
        System.out.println(space + "(" + String.valueOf(key) + ")");
        System.out.println(space + '/');

    }


    public void print()
    {
        if (size() == 0)
        {
            System.out.println("This tree is empty");
        }
        else
        {
            int heightOfTree = getHeight(root);

            printhelp(root, 0, heightOfTree);
            System.out.println("Number of records: " + size());
        }
    }


    // Return the number of records in the dictionary
    public int size()
    {
        return nodecount;
    }
}
