package experiments;

import java.util.LinkedList;

public class Stack {

	private int min = Integer.MAX_VALUE;
	private final LinkedList<Integer> elements = new LinkedList<Integer>();

	public Integer pop() {
		return elements.pop();
	}

	public void push(int entry) {
		if (entry < min) {
			min = entry;
		}
		elements.push(entry);
	}

	public int min() {
		return min;
	}

}
