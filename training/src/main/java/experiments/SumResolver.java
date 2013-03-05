package experiments;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SumResolver {

	public static class Pair {
		private final int a;
		private final int b;

		Pair(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + sum();
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Pair other = (Pair) obj;
			return sum() == other.sum();
		}

		public int sum() {
			return a + b;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Pair {" + a + ", " + b + "}";
		}

	}

	public static Set<Pair> findPairs(List<Integer> numbers, int total) {
		Collection<Pair> allPairs = new LinkedList<SumResolver.Pair>();
		for (Integer i : numbers) {
			for (Integer j : numbers) {
				allPairs.add(new Pair(i, j));
			}
		}
		Set<Pair> result = new HashSet<SumResolver.Pair>();
		for (Pair pair : allPairs) {
			if (pair.sum() == total) {
				result.add(pair);
			}
		}
		return result;
	}

}
