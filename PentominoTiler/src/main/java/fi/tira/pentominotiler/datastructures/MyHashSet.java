package fi.tira.pentominotiler.datastructures;

import java.util.Collection;

/**
 * A simple set implemented using a hash table.
 *
 * @author juha
 * @param <E> element type
 */
public class MyHashSet<E> {

    private static final int INITIAL_ARRAY_SIZE = 11;
    private static final double THRESHOLD = .75;

    private MyArrayList[] buckets;
    private int size;

    public MyHashSet() {
        this(INITIAL_ARRAY_SIZE);
    }

    public MyHashSet(int initialSize) {
        this.buckets = new MyArrayList[initialSize];
        this.size = 0;
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new MyArrayList();
        }
    }

    public boolean add(E element) {
        if (size / (double) buckets.length > THRESHOLD) {
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

    public void addAll(Collection c) {
        c.forEach((element) -> add((E) element));
    }

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

    public int size() {
        return size;
    }

    private int hash(E element, int arrayLength) {
        return Math.abs(element.hashCode()) % arrayLength;
    }

}
