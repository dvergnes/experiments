package experiments.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import experiments.entity.Todo;
import experiments.repository.TodoRepository;

@Service
public class TodoService {

	@Resource
	private TodoRepository todoRepository;

	/**
	 * @return
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	public List<Todo> findAll() {
		return todoRepository.findAll();
	}

	public Todo create(Todo todo) {
		return todoRepository.save(todo);
	}

	public void update(Long id, Todo todo) {
		todo.setLastUpdate(new Date());
		todoRepository.save(todo);
	}

	public void delete(Long id) {
		todoRepository.delete(id);
	}

}
