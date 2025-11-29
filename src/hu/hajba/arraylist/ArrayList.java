package hu.hajba.arraylist;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

public class ArrayList<E> extends AbstractList<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        elements = new Object[initialCapacity];
        size = 0;
    }

    public ArrayList(Collection<? extends E> c) {
        elements = c.toArray();
        size = c.size();
    }

    @Override
    public E get(int index) {
        checkIndexRange(index);
        return (E) elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E set(int index, E element) {
        checkIndexRange(index);
        E old = (E) elements[index];
        elements[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();
        if (index != size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = element;
        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        checkIndexRange(index);
        E old = (E) elements[index];
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        elements[size - 1] = null;
        size--;
        modCount++;
        return old;
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, (elements.length + 1) * 3 / 2);
        }
    }
}
