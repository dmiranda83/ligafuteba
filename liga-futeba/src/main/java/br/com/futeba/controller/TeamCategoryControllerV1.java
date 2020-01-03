package br.com.futeba.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.futeba.models.Category;
import br.com.futeba.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/teamCategory")
public class TeamCategoryControllerV1 {

	private static final Logger logger = LoggerFactory
			.getLogger(TeamCategoryControllerV1.class);

	@Autowired
	private CategoryService service;

	@PostMapping("/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Category save(@RequestBody final Category teamCategory) {
		return service.save(teamCategory);
	}

	@GetMapping("/list/name/{name}")
	public @ResponseBody Optional<Category> getCategoryByName(
			@PathVariable("name") String name) {
		logger.info("Find category: {}", name);
		return service.findByName(name);
	}

	@GetMapping("/list/{id}")
	public @ResponseBody Optional<Category> getCategoryById(
			@PathVariable("id") Long id) {
		logger.info("Find category id: {}", id);
		return service.findById(id);
	}

	@GetMapping("/listAll")
	public @ResponseBody Iterable<Category> getAllCategories() {
		logger.info("Listing all team category");
		return service.findAll();
	}

	@PutMapping("/update/{id}")
	public Category update(@PathVariable("id") final long id,
			@RequestBody final Category teamCategory) {

		Optional<Category> currentTeamCategory = service.findById(id);

		if (currentTeamCategory.isPresent()) {
			logger.info("Updating team category id {}", id);
			currentTeamCategory.get().setName(teamCategory.getName());
			return service.update(currentTeamCategory);
		} else {
			logger.error("Team category id {} not found.", id);
			return currentTeamCategory.get();
		}

	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") final long id) {
		Optional<Category> currentTeamCategory = service.findById(id);

		logger.info("Deleting team category id {}", id);
		if (currentTeamCategory.isPresent()) {
			service.delete(id);
		} else {
			logger.error("Team category id {} not found.", id);
		}
	}

	@DeleteMapping("/delete")
	public void delete() {

		logger.info("Deleting all team category");
		service.deleteAll();
	}
}
