package experiments;

public class Search {

	public static int indexOf(int[] a, int x) {
		int l = 0;
		int h = a.length - 1;
		int m;

		while (l <= h) {
			m = (l + h) / 2;

			if (a[m] < x) {
				l = m + 1;
			} else if (a[m] > x) {
				h = m - 1;
			} else {
				return m;
			}
		}

		return -1;
	}

}
