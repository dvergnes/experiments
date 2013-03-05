package experiments;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class StackTest {

	@Test
	public void testStack() {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(150);
		stack.push(-1);
		stack.push(1500);
		assertThat(stack.min()).isEqualTo(-1);
		assertThat(stack.pop()).isEqualTo(1500);
		assertThat(stack.pop()).isEqualTo(-1);
		assertThat(stack.pop()).isEqualTo(150);
		assertThat(stack.pop()).isEqualTo(1);
	}

}
