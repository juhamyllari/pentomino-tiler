package fi.tira.pentominotiler.datastructures;

import java.util.Collection;

/**
 * A simple set data structure implemented using a hash table.
 * Removal of elements is not required by Pentomino Tiler and is therefore not
 * implemented. For this reason, MyArrayList objects, rather than linked lists,
 * are used as buckets.
 * 
 * @author juha
 * @param <E> element type
 */
public class MyHashSet<E> {

    private static final int DEFAULT_INITIAL_ARRAY_SIZE = 11;
    private static final double DEFAULT_LOAD_FACTOR = 3.0;

    private MyArrayList[] buckets;
    private int size;
    private double loadFactor;

    /**
     * Construct a MyHashSet with default parameters.
     */
    public MyHashSet() {
        this(DEFAULT_INITIAL_ARRAY_SIZE, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Construct a MyHashSet specifying its initial size and its load factor.
     * The internal array is doubled in size when
     * (number of elements) / (array size)
     * exceeds the load factor.
     * 
     * @param initialSize
     * @param loadFactor
     */
    public MyHashSet(int initialSize, double loadFactor) {
        this.buckets = new MyArrayList[initialSize];
        this.size = 0;
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new MyArrayList();
        }
        this.loadFactor = loadFactor;
    }

    /**
     * Add an element to the set.
     * 
     * @param element
     * @return true unless element was already present
     */
    public boolean add(E element) {
        if (size / (double) buckets.length > loadFactor) {
            rehash();
        }

        MyArrayList<E> bucket = buckets[hash(element, buckets.length)];
        if (bucket.contains(element)) {
            return false;
        } else {
            bucket.add(element);
            size++;
            return true;
        }
    }

    /**
     * Add all elements in a Collection.
     * 
     * @param c
     */
    public void addAll(Collection c) {
        c.forEach((element) -> add((E) element));
    }

    /**
     * Check whether the set contains the specified element.
     * 
     * @param element
     * @return true if element is present
     */
    public boolean contains(E element) {
        MyArrayList<E> bucket = buckets[hash(element, buckets.length)];
        return bucket.contains(element);
    }

    private void rehash() {
        MyArrayList<E>[] newBuckets = new MyArrayList[2 * buckets.length];
        for (int i = 0; i < newBuckets.length; i++) {
            newBuckets[i] = new MyArrayList<>();
        }

        for (MyArrayList<E> bucket : buckets) {
            for (E element : bucket) {
                newBuckets[hash(element, newBuckets.length)].add(element);
            }
        }
        this.buckets = newBuckets;
    }

    /**
     * Get the cardinality of the set.
     * 
     * @return number of elements
     */
    public int size() {
        return size;
    }

    private int hash(E element, int arrayLength) {
        return Math.abs(element.hashCode()) % arrayLength;
    }

    /**
     * Get the size of the largest bucket.
     * If the return value is much larger than the load factor, the hashing scheme
     * may be performing poorly (i.e. there are too many collisions).
     * 
     * @return max(bucket size)
     */
    public int maxBucketSize() {
        int max = 0;
        for (MyArrayList bucket : buckets) {
            if (bucket.size() > max) {
                max = bucket.size();
            }
        }
        return max;
    }
}
