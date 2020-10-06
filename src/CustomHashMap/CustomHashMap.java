package CustomHashMap;

public class CustomHashMap {
    private int size;
    private int capacity;
    private EntryCustomHashMap[] entries = new EntryCustomHashMap[capacity];

    public CustomHashMap() {
    }

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
    }

    private int Hashing(int hashCode) {
        int location = hashCode % capacity;
        System.out.println("Location: " + location);
        return location;
    }

    public int size() {
        return entries.length;
    }

    public Object get(Object key) {
        for (EntryCustomHashMap entry : entries) {
            if (key.equals(null)) {
                throw new NullPointerException("No such key");
            }
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void put(Object key, Object value) {
        if (key == null) {
            throw new NullPointerException("Key can be null");
        }  else {
            EntryCustomHashMap currentEntry = new EntryCustomHashMap();
            currentEntry.setKey(key);
            currentEntry.setValue(value);
            entries[size] = currentEntry;
            size++;
        }
    }

    public boolean isEmpty(){
        if (size == 0) return true;
        return false;
    }

    public boolean containsKey(Object key) {
        if (key == null) {
            throw new NullPointerException("Key can be null");
        }  else {
            for (EntryCustomHashMap entry : entries) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
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
