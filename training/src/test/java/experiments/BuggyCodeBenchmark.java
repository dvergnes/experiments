package experiments;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class BuggyCodeBenchmark extends SimpleBenchmark {
	@Param({ "1000", "10000", "50000" })
	private int size;
	private List<Integer> values;

	@Override
	protected void setUp() throws Exception {
		values = new ArrayList<Integer>(size);
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			values.add(random.nextInt());
		}
	}

	public List<Integer> timeBuggySortAsc(int reps) {
		List<Integer> dummy = new LinkedList<Integer>();
		for (int i = 0; i < reps; i++) {
			dummy.addAll(BuggyCode.buggySortAsc(values));
		}
		return dummy;
	}

	public void timeSortAsc(int reps) {
		for (int i = 0; i < reps; i++) {
			BuggyCode.sortAsc(values);
		}
	}

	public static void main(String[] args) throws Exception {
		Runner.main(BuggyCodeBenchmark.class,
				new String[] { "--timeUnit", "ms" });
	}

}
