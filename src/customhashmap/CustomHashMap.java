package customhashmap;

public class CustomHashMap<K, V> {
    private static final int THRESHOLD = 20;
    private static final double MAX_LOAD_FACTOR = 0.75d;
    private static final double MIN_LOAD_FACTOR = 0.25d;
    private int size;
    private int noOfBuckets = 10;
    private EntryCustomHashMap<K, V>[] entries;

    public CustomHashMap() {
        entries = new EntryCustomHashMap[noOfBuckets];
    }

    public CustomHashMap(int bucket) {
        this.noOfBuckets = bucket;
        entries = new EntryCustomHashMap[noOfBuckets];
    }

    private int hashing(int hashCode) {
        int location = hashCode % noOfBuckets;
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

        resize();
    }

    private void resize() {
        if (noOfBuckets < 10 || needResize() == 0) return;

        EntryCustomHashMap[] oldEntries = entries;
        if (needResize() > 0) {
            noOfBuckets *= 2;
        } else {
            noOfBuckets /= 2;
        }
        entries = new EntryCustomHashMap[noOfBuckets];

        for (EntryCustomHashMap<K, V> entry: oldEntries) {
            EntryCustomHashMap<K, V> t = entry;
            while (t != null) {
                put(t.getKey(), t.getValue());
                t = t.getNext();
            }
        }
    }

    private int needResize() {
        double loadFactor = ((double) size) / noOfBuckets / THRESHOLD;
        int tmp = (loadFactor < MIN_LOAD_FACTOR) ? -1 : 0;
        return (loadFactor > MAX_LOAD_FACTOR) ? 1 : tmp;
    }

    public void remove(K key) {
        validateKey(key);
        if (containsKey(key)) {
            int bucket = hashing(key.hashCode());
            EntryCustomHashMap<K, V> entry = entries[bucket];
            if (key.equals(entry.getKey())) {
                entries[bucket] = entry.getNext();
            } else {
                while (!key.equals(entry.getNext().getKey())) {
                    entry = entry.getNext();
                }
                entry.setNext(entry.getNext().getNext());
            }
            size--;
            resize();
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
