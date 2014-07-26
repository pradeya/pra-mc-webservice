package com.pradeya.cast.repository;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class MongoDBUtil {

	static Logger L = LoggerFactory.getLogger(MongoDBUtil.class);

	
	public static DBObject updateDocumentAttribute(DBCollection collection, String baseDocIdKey,
			long baseDocIdVal, String docAttribute,  Object docAttributeValue) {

			BasicDBObject query = new BasicDBObject(baseDocIdKey, baseDocIdVal);
			
			BasicDBObject attribute = new BasicDBObject(docAttribute, docAttributeValue);
			BasicDBObject updateQuery = new BasicDBObject("$set", attribute);
			BasicDBObject field = new BasicDBObject("orgId", 1).append(docAttribute, 1);
			
			L.debug("updateDocumentAttribute req {{},{}}: ", query, updateQuery);
			
			DBObject responseObj = collection.findAndModify(query,
					field, null, false, updateQuery, true, false);
			L.debug("updateDocumentAttribute res obj {}: ", responseObj);
			return 	responseObj;
	}	
	

	public static int saveSubDocument(DBCollection collection, String baseDocIdKey,
			long baseDocIdVal, String subDocKey, DBObject subDocObj) {

			BasicDBObject query = new BasicDBObject(baseDocIdKey, baseDocIdVal);
			BasicDBObject subDoc = new BasicDBObject(subDocKey, subDocObj);
			BasicDBObject updateQuery = new BasicDBObject("$set",subDoc);
			L.debug("saveSubDocument req {{},{}}: ", query, updateQuery);
			WriteResult responseObj = collection.update(query, updateQuery);
			L.debug("saveSubDocument res obj: {} ", responseObj);
			return responseObj.getN();
	}

	public static int saveSubDocument(DBCollection collection, DBObject query, 
			String subDocKey, DBObject subDocObj) {

			//BasicDBObject query = new BasicDBObject(baseDocIdKey, baseDocIdVal);
			BasicDBObject subDoc = new BasicDBObject(subDocKey, subDocObj);
			BasicDBObject updateQuery = new BasicDBObject("$set",subDoc);
			L.debug("saveSubDocument req {{},{}}: ", query, updateQuery);
			WriteResult responseObj = collection.update(query, updateQuery);
			L.debug("saveSubDocument res obj: {} ", responseObj);
			return responseObj.getN();
	}

	
	//db.students.update( { _id: 1, grades: 80 }, { $set: { "grades.$" : 82 } } )	
	public static int updateSubDocument(
			DBCollection collection, 
			String baseDocIdKey,
			long baseDocIdVal, String subDocKey, 
			String subDocUniqueAttribute, 
			String subDocUniqueAttributeVal,
			DBObject subDocObj
			) {

			BasicDBObject query = new BasicDBObject(baseDocIdKey, baseDocIdVal).
						append(subDocKey+"."+subDocUniqueAttribute, subDocUniqueAttributeVal);
			BasicDBObject subDoc = new BasicDBObject(subDocKey+".$", subDocObj);
			BasicDBObject updateQuery = new BasicDBObject("$set",subDoc);
			L.debug("saveSubDocument req {{},{}}: ", query, updateQuery);
			WriteResult responseObj = collection.update(query, updateQuery);
			L.debug("saveSubDocument res obj: {} ", responseObj);
			return responseObj.getN();
	}
	
	//db.students.update( { _id: 1, grades: 80 }, { $set: { "grades.$" : 82 } } )	
	public static int updateSubDocument(
			DBCollection collection, 
			String baseDocIdKey,
			long baseDocIdVal, String subDocKey, 
			String subDocUniqueAttribute, 
			long subDocUniqueAttributeVal,
			DBObject subDocObj
			) {

			BasicDBObject query = new BasicDBObject(baseDocIdKey, baseDocIdVal).
						append(subDocKey+"."+subDocUniqueAttribute, subDocUniqueAttributeVal);
			BasicDBObject subDoc = new BasicDBObject(subDocKey+".$", subDocObj);
			BasicDBObject updateQuery = new BasicDBObject("$set",subDoc);
			L.debug("saveSubDocument req {{},{}}: ", query, updateQuery);
			WriteResult responseObj = collection.update(query, updateQuery);
			L.debug("saveSubDocument res obj: {} ", responseObj);
			return responseObj.getN();
	}
	
	//db.students.update( { _id: 1, grades: 80 }, { $set: { "grades.$" : 82 } } )	
	public static int updateSubDocument(
			DBCollection collection, 
			DBObject query, String subDocKey, 
			String subDocUniqueAttribute, 
			long subDocUniqueAttributeVal,
			DBObject subDocObj
			) {

			BasicDBObject subDoc = new BasicDBObject(subDocKey+".$", subDocObj);
			BasicDBObject updateQuery = new BasicDBObject("$set",subDoc);
			L.debug("saveSubDocument req {{},{}}: ", query, updateQuery);
			WriteResult responseObj = collection.update(query, updateQuery);
			L.debug("saveSubDocument res obj: {} ", responseObj);
			return responseObj.getN();
	}

	//db.students.update( { _id: 1, grades: 80 }, { $set: { "grades.$" : 82 } } )	
	public static DBObject updateAttribute(DBCollection collection,
			String docId, long baseDocIdVal,  String docAttribute,  String docAttributeValue) {

			BasicDBObject query = new BasicDBObject(docId, baseDocIdVal);
			//BasicDBObject subDoc = new BasicDBObject(subDocAttribute, subDocAttributeValue);
			BasicDBObject docAtt = new BasicDBObject(docAttribute, docAttributeValue);
			
			BasicDBObject updateQuery = new BasicDBObject("$set",docAtt);
			L.debug("updateAttribute req {{},{}}: ", query, updateQuery);
			DBObject responseObj = collection.findAndModify(query,
					null, null, false, updateQuery, true, false);
			L.debug("updateAttribute res obj {}: ", responseObj);
			return responseObj;
	}
	
	public static DBObject updateSubDocumentArrayAttribute(DBCollection collection, String baseDocIdKey,
			long baseDocIdVal, String subDocKey,  String subDocAttribute,  String subDocAttributeValue) {

			BasicDBObject query = new BasicDBObject(baseDocIdKey, baseDocIdVal);
			//BasicDBObject subDoc = new BasicDBObject(subDocKey, subDocObj);
			BasicDBObject updateQuery = new BasicDBObject(subDocKey+".$."+subDocAttribute, subDocAttributeValue);
			
			//BasicDBObject updateQuery = new BasicDBObject("$set",subDoc);
			L.debug("updateSubDocumentAttribute req {{},{}}: ", query, updateQuery);
			DBObject responseObj = collection.findAndModify(query,
					null, null, false, updateQuery, true, false);
			L.debug("updateSubDocumentAttribute res obj {}: ", responseObj);
			return responseObj;
	}
	
	public static DBObject updateSubDocumentAttribute(DBCollection collection, String baseDocIdKey,
			long baseDocIdVal, String subDocKey,  String subDocAttribute,  String subDocAttributeValue) {

			BasicDBObject query = new BasicDBObject(baseDocIdKey, baseDocIdVal);
			//BasicDBObject subDoc = new BasicDBObject(subDocAttribute, subDocAttributeValue);
			BasicDBObject subDoc = new BasicDBObject(subDocKey+"."+subDocAttribute, subDocAttributeValue);
			
			BasicDBObject updateQuery = new BasicDBObject("$set",subDoc);
			L.debug("updateSubDocumentAttribute req {{},{}}: ", query, updateQuery);
			DBObject responseObj = collection.findAndModify(query,
					null, null, false, updateQuery, true, false);
			L.debug("updateSubDocumentAttribute res obj {}: ", responseObj);
			return responseObj;
	}

	public static DBObject saveSubDocumentToArray(DBCollection collection, DBObject query,
			String subDocKey, DBObject subDoc) {

			BasicDBObject subDocForSave = new BasicDBObject(subDocKey, subDoc);
			BasicDBObject updateQuery = new BasicDBObject("$addToSet",subDocForSave);
			L.debug("saveSubDocumentToArray req {{},{}}: ", query, updateQuery);
			DBObject responseObj = collection.findAndModify(query,
					null, null, false, updateQuery, true, true);
			L.debug("saveSubDocumentToArray res obj {}: ", responseObj);
			return responseObj;
	}
	
	public static DBObject saveSubDocumentToArray(DBCollection collection, String baseDocIdKey,
			long baseDocIdVal, String subDocKey, DBObject subDoc) {

			BasicDBObject query = new BasicDBObject(baseDocIdKey, baseDocIdVal);
			BasicDBObject subDocForSave = new BasicDBObject(subDocKey, subDoc);
			BasicDBObject updateQuery = new BasicDBObject("$addToSet",subDocForSave);
			L.debug("saveSubDocumentToArray req {{},{}}: ", query, updateQuery);
			DBObject responseObj = collection.findAndModify(query,
					null, null, false, updateQuery, true, true);
			L.debug("saveSubDocumentToArray res obj {}: ", responseObj);
			return responseObj;
	}
	
	// using collection aggregate function
	//
	// db.organization.aggregate(
	// {$match: {"orgId":9887765}},
	// {"$unwind":"$fp"},
	// {$group:
	// {"_id":"$fp.username","count":{$sum:1}}
	// }
	// )
	//
	// Response
	// { "serverUsed" : "/127.0.0.1:27017" , "result" : [ { "_id" : "mahesh" ,
	// "count" : 1} , { "_id" : "bob" , "count" : 2}] , "ok" : 1.0}
	public static Iterator<DBObject> getSubDocument(DBCollection collection, String baseDocIdKey,
			long baseDocIdVal, String subDocName, String subDockey) {
		L.debug("getSubDocument for {}.subDocumentName: {}", collection,
				subDocName);

		BasicDBObject match = new BasicDBObject("$match", new BasicDBObject(
				"orgId", baseDocIdVal));
		BasicDBObject unwind = new BasicDBObject("$unwind",
				("$" + subDocName));
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject(
				"_id", ("$" + subDocName + "." + subDockey)).append("count",
				new BasicDBObject("$sum", 1)));

		L.debug("getSubDocument for {}.subDocumentName: {},{},{}", collection,
				match, unwind, group);
		AggregationOutput ao = collection.aggregate(match, unwind, group);
		Iterable<DBObject> list = ao.results();
		L.debug("getSubDocument aggregation res: {}", ao);
		return list.iterator();
	}

	public static int getSubDocumentCount(Iterator<DBObject> resultIterator,String subDocVal) {

		String keyVal = "";
		int count = 0;
		while (resultIterator.hasNext()) {
			DBObject obj = resultIterator.next();
			keyVal = (String) obj.get("_id");

			if (keyVal.equalsIgnoreCase(subDocVal))
				count = Integer.parseInt(obj.get("count").toString());
		}
		L.debug("getFPCount count {}", count);
		return count;

	}

	public static int getSubDocumentCount(Iterator<DBObject> resultIterator, int subDocVal) {

		int keyVal = 0;
		int count = 0;
		while (resultIterator.hasNext()) {
			DBObject obj = resultIterator.next();
			keyVal = (Integer) obj.get("_id");

			if (keyVal == subDocVal)
				count = (Integer)obj.get("count");
		}
		L.debug("getFPCount count {}", count);
		return count;

	}

	public static int getSubDocumentCount(Iterator<DBObject> resultIterator, long subDocVal) {

		Long keyVal = 0L;
		int count = 0;
		while (resultIterator.hasNext()) {
			DBObject obj = resultIterator.next();
			keyVal = (Long) obj.get("_id");

			if (keyVal == subDocVal)
				count = (Integer)obj.get("count");
		}
		L.debug("getFPCount count {}", count);
		return count;

	}

}
