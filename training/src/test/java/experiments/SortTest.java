package experiments;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SortTest {

	@Test
	public void testBuggySortAsc() {
		List<Integer> result = Sort.buggySortAsc(new ArrayList<Integer>(
				Arrays.asList(3, 1, 3, 2)));
		assertThat(result).containsExactly(1, 2, 3, 3);
	}

	@Test
	public void testSortAsc() {
		List<Integer> result = Sort.sortAsc(new ArrayList<Integer>(Arrays
				.asList(3, 1, 3, 2)));
		assertThat(result).containsExactly(1, 2, 3, 3);
	}

}
