package experiments;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SumResolverTest {

	@Test
	public void testFindPairs() {
		List<Integer> numbers = Arrays.asList(1, 6, 6, 5, 8, 3, 13, 9, -1, 10,
				4);
		int total = 12;
		assertThat(SumResolver.findPairs(numbers, total)).containsOnly(
				new SumResolver.Pair(3, 9), new SumResolver.Pair(4, 8),
				new SumResolver.Pair(-1, 13), new SumResolver.Pair(6, 6));
	}

}
