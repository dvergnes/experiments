package experiments;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import experiments.SumResolver.Pair;

public class SumResolverTest {

	@Test
	public void testFindPairs() {
		List<Integer> numbers = Arrays.asList(1, 6, 5, 8, 3, 13, 9, -1, 10, 4);
		int total = 12;
		Set<Pair> pairs = SumResolver.findPairs(numbers, total);
		assertThat(pairs).hasSize(3).contains(new SumResolver.Pair(3, 9),
				new SumResolver.Pair(-1, 13), new SumResolver.Pair(4, 8));
	}

}
