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

import com.pradeya.cast.domain.Mail;
import com.pradeya.cast.service.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {

	@Autowired
MailService mailService;

	@RequestMapping(method = POST)
	@ResponseBody
	public boolean createMail(@RequestBody final Mail mail) {
		return mailService.create(mail);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseBody
	public int updateMail(@RequestBody Mail mail) {
		return mailService.update(mail);
	}

	@RequestMapping(value = "/{id}", method = GET)
	@ResponseBody
	public Mail getMail(@PathVariable long id) {
		return mailService.findOne(id);
	}

	/*@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseBody
	public Organization deleteOrg(@PathVariable String id) {
		return organizationService.delete(id);
	}*/

	@RequestMapping(method = GET)
	@ResponseBody
	public Iterable<Mail> getALLMail() {
		return mailService.findAll();
	}
}
