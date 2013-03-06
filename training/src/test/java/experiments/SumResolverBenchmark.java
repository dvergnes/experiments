package experiments;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class SumResolverBenchmark extends SimpleBenchmark {

	@Param({ "10", "100", "1000", "2000" })
	private int size;
	private int total;
	private List<Integer> values;

	@Override
	protected void setUp() throws Exception {
		values = new ArrayList<Integer>(size);
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			int value = random.nextInt();
			values.add(value);
		}
		total = random.nextInt();
	}

	public void timeFindPair(int reps) {
		for (int i = 0; i < reps; i++) {
			SumResolver.findPairs(values, total);
		}
	}

	public void timeFindPairV2(int reps) {
		for (int i = 0; i < reps; i++) {
			SumResolver.findPairsV2(values, total);
		}
	}

	public static void main(String[] args) {
		Runner.main(SumResolverBenchmark.class, new String[] { "--timeUnit",
				"ms" });
	}

}
