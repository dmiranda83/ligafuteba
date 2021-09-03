package br.com.futeba.controllers.view;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/")
@ApiIgnore
public class IndexController implements ErrorController {

	@GetMapping
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/error")
	@ResponseBody
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "No Mapping Found";
	}

}
