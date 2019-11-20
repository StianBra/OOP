import java.util.ArrayList;
import java.util.Collections;

public class Liste<T extends Comparable<T>> {
    private final ArrayList<T> elements;

    public Liste() {
        this.elements = new ArrayList<>();
    }

    /**
     *
     * @param value Value to be added to the list, primitive.
     */
    public void add(T value) {
        int index = Collections.binarySearch(this.elements, value);

        System.out.println("Funnet index "+index);

        if (index < 0) {
            index += 1;
            index *= -1;
        }

        System.out.println("Legges på index "+index);

        this.elements.add(index, value);
    }

    public void print() {
        for (T l:elements) {
            System.out.println(l);
        }
    }
}