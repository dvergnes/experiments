package experiments.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import experiments.entity.Todo;
import experiments.exception.UnauthorizedActionException;
import experiments.repository.TodoRepository;

@Service
public class TodoService {

	@Resource
	private TodoRepository todoRepository;

	/**
	 * @param username
	 * @return
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	public List<Todo> findAll(String username) {
		return todoRepository.findByUsername(username);
	}

	public Todo create(String username, Todo todo) {
		todo.setUsername(username);
		return todoRepository.save(todo);
	}

	public void update(String username, String id, Todo todo)
			throws UnauthorizedActionException {
		loadTodo(username, id);
		todo.setLastUpdate(new Date());
		todoRepository.save(todo);
	}

	private Todo loadTodo(String username, String id)
			throws UnauthorizedActionException {
		Todo old = todoRepository.findOne(id);
		if (!username.equals(old.getUsername())) {
			throw new UnauthorizedActionException();
		}
		return old;
	}

	public void delete(String username, String id)
			throws UnauthorizedActionException {
		loadTodo(username, id);
		todoRepository.delete(id);
	}

}
