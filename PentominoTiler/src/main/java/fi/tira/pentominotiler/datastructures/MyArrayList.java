package fi.tira.pentominotiler.datastructures;

import java.util.AbstractList;

/**
 *
 * @author juha
 * @param <T>
 */
public class MyArrayList<T> extends AbstractList<T> {

    private static final int DEFAULT_SIZE = 4;

    private int nextIndex;
    private T[] array;

    public MyArrayList() {
        this(DEFAULT_SIZE);
    }

    public MyArrayList(int initialSize) {
        this.nextIndex = 0;
        this.array = (T[]) new Object[initialSize];
    }

    @Override
    public boolean add(T element) {
        if (nextIndex >= array.length) {
            extendArray();
        }
        array[nextIndex++] = element;
        return true;
    }

    private void extendArray() {
        if (array.length >= Integer.MAX_VALUE) {
            throw new IllegalStateException("Collection is full.");
        }
        T[] newArray = (T[]) new Object[array.length * 2];
        for (int i = 0; i < nextIndex; i++) {
            newArray[i] = array[i];
        }
        this.array = newArray;
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public int size() {
        return nextIndex;
    }

}
