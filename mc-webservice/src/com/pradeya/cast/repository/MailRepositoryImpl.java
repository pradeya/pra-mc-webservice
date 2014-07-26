package com.pradeya.cast.repository;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.pradeya.cast.domain.Mail;

public class MailRepositoryImpl implements MailRepositoryCustom{
	private static Logger L = LoggerFactory
			.getLogger(OrganizationRepositoryImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MappingMongoConverter converter;
	
	@Autowired
	SequenceRepository sequenceRepository;
	
	@Override
	public boolean createMail(Mail mail){
		
		mail.setId(sequenceRepository.getNextSequence(MAIL));
		
		BasicDBObject subDocForSave = (BasicDBObject) converter
				.convertToMongoType(mail);
		WriteResult responseObj = getCollection().save(subDocForSave);
		return responseObj.getN()>0 ? true:false;
	}
	private DBCollection getCollection() {
		return mongoTemplate.getCollection(MAIL);
	}
	public Mail findMail(long baseDocIdVal) {
		BasicDBObject query = new BasicDBObject(ID, baseDocIdVal);
		/*BasicDBObject fields = new BasicDBObject(ORGANIZATION_DETAIL, 1).append("ID", 1).append(FINANCIAL_PLANNER, 1);
		L.debug("findSubDocument req {{},{}}: ", query, fields);*/

		DBCursor cursor = getCollection().find(query);
		L.debug("findSubDocument res total count: {} ", cursor.count());
		DBObject obj = cursor.next();
		L.debug("findSubDocument res obj: {} ", obj);
		Mail mail = converter.read(Mail.class, obj);
		return mail;
	}

	public ArrayList<Mail> findAllMail(){
		ArrayList<Mail> org=new ArrayList<Mail>();
		DBCursor cursor=getCollection().find();
		while(cursor.hasNext()){
			DBObject obj=cursor.next();
			Mail mail=converter.read(Mail.class,obj);
			org.add(mail);
		}
		return org;
	}
	@Override
	public int updateMail(
			Mail mail) {
		DBObject subDocForUpdate = (BasicDBObject) converter
				.convertToMongoType(mail);
		return MongoDBUtil.updateSubDocument(
				getCollection(), 
				null,null,
				MAIL,
				mail.getId(),subDocForUpdate);
	}
	

}
