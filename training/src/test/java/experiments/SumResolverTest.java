package experiments;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import experiments.SumResolver.Pair;

public class SumResolverTest {

	private final List<Integer> numbers = Arrays.asList(1, 6, 5, 8, 3, 13, 9,
			-1, 10, 4);
	private final int total = 12;

	@Test
	public void testFindPairsEmpty() {
		assertThat(SumResolver.findPairs(null, total)).isEmpty();
	}

	@Test
	public void testFindPairsV2Empty() {
		assertThat(SumResolver.findPairsV2(null, total)).isEmpty();
	}

	@Test
	public void testFindPairs() {
		Set<Pair> pairs = SumResolver.findPairs(numbers, total);
		assertThat(pairs).hasSize(3).contains(new SumResolver.Pair(3, 9),
				new SumResolver.Pair(-1, 13), new SumResolver.Pair(4, 8));
	}

	@Test
	public void testFindPairsV2() {
		Set<Pair> pairs = SumResolver.findPairsV2(numbers, total);
		assertThat(pairs).hasSize(3).contains(new SumResolver.Pair(3, 9),
				new SumResolver.Pair(-1, 13), new SumResolver.Pair(4, 8));
	}

}
