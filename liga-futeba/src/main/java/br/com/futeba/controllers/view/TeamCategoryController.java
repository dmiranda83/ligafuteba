package br.com.futeba.controllers.view;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.futeba.models.Category;
import br.com.futeba.services.CategoryService;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/")
@ApiIgnore
public class TeamCategoryController {

	private static final Logger logger = LoggerFactory
			.getLogger(TeamCategoryController.class);

	@Autowired
	private CategoryService service;

	@GetMapping("/teamCategories")
	public ModelAndView listAll() {
		logger.info("Listing all team category");
		ModelAndView model = new ModelAndView("registerCategory");
		model.addObject("list", service.findAll());
		return model;
	}

	@GetMapping("/teamCategories/{id}/edit")
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("editCategory");
		Optional<Category> category = service.findById(id);
		if (category.isPresent()) {
			model.addObject("category", category.get());
		}
		return model;
	}
	@GetMapping("/teamCategories/{id}/delete")
	public ModelAndView deleteCategory(@PathVariable long id) {
		service.deleteById(id);
		return new ModelAndView("redirect:/teamCategories");
	}

	@PostMapping("/teamCategories")
	public ModelAndView save(
			@ModelAttribute("category") Category teamCategory) {
		ModelAndView model = new ModelAndView("teamCategories");
		Optional<Category> foundCategory = service
				.findByName(teamCategory.getName());
		if (teamCategory.getName() == null
				|| (foundCategory.isPresent() && teamCategory.getName()
						.equals(foundCategory.get().getName()))) {
			model.addObject("danger", "Something Going Bad");
		} else {
			service.save(teamCategory);
			model.addObject("warning", "Category Registration Success");
		}

		return new ModelAndView("redirect:/teamCategories");
	}

	@PostMapping("/teamCategories/update")
	public ModelAndView update(@RequestParam("id") long id,
			@RequestParam("name") String name) {
		Optional<Category> category = service.findById(id);
		if (category.isPresent()) {
			category.get().setName(name);
			service.save(category.get());
		}
		return new ModelAndView("redirect:/teamCategories");
	}
}
