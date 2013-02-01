package string;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoyerMooreStringFinder implements StringFinder {

	@Override
	public List<Integer> search(String pattern, String text) {
		Map<Character, Integer> wrongRuleTable = prepareWrongRuleTable(pattern);
		return null;
	}

	protected Map<Character, Integer> prepareWrongRuleTable(String pattern) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int length = pattern.length();
		char lastChar = pattern.charAt(length - 1);
		for (int i = 0; i < length; i++) {
			char currentChar = pattern.charAt(i);
			if (map.get(currentChar) == null && currentChar != lastChar) {
				map.put(currentChar, length - pattern.lastIndexOf(currentChar)
						- 1);
			}
		}
		return map;
	}
}
