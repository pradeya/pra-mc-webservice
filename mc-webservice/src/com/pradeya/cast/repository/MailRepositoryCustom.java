package com.pradeya.cast.repository;

import java.util.ArrayList;
import java.util.Date;

import com.pradeya.cast.domain.Mail;

public interface MailRepositoryCustom {
	public static final String MAIL = "mail";
	public static final String ID = "_id";
	public String TO="to";
	public String TYPEOFMAIL="typeOfMail";
	public String FOLDER="folder";
	public String  FROM="from";
    public String  SUBJECT="subject";
    public String BODY="body";
    public final Date date=new Date();
	
	public boolean createMail(Mail mail);
	public Mail findMail(long baseDocIdVal);
	public ArrayList<Mail> findAllMail();
	public int updateMail(Mail mail);
	}
