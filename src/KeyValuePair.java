// -------------------------------------------------------------------------
/**
 * Key value pair where value is seminar object
 * 
 * @param <K>
 *            key
 * @param <V>
 *            value
 * 
 * @author Yaw Agyemang	
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class KeyValuePair<K extends Comparable<K>, V>
    implements Comparable<KeyValuePair<K, V>> {

    private K key;
    private V value;

    // ----------------------------------------------------------
    /**
     * Create a new KeyValuePair object.
     * 
     * @param newKey
     *            a new key
     * @param newValue
     *            a new value
     */
    public KeyValuePair(K newKey, V newValue) {
        this.key = newKey;
        this.value = newValue;
    }


    // ----------------------------------------------------------
    /**
     * gets the key
     * 
     * @return key of KV pair
     */
    public K getKey() {
        return this.key;
    }


    // ----------------------------------------------------------
    /**
     * gets value
     * 
     * @return value of KV pair
     */
    public V getValue() {
        return this.value;
    }


    // ----------------------------------------------------------
    /**
     * compares keys
     * 
     * @param o
     *            object to compare to
     * @return -1, 0 or 1 depending on which object is larger in magnitde
     */
    public int compareTo(K o) {
        return key.compareTo(o);
    }


    @Override
    /**
     * compare key value pair
     * 
     * @return return -1, 0 m1 dependign on the object
     */
    public int compareTo(KeyValuePair<K, V> o) {
        return key.compareTo(o.getKey());
    }

}
