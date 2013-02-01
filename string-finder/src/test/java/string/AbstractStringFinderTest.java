package string;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStringFinderTest {

	private StringFinder stringFinder;

	public AbstractStringFinderTest() {
		super();
	}

	@Before
	public void setUp() {
		stringFinder = createStringFinder();
	}

	protected abstract StringFinder createStringFinder();

	@Test
	public void testSearchNoMatch() {
		assertTrue(stringFinder.search("too long pattern for this text",
				"too short").isEmpty());
	}

	@Test
	public void testSearchPerfectMatch() {
		String pattern = "exactly the same";
		assertThat(stringFinder.search(pattern, pattern)).containsExactly(0);
	}

	@Test
	public void testSearch() {
		String text = "treuil toto voiture truc toreador totorototu rototototo";
		String patternString = "toto";
		Pattern pattern = Pattern.compile(patternString);
		LinkedList<Integer> indexes = new LinkedList<Integer>();
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			indexes.add(matcher.start());
		}
		System.out.println(indexes);
		assertEquals(indexes, stringFinder.search(patternString, text));
	}

}