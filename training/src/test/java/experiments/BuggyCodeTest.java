package experiments;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class BuggyCodeTest {

	@Test
	public void testSortAsc() {
		ArrayList result = BuggyCode.sortAsc(new ArrayList(Arrays.asList(3, 1,
				3, 2)));
		assertThat(result).containsExactly(1, 2, 3, 3);
	}

}
