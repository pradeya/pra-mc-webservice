package com.pradeya.cast.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class ScheduleRepositoryImpl implements MediaRepositoryCustom {
	@Autowired
	MongoTemplate mongoTemplate;

	public boolean updatePushState(long scheduleId,String status){
		DBObject responseObj = MongoDBUtil.updateDocumentAttribute(
				getCollection(), ID, scheduleId,"pushState",status);
			return true;
	}
	
	private DBCollection getCollection() {
		return mongoTemplate.getCollection(MEDIA);
	}
	
	

}
