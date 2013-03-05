package experiments;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class SearchTest {

	@Test
	public void testIndexOf() {
		assertThat(Search.indexOf(new int[] { 1, 2, 5, 6 }, 6)).isEqualTo(3);
	}

	@Test
	public void testIndexOfNotFound() {
		assertThat(Search.indexOf(new int[] { 1, 2, 5, 6 }, 8)).isEqualTo(-1);
	}

}
