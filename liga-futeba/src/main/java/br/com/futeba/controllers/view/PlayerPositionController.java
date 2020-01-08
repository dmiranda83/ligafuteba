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

import br.com.futeba.models.Position;
import br.com.futeba.services.PositionService;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/")
@ApiIgnore
public class PlayerPositionController {

	private static final Logger logger = LoggerFactory
			.getLogger(PlayerPositionController.class);

	@Autowired
	private PositionService service;

	@GetMapping("/playerPositions")
	public ModelAndView listAll() {
		logger.info("Listing all team category");
		ModelAndView model = new ModelAndView("registerPlayerPosition");
		model.addObject("list", service.findAll());
		return model;
	}

	@GetMapping("/playerPositions/{id}/edit")
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("editPlayerPosition");
		Optional<Position> position = service.findById(id);
		if (position.isPresent()) {
			model.addObject("place", position.get());
		}
		return model;
	}
	@GetMapping("/playerPositions/{id}/delete")
	public ModelAndView deletePlace(@PathVariable long id) {
		service.deleteById(id);
		return new ModelAndView("redirect:/playerPositions");
	}

	@PostMapping("/playerPositions")
	public ModelAndView save(@ModelAttribute("position") Position position) {
		ModelAndView model = new ModelAndView("playerPositions");
		Optional<Position> foundPosition = service
				.findByName(position.getName());
		if (position.getName() == null || (foundPosition.isPresent()
				&& position.getName().equals(foundPosition.get().getName()))) {
			model.addObject("danger", "Something Going Bad");
		} else {
			service.save(position);
			model.addObject("warning", "Player Position Registration Success");
		}

		return new ModelAndView("redirect:/playerPositions");
	}

	@PostMapping("/playerPositions/update")
	public ModelAndView update(@RequestParam("id") long id,
			@RequestParam("name") String name) {
		Optional<Position> position = service.findById(id);
		if (position.isPresent()) {
			position.get().setName(name);
			service.save(position.get());
		}
		return new ModelAndView("redirect:/playerPositions");
	}
}
