public class KeyValuePair<K extends Comparable<K>, V>
    implements Comparable<KeyValuePair<K, V>>
{
    
    private K key;
    private V value;
    
    public KeyValuePair(K newKey, V newValue)
    {
        this.key = newKey;
        this.value = newValue;
    }
    
    public K getKey() {
        return this.key;
    }
    
    public V getValue() {
        return this.value;
    }

    public int compareTo(K o)
    {
        return key.compareTo(o);
    }

    @Override
    public int compareTo(KeyValuePair<K, V> o)
    {
        // TODO Auto-generated method stub
        return key.compareTo(o.getKey());
    }
    
    
}
