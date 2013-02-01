package string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.Test;

public class BoyerMooreStringFinderTest extends AbstractStringFinderTest {

	@Test
	public void testPrepareWrongRuleTable() {
		Map<Character, Integer> wikipediaTable = BoyerMooreStringFinder
				.prepareWrongRuleTable("WIKIPEDIA");
		assertNull(wikipediaTable.get('A'));
		assertEquals(1, wikipediaTable.get('I').intValue());
		assertEquals(2, wikipediaTable.get('D').intValue());
		assertEquals(3, wikipediaTable.get('E').intValue());
		assertEquals(4, wikipediaTable.get('P').intValue());
		assertEquals(6, wikipediaTable.get('K').intValue());
		assertEquals(8, wikipediaTable.get('W').intValue());
		assertNull(wikipediaTable.get('Z'));

		Map<Character, Integer> exampleTable = BoyerMooreStringFinder
				.prepareWrongRuleTable("EXAMPLE");
		assertEquals(6, exampleTable.get('E').intValue());
		assertEquals(1, exampleTable.get('L').intValue());
		assertEquals(2, exampleTable.get('P').intValue());
		assertEquals(3, exampleTable.get('M').intValue());
		assertEquals(4, exampleTable.get('A').intValue());
		assertEquals(5, exampleTable.get('X').intValue());

	}

	@Override
	protected StringFinder createStringFinder(String pattern) {
		return new BoyerMooreStringFinder(pattern);
	}

}
