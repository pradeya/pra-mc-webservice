package com.pradeya.cast.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeya.cast.domain.Mail;
import com.pradeya.cast.repository.MailRepository;
import com.pradeya.spring.mvc.rest.exhandler.UnknownResourceException;

@SuppressWarnings("serial")
@Service
public class MailService implements Serializable{

	@Autowired
	private MailRepository mailRepository;
	
	public Mail findOne(long id) throws UnknownResourceException{
		Mail mail = mailRepository.findMail(id);
		if(null == mail)
			throw new UnknownResourceException("Unable to find organization with id '" + id + "'");
		else return mail;
	}

	public List<Mail> findAll(){
		return mailRepository.findAllMail();
	}
	
	public boolean create(Mail mail) {
		return mailRepository.createMail(mail);
	}

	public int update(Mail mail) throws UnknownResourceException{
		return mailRepository.updateMail(mail);
	}
}
