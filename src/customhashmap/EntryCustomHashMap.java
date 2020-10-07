package customhashmap;

import java.util.Objects;

public class EntryCustomHashMap<K, V> {
    private K key;
    private V value;
    private EntryCustomHashMap<K, V> next;

    public EntryCustomHashMap(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public EntryCustomHashMap<K, V> getNext() {
        return next;
    }

    public void setNext(EntryCustomHashMap<K, V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getKey() == null && o == null) return true;
        if (getKey() == null) return false;
        if (o == null || getClass() != o.getClass()) return false;
        EntryCustomHashMap<?, ?> that = (EntryCustomHashMap<?, ?>) o;
        return getKey().equals(that.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
    }
}
