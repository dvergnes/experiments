package string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.Test;

public class BoyerMooreStringFinderTest {

	@Test
	public void testPrepareWrongRuleTable() {
		BoyerMooreStringFinder stringFinder = new BoyerMooreStringFinder();
		Map<Character, Integer> wrongRuleTable = stringFinder
				.prepareWrongRuleTable("WIKIPEDIA");
		assertNull(wrongRuleTable.get('A'));
		assertEquals(1, wrongRuleTable.get('I').intValue());
		assertEquals(2, wrongRuleTable.get('D').intValue());
		assertEquals(3, wrongRuleTable.get('E').intValue());
		assertEquals(4, wrongRuleTable.get('P').intValue());
		assertEquals(6, wrongRuleTable.get('K').intValue());
		assertEquals(8, wrongRuleTable.get('W').intValue());
		assertNull(wrongRuleTable.get('Z'));
	}

}
