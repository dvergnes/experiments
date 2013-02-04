package string;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NaiveStringFinder implements StringFinder {

	private final String pattern;

	public NaiveStringFinder(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public List<Integer> search(String text) {
		List<Integer> result = Collections.emptyList();
		if (pattern.equals(text)) {
			result = Arrays.asList(0);
		} else if (pattern.length() < text.length()) {
			result = new LinkedList<Integer>();
			int i = 0;
			int index = 0;
			while (i < text.length()) {
				if (text.charAt(i) != pattern.charAt(index)) {
					i++;
				} else {
					while ((i + index < text.length())
							&& (index < pattern.length())
							&& (text.charAt(i + index) == pattern.charAt(index))) {
						index++;
					}
					if (index == pattern.length()) {
						result.add(i);
					}
					i += index;
					index = 0;
				}
			}
		}

		return result;
	}
}
