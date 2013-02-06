package experiments.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import experiments.entity.Todo;

public interface TodoRepository extends
		PagingAndSortingRepository<Todo, String> {

	List<Todo> findByUsername(String username);

}
