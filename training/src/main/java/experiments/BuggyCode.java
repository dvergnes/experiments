package experiments;

import java.util.ArrayList;
import java.util.Collections;

public class BuggyCode {

	public static ArrayList f(ArrayList list) {
		ArrayList t = new ArrayList();
		while (!list.isEmpty()) {
			Object max = Collections.max(list);
			t.add(max);
			list.remove(max);
		}
		Collections.reverse(t);
		return t;
	}

}
