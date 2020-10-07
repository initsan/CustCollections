package customhashmap;

public class CustomHashMap<K, V> {
    private int size;
    private int capacity = 10;
    private EntryCustomHashMap<K, V>[] entries = new EntryCustomHashMap[capacity];

    public CustomHashMap() {
    }

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
    }

    private int hashing(int hashCode) {
        int location = hashCode % capacity;
        System.out.println("Location: " + location);
        return location;
    }

    public int size() {
        return entries.length;
    }

    public V get(K key) {
        validateKey(key);

        if (!containsKey(key)) return null;
        return lookupExistingEntry(key).getValue();
    }

    public void put(K key, V value) {
        validateKey(key);

        int bucket = hashing(key.hashCode());
        if (containsKey(key)) {
            lookupExistingEntry(key).setValue(value);
        } else {
            EntryCustomHashMap<K, V> entry = entries[bucket];
            if (entry == null) {
                entries[bucket] = new EntryCustomHashMap<>(key, value);
            } else {
                while (entry.getNext() != null) {
                    entry = entry.getNext();
                }
                entry.setNext(new EntryCustomHashMap<>(key, value));
            }
            size++;
        }
    }

    private EntryCustomHashMap<K, V> lookupExistingEntry(K key) {
        int bucket = hashing(key.hashCode());
        EntryCustomHashMap<K, V> entry = entries[bucket];
        while (!key.equals(entry.getKey())) {
            entry = entry.getNext();
        }
        return entry;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new NullPointerException("Key can be null");
        }
    }

    public boolean containsKey(K key) {
        validateKey(key);

        int bucket = hashing(key.hashCode());
        if (entries[bucket] == null) return false;

        EntryCustomHashMap<K, V> entry = entries[bucket];
        while (entry != null) {
            if (key.equals(entry.getKey())) return true;
            entry = entry.getNext();
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for (EntryCustomHashMap entry : entries) {
            if (entry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

}
