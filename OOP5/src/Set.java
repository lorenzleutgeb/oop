import java.util.Iterator;
import java.util.NoSuchElementException;

public class Set<T> implements Iterable<T> {
	protected Item<T> head = null;

	public void insert(T value) {
		if (!contains(value))
			head = new Item<T>(value, head);
	}

	public Iterator<T> iterator() {
		return new ItemIterator<T>(this);
	}
	
	protected boolean contains(T value) {
		for (T item : this)
			if (item == value)
				return true;
		
		return false;
	}
	
	protected boolean isEmpty() {
		return head == null;
	}

	protected static class Item<T> {
		private Item<T> next = null;
		private T value = null;

		public Item(T value, Item<T> next) {
			this.value = value;
			this.next = next;
		}

		public T getValue() {
			return value;
		}

		public Item<T> getNext() {
			return next;
		}
		
		public void setNext(Item<T> value) {
			next = value;
		}
	}

	protected static class ItemIterator<T> implements Iterator<T> {
		private Item<T> current = null;
		private Item<T> previous = null;
		private boolean removed = false;
		private final Set<T> set;

		public ItemIterator(Set<T> set) {
			this.set = set;
		}

		@Override
		public boolean hasNext() {
			return current == null ? set.head != null : current.getNext() != null;
		}

		@Override
		public T next() {
			previous = current;
			current = current == null ? set.head : current.getNext();
			
			if (current == null)
				throw new NoSuchElementException();
			
			removed = false;
			
			return current.getValue();
		}

		@Override
		public void remove() {
			if (current == null || removed)
				throw new IllegalStateException();
			
			if (previous == null)
				set.head = current.getNext();
			else
				previous.setNext(current.getNext());
			
			removed = true;
		}
	}
}
