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

import com.pradeya.cast.domain.Media;
import com.pradeya.cast.service.MediaService;

@Controller
@RequestMapping("/media")
public class MediaController {

	@Autowired
	MediaService mediaService;

	@RequestMapping(method = POST)
	@ResponseBody
	public Media createMedia(@RequestBody final Media media) {
		return mediaService.create(media);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseBody
	public Media updateMedia(@RequestBody Media media) {
		return mediaService.update(media);
	}

	@RequestMapping(value = "/{id}", method = GET)
	@ResponseBody
	public Media getMedia(@PathVariable long id) {
		return mediaService.findMedia(id);
	}
	
	@RequestMapping(method = GET)
	@ResponseBody
	public Iterable<Media> getAllMedia() {
		return mediaService.findAll();
	}
	
	@RequestMapping(value = "/user/{username}", method = GET)
	@ResponseBody
	public Iterable<Media> getUserMediaByUserName(@PathVariable String username) {
		return mediaService.findUserMediaByUserName(username);
	}

}
