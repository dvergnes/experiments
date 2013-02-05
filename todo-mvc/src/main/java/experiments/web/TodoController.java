package experiments.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import experiments.entity.Todo;
import experiments.service.TodoService;

@Controller
@RequestMapping(value = "/todo", produces = "application/json")
public class TodoController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TodoController.class);

	@Resource
	private TodoService todoService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Todo> getAll() {
		return todoService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Todo create(@RequestBody Todo todo) {
		LOGGER.info("Create todo: '{}'", todo);
		return todoService.create(todo);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Todo update(@RequestBody Todo todo, @PathVariable Long id) {
		LOGGER.info("Update todo: '{}'", todo);
		todoService.update(id, todo);
		return todo;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		LOGGER.info("Delete todo id: '{}'", id);
		todoService.delete(id);
	}

}
