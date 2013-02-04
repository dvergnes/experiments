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

	private static final String PATTERN = "toto";
	private StringFinder stringFinder;

	public AbstractStringFinderTest() {
		super();
	}

	@Before
	public void setUp() {
		stringFinder = createStringFinder(PATTERN);
	}

	protected abstract StringFinder createStringFinder(String pattern);

	@Test
	public void testSearchNoMatch() {
		assertTrue(stringFinder.search("to").isEmpty());
	}

	@Test
	public void testSearchPerfectMatch() {
		assertThat(stringFinder.search(PATTERN)).containsExactly(0);
	}

	@Test
	public void testSearch() {
		String text = "treuil toto voiture truc toreador totorototu rototototo";
		String patternString = PATTERN;
		Pattern pattern = Pattern.compile(patternString);
		LinkedList<Integer> indexes = new LinkedList<Integer>();
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			indexes.add(matcher.start());
		}
		assertEquals(indexes, stringFinder.search(text));
	}

}