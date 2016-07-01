package epam.arraylist;

import java.util.*;

/**
 * Created by Сергей on 01.07.2016.
 */

public class MyArrayList<T> implements List<T>{
    private static final int INITIAL_CAPACITY = 10;
    private Object[] array;
    private int size;
    private int modCount;

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        this.array = new Object[initialCapacity];
        this.size = 0;
        this.modCount = 0;
    }

    public MyArrayList() {
        this.array = new Object[INITIAL_CAPACITY];
        this.size = 0;
        this.modCount = 0;
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        checkCapacity();
        array[size] = element;
        size++;
        modCount++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        checkCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[size] = element;
        size++;
        modCount++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T)array[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T changed = (T)array[index];
        array[index] = element;
        modCount++;
        return (T)changed;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null/* || !(o instanceof T*/) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if(array[i].equals(o)) {
                remove(i);
            }
        }
        size--;
        modCount++;
        return true;
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T removed = (T)array[index];
        array[index] = null;
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        modCount++;
        return removed;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (array[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(array[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            int index = 0;
            int expectedModCount = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return (T) array[index++];
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public T previous() {
                return (T) array[--index];
            }

            @Override
            public int nextIndex() {
                return index + 1;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(index--);
                expectedModCount++;
            }

            @Override
            public void set(T o) {
                MyArrayList.this.set(index, o);
                expectedModCount++;
            }

            @Override
            public void add(T o) {
                MyArrayList.this.add(index, o);
                expectedModCount++;
            }

            private void checkModCount() {
                if(expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };

    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    public void checkCapacity() {
        if (size == array.length - 1) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            this.array = newArray;
        }
    }
}

