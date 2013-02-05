package experiments.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import experiments.entity.Todo;
import experiments.exception.UnauthorizedActionException;
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
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		return todoService.findAll(username);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Todo create(@RequestBody Todo todo) {
		LOGGER.info("Create todo: '{}'", todo);
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		return todoService.create(username, todo);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Todo update(@RequestBody Todo todo, @PathVariable Long id)
			throws UnauthorizedActionException {
		LOGGER.info("Update todo: '{}'", todo);
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		todoService.update(username, id, todo);
		return todo;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void delete(@PathVariable Long id)
			throws UnauthorizedActionException {
		LOGGER.info("Delete todo id: '{}'", id);
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		todoService.delete(username, id);
	}

	@ExceptionHandler(UnauthorizedActionException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "You cannot access this resource because you are not the owner of it")
	public void handleUnauthorizedActionException() {
		LOGGER.info("Attempt to access to a resource owned by an another user");
	}

}
