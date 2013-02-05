package experiments.entity;

public class TodoBuilder {

	private final Todo todo = new Todo();

	public TodoBuilder title(String t) {
		todo.setTitle(t);
		return this;
	}

	public TodoBuilder username(String username) {
		todo.setUsername(username);
		return this;
	}

	public TodoBuilder content(String content) {
		todo.setContent(content);
		return this;
	}

	public TodoBuilder complete() {
		todo.setCompleted(true);
		return this;
	}

	public TodoBuilder order(int o) {
		todo.setOrder(o);
		return this;
	}

	public Todo get() {
		return todo;
	}

}
