package fi.tira.pentominotiler.datastructures;

import java.util.Collection;
import java.util.Iterator;

/**
 * A rudimentary list data structure implemented using an array. MyArrayList
 * only supports adding elements, querying for size, getting elements by their
 * index and iterating over elements. Element removal is not required by
 * Pentomino Tiler and is therefore not implemented.
 *
 * @author juha
 * @param <E> element type
 */
public class MyArrayList<E> implements Collection<E> {

    private static final int DEFAULT_SIZE = 4;

    private int nextIndex;
    private E[] array;

    /**
     * Construct a MyArrayList of default (initial) size.
     */
    public MyArrayList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Construct a MyArrayList of the specified (initial) size.
     *
     * @param initialSize
     */
    public MyArrayList(int initialSize) {
        this.nextIndex = 0;
        this.array = (E[]) new Object[initialSize];
    }

    @Override
    public boolean isEmpty() {
        return nextIndex == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (E element : this) {
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Add an element to the end of the list.
     *
     * @param element
     * @return true
     */
    @Override
    public boolean add(E element) {
        if (nextIndex >= array.length) {
            extendArray();
        }
        array[nextIndex++] = element;
        return true;
    }

    /**
     * Get the element at the specified index.
     *
     * @param index
     * @return elements[index]
     */
    public E get(int index) {
        return array[index];
    }

    /**
     * Get the length of the list.
     *
     * @return length(list)
     */
    @Override
    public int size() {
        return nextIndex;
    }

    /**
     * Replace the internal array with a new array twice as long.
     * Existing elements are copied into the new array.
     */
    private void extendArray() {
        if (array.length >= Integer.MAX_VALUE) {
            throw new IllegalStateException("Collection is full.");
        }
        E[] newArray = (E[]) new Object[array.length * 2];
        for (int i = 0; i < nextIndex; i++) {
            newArray[i] = array[i];
        }
        this.array = newArray;
    }

    private class MyIterator implements Iterator {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < nextIndex;
        }

        @Override
        public Object next() {
            return array[index++];
        }

    }

}
