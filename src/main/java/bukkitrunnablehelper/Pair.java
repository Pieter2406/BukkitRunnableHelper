package bukkitrunnablehelper;

/**
 * Created by Pieter on 21/06/2015.
 */
public class Pair<K,V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public String toString(){
        return key.toString() + " " + value.toString();
    }
}
