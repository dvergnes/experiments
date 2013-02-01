package string;

public class NaiveStringFinderTest extends AbstractStringFinderTest {

	@Override
	protected StringFinder createStringFinder() {
		return new NaiveStringFinder();
	}

}
