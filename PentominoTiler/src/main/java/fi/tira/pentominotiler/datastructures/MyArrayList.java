package fi.tira.pentominotiler.datastructures;

import java.util.Collection;
import java.util.Iterator;

/**
 * A rudimentary list data structure implemented using an array.
 * MyArrayList only supports adding elements, querying for size, getting elements
 * by their index and iterating over elements. Element removal is not required
 * by Pentomino Tiler and is therefore not implemented.
 * 
 * @author juha
 * @param <T>
 */
public class MyArrayList<T> implements Collection<T> {

    private static final int DEFAULT_SIZE = 4;

    private int nextIndex;
    private T[] array;

    @Override
    public boolean isEmpty() {
        return nextIndex == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T element : this) {
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c) {
            add(t);
        }
        return true;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        this.array = (T[]) new Object[initialSize];
    }

    /**
     * Add an element to the end of the list.
     * 
     * @param element
     * @return true
     */
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

    /**
     * Get the element at the specified index.
     * 
     * @param index
     * @return elements[index]
     */
    public T get(int index) {
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

}
