package experiments;

public class Stack {

	private int min = Integer.MAX_VALUE;
	private Entry current;

	private static class Entry {
		private int value;
		private Entry next;
	}

	public Integer pop() {
		int value = current.value;
		current = current.next;
		return value;
	}

	public void push(int entry) {
		if (entry < min) {
			min = entry;
		}
		Entry newEntry = new Entry();
		newEntry.value = entry;
		newEntry.next = current;
		current = newEntry;
	}

	public int min() {
		return min;
	}

}
