package string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BoyerMooreStringFinder implements StringFinder {

	private final String pattern;
	private final Map<Character, Integer> wrongRuleTable;

	public BoyerMooreStringFinder(String pattern) {
		this.pattern = pattern;
		wrongRuleTable = prepareWrongRuleTable(pattern);
	}

	@Override
	public List<Integer> search(String text) {
		List<Integer> result = new LinkedList<Integer>();
		if (pattern.equals(text)) {
			result.add(0);
		} else if (pattern.length() < text.length()) {
			int i = pattern.length() - 1;
			while (i < text.length()) {
				char currentChar = text.charAt(i);
				int j = 0;
				while (j < pattern.length()
						&& text.charAt(i - j) == pattern.charAt(pattern
								.length() - 1 - j)) {
					j++;
				}
				if (j == pattern.length()) {
					result.add(i - j + 1);
					i += pattern.length() - 1;
				} else {
					i += getNextPosition(currentChar);
				}
			}
		}
		return result;
	}

	private Integer getNextPosition(char currentChar) {
		Integer shift = wrongRuleTable.get(currentChar);
		if (shift == null) {
			shift = pattern.length();
		}
		return shift;
	}

	protected static Map<Character, Integer> prepareWrongRuleTable(
			String pattern) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		String subPattern = pattern.substring(0, pattern.length() - 1);
		int length = subPattern.length();
		for (int i = 0; i < length; i++) {
			char currentChar = pattern.charAt(i);
			map.put(currentChar, length - i);
		}
		return map;
	}
}
