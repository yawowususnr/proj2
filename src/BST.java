
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
     * @param e
     */
    public void insert(KeyValuePair<K, V> newKVP)
    {
        root = inserthelp(root, newKVP);
        nodecount++;
    }

    private BSTNode<KeyValuePair<K, V>> inserthelp(BSTNode<KeyValuePair<K, V>> root, KeyValuePair<K, V> newKVP)
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


    private BSTNode deleteMax(BSTNode rt)
    {
        if (rt.right() == null)
        {
            return rt.getLeft();
        }
        rt.setRight(deleteMax(rt.right()));
    }


    private void printhelp(BSTNode<E> rt)
    {
        if (rt == null)
        {
            return;
        }
        printhelp(rt.left());
        printVisit(rt.value());
        printhelp(rt.right());
    }


    private BSTNode removehelp(BSTNode rt, Comparable key)
    {
        if (rt == null)
        {
            return null;
        }

        if (rt.value().compareTo(key) > 0)
        {
            rt.setLeft(removehelp(rt.left(), key));
        }
        else if (rt.value().compareTo(key) < 0)
        {
            rt.setRight(removehelp(rt.right(), key));
        }
        else
        { // Found the node
            if (rt.left() == null)
            {
                return rt.right();
            }
            else if (rt.right() == null)
            {
                return rt.left();
            }
            else
            { // Two children
                BSTNode temp = getmax(rt.left());
                rt.setValue(temp.value());
                rt.setLeft(deletemax(rt.left()));
            }
        }

        return rt;
    }


    // Remove a record from the tree
    // key: The key value of record to remove
    // Returns the record removed, null if there is none.
    public E remove(E key)
    {
        E temp = findhelp(root, key); // First find it
        if (temp != null)
        {
            root = removehelp(root, key); // Now remove it
            nodecount--;
        }
        return temp;
    }





    // Return the number of records in the dictionary
    public int size()
    {
        return nodecount;
    }
}
