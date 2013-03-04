package experiments;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class BuggyCodeTest {

	@Test
	public void testF() {
		ArrayList result = BuggyCode.f(new ArrayList(Arrays.asList(3, 1, 2)));
		assertThat(result).containsExactly(1, 2, 3);
	}

}
