
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


    // Insert a record into the tree.
    // Records can be anything, but they must be Comparable
    // e: The record to insert.
    public void insert(E e)
    {
        root = inserthelp(root, e);
        nodecount++;
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


    private BSTNode inserthelp(BSTNode rt, Comparable e)
    {
        if (rt == null)
        {
            return new BSTNode(e);
        }
        if (rt.value().compareTo(e) >= 0)
        {
            rt.setLeft(inserthelp(rt.left(), e));
        }
        else
        {
            rt.setRight(inserthelp(rt.right(), e));
        }
        return rt;
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
