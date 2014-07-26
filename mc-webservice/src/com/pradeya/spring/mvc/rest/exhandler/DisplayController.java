package com.pradeya.spring.mvc.rest.exhandler;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pradeya.cast.domain.Display;
import com.pradeya.cast.service.DisplayService;

@Controller
@RequestMapping("/display")
public class DisplayController {

	@Autowired
	DisplayService displayService;

	@RequestMapping(method = POST)
	@ResponseBody
	public Display createDisplay(@RequestBody final Display display) {
		return displayService.create(display);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseBody
	public Display updateDisplay(@RequestBody Display display) {
		return displayService.update(display);
	}

	@RequestMapping(value = "/{id}", method = GET)
	@ResponseBody
	public Display getDisplay(@PathVariable long id) {
		return displayService.findDisplay(id);
	}
	
	@RequestMapping(method = GET)
	@ResponseBody
	public Iterable<Display> getAllDisplay() {
		return displayService.findAll();
	}
	
}
