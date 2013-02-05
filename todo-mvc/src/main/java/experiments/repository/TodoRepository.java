package experiments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import experiments.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	List<Todo> findByUsername(String username);

}
