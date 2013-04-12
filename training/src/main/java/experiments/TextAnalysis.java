package experiments;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalysis {

	public class Result {
		private int start;
		private int end;
		private int length;

		/**
		 * @return the start
		 */
		public int getStart() {
			return start;
		}

		/**
		 * @return the end
		 */
		public int getEnd() {
			return end;
		}

		/**
		 * @return the length
		 */
		public int getLength() {
			return length;
		}
	}

	public static Result analyse(String text, List<String> words) {
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		for (String word : words) {
			map.put(word, search(text, word));
		}

		return null;
	}

	static List<Integer> search(String text, String word) {
		List<Integer> indexes = new LinkedList<Integer>();
		Pattern pattern = Pattern.compile("[\\.\\?!]?" + word + "[\\s+\\.*]",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			indexes.add(matcher.start());
		}
		return indexes;
	}

}
