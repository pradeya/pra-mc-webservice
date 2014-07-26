package com.pradeya.cast.repository;

import org.springframework.data.repository.CrudRepository;

import com.pradeya.cast.domain.Mail;

public interface MailRepository extends
CrudRepository<Mail, Long>, MailRepositoryCustom{

}
