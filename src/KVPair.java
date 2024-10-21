// -------------------------------------------------------------------------
/**
 * Key-value pair where the value is a seminar object.
 * 
 * @param <K>
 *            The key type, which must be comparable.
 * @param <V>
 *            The value type.
 * 
 * @author Yaw Agyemang    
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class KVPair<K extends Comparable<K>, V>
    implements Comparable<KVPair<K, V>> {

    private K key;
    private V value;

    // ----------------------------------------------------------
    /**
     * Constructs a new KeyValuePair object with the given key and value.
     * 
     * @param newKey
     *            The key for this pair.
     * @param newValue
     *            The value for this pair.
     */
    public KVPair(K newKey, V newValue) {
        this.key = newKey;
        this.value = newValue;
    }


    // ----------------------------------------------------------
    /**
     * Returns the key of this KeyValuePair.
     * 
     * @return The key.
     */
    public K getKey() {
        return this.key;
    }


    // ----------------------------------------------------------
    /**
     * Returns the value of this KeyValuePair.
     * 
     * @return The value.
     */
    public V getValue() {
        return this.value;
    }


    // ----------------------------------------------------------
    /**
     * Compares this KeyValuePair's key to another key.
     * 
     * @param o
     *            The key to compare against.
     * @return -1, 0, or 1 depending on whether this key is less than,
     *         equal to, or greater than the specified key.
     */
    public int compareTo(K o) {
        return key.compareTo(o);
    }


    @Override
    /**
     * Compares this KeyValuePair to another KeyValuePair by comparing their keys.
     * 
     * @param o
     *            The KeyValuePair to compare against.
     * @return -1, 0, or 1 depending on the result of comparing the keys.
     */
    public int compareTo(KVPair<K, V> o) {
        return key.compareTo(o.getKey());
    }
}
