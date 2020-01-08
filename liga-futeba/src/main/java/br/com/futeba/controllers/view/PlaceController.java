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

import br.com.futeba.models.Place;
import br.com.futeba.services.PlaceService;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping()
@ApiIgnore
public class PlaceController {

	private static final Logger logger = LoggerFactory
			.getLogger(PlaceController.class);

	@Autowired
	private PlaceService service;

	@GetMapping("/places")
	public ModelAndView listAll() {
		logger.info("Listing all game places");
		ModelAndView model = new ModelAndView("registerPlace");
		model.addObject("list", service.findAll());
		return model;
	}

	@GetMapping("/places/{id}/edit")
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("editPlace");
		Optional<Place> place = service.findById(id);
		if (place.isPresent()) {
			model.addObject("place", place.get());
		}
		return model;
	}
	@GetMapping("/places/{id}/delete")
	public ModelAndView deletePlace(@PathVariable long id) {
		service.deleteById(id);
		return new ModelAndView("redirect:/places");
	}

	@PostMapping("/places")
	public ModelAndView save(@ModelAttribute("place") Place place) {
		ModelAndView model = new ModelAndView("places");
		Optional<Place> foundPlace = service.findByName(place.getName());
		if (place.getName() == null || (foundPlace.isPresent()
				&& place.getName().equals(foundPlace.get().getName()))) {
			model.addObject("danger", "Something Going Bad");
		} else {
			service.save(place);
			model.addObject("warning", "Game Place Registration Success");
		}

		return new ModelAndView("redirect:/places");
	}

	@PostMapping("/places/update")
	public ModelAndView update(@RequestParam("id") long id,
			@RequestParam("name") String name) {
		Optional<Place> place = service.findById(id);
		if (place.isPresent()) {
			place.get().setName(name);
			service.save(place.get());
		}
		return new ModelAndView("redirect:/places");
	}
}
