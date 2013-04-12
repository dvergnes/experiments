package experiments;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class TextAnalysisTest {

	private final String text = "I am testing my algorithm. My purpose is to train on this. This is not so simple. But I will try my best.";

	@Test
	public void testAnalyse() {
		TextAnalysis.analyse(text, Arrays.asList("my", "is", "this"));
	}

	@Test
	public void testSearch() {
		assertThat(
				TextAnalysis
						.search("I try my best. My goal is to be happy. Oh my...!My lord! Mythology?A typo!hapmy",
								"my")).containsExactly(6, 15, 42, 47);
	}
}
