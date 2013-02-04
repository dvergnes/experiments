package string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BoyerMooreStringFinder implements StringFinder {

	private final String pattern;
	private final Map<Character, Integer> wrongCharacterRuleTable;
	private final int[] goodSuffixesRuleTable;

	public BoyerMooreStringFinder(String pattern) {
		this.pattern = pattern;
		wrongCharacterRuleTable = prepareWrongRuleTable(pattern);
		goodSuffixesRuleTable = prepareGoodSuffixesTable(pattern);
	}

	protected static int[] suffixes(String pattern) {
		int f = 0;
		int patternLength = pattern.length();
		int[] suff = new int[patternLength];

		suff[patternLength - 1] = patternLength;
		int g = patternLength - 1;
		for (int i = patternLength - 2; i >= 0; --i) {
			if (i > g && suff[i + patternLength - 1 - f] < i - g) {
				suff[i] = suff[i + patternLength - 1 - f];
			} else {
				if (i < g) {
					g = i;
				}
				f = i;
				while (g >= 0
						&& pattern.charAt(g) == pattern.charAt(g
								+ patternLength - 1 - f)) {
					--g;
				}
				suff[i] = f - g;
			}
		}
		return suff;
	}

	protected static int[] prepareGoodSuffixesTable(String pattern) {
		int patternLength = pattern.length();
		int[] suff = suffixes(pattern);
		int[] bmGs = new int[patternLength];

		for (int i = 0; i < patternLength; ++i) {
			bmGs[i] = patternLength;
		}
		for (int i = patternLength - 1; i >= 0; --i) {
			if (suff[i] == i + 1) {
				for (int j = 0; j < patternLength - 1 - i; ++j) {
					if (bmGs[j] == patternLength) {
						bmGs[j] = patternLength - 1 - i;
					}
				}
			}
		}
		for (int i = 0; i <= patternLength - 2; ++i) {
			bmGs[patternLength - 1 - suff[i]] = patternLength - 1 - i;
		}
		return bmGs;
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
					i += Math.max(goodSuffixesRuleTable[pattern.length() - 1
							- j], getNextPosition(currentChar));
				}
			}
		}
		return result;
	}

	private Integer getNextPosition(char currentChar) {
		Integer shift = wrongCharacterRuleTable.get(currentChar);
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
