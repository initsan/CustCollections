import java.util.Arrays;

public class CustomArrayList<T> {
    private int startSize = 0;
    private int size;
    private Object[] array = new Object[16];
    private int lastElement = 0;

    public CustomArrayList() {
    }

    public CustomArrayList(int startSize) {
        this.startSize = startSize;
    }

    public void add(T item) {
        if (lastElement == array.length - 1) {
            this.resize((int) (array.length*1.5));
        }
        array[lastElement++] = item;
    }

    public void resize (int newLenth) {
        array = Arrays.copyOf(array, newLenth);
    }

    public T get(int index) {
        return (T) array[index];
    }

    public int size(){
        return lastElement;
    }

    public void remove(int index) {
        if (array.length - index >= 0) System.arraycopy(array, index + 1, array, index, array.length - index);
        array[lastElement] = null;
        lastElement--;
        if (lastElement < (int) (array.length*1.5) && (int) (array.length*1.5) > startSize) {
            this.resize((int) (array.length*1.5));
        }
    }

}
