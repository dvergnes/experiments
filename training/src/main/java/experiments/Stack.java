package experiments;

public class Stack {

	private Entry min;
	private Entry current;

	private static class Entry {
		private int value;
		private Entry next;
	}

	public Integer pop() {
		Integer value = null;
		if (current != null) {
			if (current.value == min.value) {
				min = min.next;
			}
			value = current.value;
			current = current.next;
		}
		return value;
	}

	public void push(int i) {
		if (min == null || i < min.value) {
			Entry minEntry = new Entry();
			minEntry.next = min;
			minEntry.value = i;
			min = minEntry;
		}
		Entry newEntry = new Entry();
		newEntry.value = i;
		newEntry.next = current;
		current = newEntry;
	}

	public Integer min() {
		return min == null ? null : min.value;
	}

}
