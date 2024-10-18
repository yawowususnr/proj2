// -------------------------------------------------------------------------
/**
 *  Key value pair where value is seminar object
 *  @param <K>
 *  @param <V>
 * 
 *  @author Yaw Owusu Snr
 *  @version Oct 2, 2024
 */
public class KeyValuePair<K extends Comparable<K>, V>
    implements Comparable<KeyValuePair<K, V>>
{

    private K key;
    private V value;

    // ----------------------------------------------------------
    /**
     * Create a new KeyValuePair object.
     * @param newKey
     * @param newValue
     */
    public KeyValuePair(K newKey, V newValue)
    {
        this.key = newKey;
        this.value = newValue;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return key of KV pair
     */
    public K getKey()
    {
        return this.key;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return value of KV pair
     */
    public V getValue()
    {
        return this.value;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param o
     *            object to compare to
     * @return -1, 0 or 1 depending on which object is larger in magnitde
     */
    public int compareTo(K o)
    {
        return key.compareTo(o);
    }


    @Override
    public int compareTo(KeyValuePair<K, V> o)
    {
        return key.compareTo(o.getKey());
    }

}
