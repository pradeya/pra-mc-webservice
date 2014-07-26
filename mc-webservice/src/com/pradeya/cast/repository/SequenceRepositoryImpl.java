package com.pradeya.cast.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.pradeya.cast.domain.Sequence;

public class SequenceRepositoryImpl implements SequenceRepositoryCustom {

	static Logger L = LoggerFactory.getLogger(SequenceRepositoryImpl.class);
	public static final String SEQUENCE_COLLECTION = "sequence";

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MappingMongoConverter converter;

	public long getNextSequence(String name) {

		BasicDBObject query = new BasicDBObject("name", name);

		BasicDBObject updateQuery = null;
		if (name != null
				&& OrganizationRepositoryCustom.ORGANIZATION.equalsIgnoreCase(name)) {
			if (findSequenceCount(name) == 0) {
				updateQuery = new BasicDBObject("$inc", new BasicDBObject(
						"sequence", 707261));
			} else {
				updateQuery = new BasicDBObject("$inc", new BasicDBObject(
						"sequence", getRandomNumber()));
			}
		}else{
			updateQuery = new BasicDBObject("$inc", new BasicDBObject(
					"sequence", 1));
		}

		L.debug("Seq req findAndModify {{},{}}", query, updateQuery);
		DBObject responseObj = mongoTemplate.getCollection(SEQUENCE_COLLECTION)
				.findAndModify(query, null, null, false, updateQuery, true,
						true);
		L.debug("Seq response obj {}", responseObj);

		Sequence sq = new Sequence();
		DBObject d = (DBObject) converter.convertToMongoType(sq);
		L.debug("converter.convertToMongoType {}", d);
		return converter.read(Sequence.class, responseObj).getSequence();

	}

	public static long getRandomNumber() {
		return Math.round(Math.random() * 100);
	}

	public long findSequenceCount(String name) {
		BasicDBObject query = new BasicDBObject("name", name);
		return mongoTemplate.getCollection(SEQUENCE_COLLECTION).count(query);
	}

}
