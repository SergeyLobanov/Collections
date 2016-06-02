
public class MyArrayList<T> implements List {
	private static final int INITIAL_CAPACITY = 10;
	private T[] array;
	private int size;
	private int modCount;
	
	public MyArrayList(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException();
		}
		this.array = new T[initialCapacity];
		this.size = 0;
		this.modCount = 0;
	}
	
	/*
	public MyArrayList(Collection<? extends T> collection) {
		if (collection == null) {
			throw new NullPointerException();
		}
	}
	*/
	
	public MyArrayList() {
		this.array = new T[INITIAL_CAPACITY];
		this.size = 0;
		this.modCount = 0;
	}

	@Override
	public boolean add(T element) {
		if (t == null) {
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
		if (t == null) {
			//return false;
		}
		checkCapacity();
		System.arraycopy(array, index, array, index + 1, size - index);
		array[size] = element;
		size++;
		modCount++;
		return true;
	}
	
	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];		
	}
	
	@Override
	public T set(int index, T element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		T changed = array[i];
		array[i] = element;
		modCount++;
		return changed;		
	}
	
	@Override
	public boolean remove(Object o) {
		if (o == null || !(o instanceof T)) {
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
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		T removed = array[index];
		array[index] = null;
		System.arraycopy(array, index + 1, array, index, size - index);
		size--;
		modCount++;
		return removed;
	}
	
	@Override
	public ListIterator<T> listIterator() {
		return new ListIterator() {
			int index = 0;
			int modCount = 0;
			
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
				modCount++;				
			}
			
			@Override
			public void set(T o) {
				MyArrayList.this.set(index, o);
				modCount++;
			}
			
			@Override
			public void add(T o) {
				MyArrayList.this.add(index, o)
				modCount++;
			}
			
			private void checkModCount() {
				if(MyArrayList.modCount != modCount) {
					throw new ConcurrentModificationException();
				}
			}
		}
	
	}
	
	public void checkCapacity() {
		if (size == array.length - 1) {
			T[] newArray = new T[array.length * 2];
			System.arraycopy(array, 0, newArray, 0, array.length);	
			this.array = newArray;
		}
	}
} 