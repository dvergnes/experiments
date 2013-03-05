package experiments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort {

	public static <T extends Comparable<? super T>> List<T> buggySortAsc(
			List<T> list) {
		ArrayList<T> t = new ArrayList<T>();
		while (!list.isEmpty()) {
			T max = Collections.max(list);
			t.add(max);
			list.remove(max);
		}
		Collections.reverse(t);
		return t;
	}

	public static <T extends Comparable<? super T>> List<T> sortAsc(List<T> list) {
		Collections.sort(list);
		return list;
	}

}
