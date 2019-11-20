import java.util.ArrayList;
import java.util.Collections;

public class Liste<T extends Comparable<T>> {
    private final ArrayList<T> elements;

    public Liste() {
        this.elements = new ArrayList<T>();
    }

    /**
     *
     * @param value Value to be added to the list, primitive.
     */
    public void add(T value) {
        int index = Collections.binarySearch(this.elements, value);

        if (index < 0) {
            index += 1;
            index *= -1;
        }

        this.elements.add(index, value);
    }

    public int size() {
        return this.elements.size();
    }

    public T remove() {
        return this.elements.remove(0);
    }

    public T remove(int i) {
        return this.elements.remove(i);
    }
}