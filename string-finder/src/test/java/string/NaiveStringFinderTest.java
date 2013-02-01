package string;

public class NaiveStringFinderTest extends AbstractStringFinderTest {

	@Override
	protected StringFinder createStringFinder(String pattern) {
		return new NaiveStringFinder(pattern);
	}

}
