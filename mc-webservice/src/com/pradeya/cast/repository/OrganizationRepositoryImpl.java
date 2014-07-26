package com.pradeya.cast.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.pradeya.cast.domain.Detail;
import com.pradeya.cast.domain.Member;
import com.pradeya.cast.domain.Organization;

public class OrganizationRepositoryImpl implements OrganizationRepositoryCustom {

	private static Logger L = LoggerFactory
			.getLogger(OrganizationRepositoryImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MappingMongoConverter converter;
	
	@Autowired
	SequenceRepository sequenceRepository;
	
	@Override
	public boolean createOrganization(Organization org){
		
		org.setId(sequenceRepository.getNextSequence(ORGANIZATION));
		org.getDetail().setStatus(ACTIVE);
		//TODO remove the hard coding
		org.getDetail().setEstablished("12 WED 2012 10 AM");
		
		BasicDBObject subDocForSave = (BasicDBObject) converter
				.convertToMongoType(org);
		L.debug("createOrganization req {{}}: ", subDocForSave);
		WriteResult responseObj = getCollection().save(subDocForSave);
		L.debug("createOrganization res {{}}: ", responseObj);
		return responseObj.getN()>0 ? true:false;
	}

	public Organization findOrganization(long baseDocIdVal) {
		BasicDBObject query = new BasicDBObject(ID, baseDocIdVal);
		BasicDBObject fields = new BasicDBObject(ORGANIZATION_DETAIL, 1).append("ID", 1).append(MEMBER, 1);
		L.debug("findOrganization req {{},{}}: ", query, fields);

		DBCursor cursor = getCollection().find(query, fields);
		L.debug("findOrganization res total count: {} ", cursor.count());
		DBObject obj = cursor.next();
		L.debug("findOrganization res obj: {} ", obj);
		Organization org = converter.read(Organization.class, obj);
		return org;
	}
	
	public long findOrganizationIdByDomain(String domain) {
		BasicDBObject query = new BasicDBObject(DOMAIN, domain);
		BasicDBObject fields = new BasicDBObject("ID", 1);
		L.debug("findOrganizationIdByDomain req {{},{}}: ", query, fields);

		DBCursor cursor = getCollection().find(query, fields);
		L.debug("findOrganizationIdByDomain res total count: {} ", cursor.count());
		if(cursor.count()==0) return 0;
			
		DBObject obj = cursor.next();
		L.debug("findOrganizationIdByDomain res obj: {} ", obj);
		Organization org = converter.read(Organization.class, obj);
		return org.getId();
	}
	
	public Organization findOrganizationDetail(long baseDocIdVal) {
		BasicDBObject query = new BasicDBObject(ID, baseDocIdVal);
		BasicDBObject fields = new BasicDBObject("ID", 1).append(ORGANIZATION_DETAIL, 1);
		L.debug("findOrganizationDetail req {{},{}}: ", query, fields);

		DBCursor cursor = getCollection().find(query, fields);
		L.debug("findOrganizationDetail res total count: {} ", cursor.count());
		DBObject obj = cursor.next();
		L.debug("findOrganizationDetail res obj: {} ", obj);
		Organization org = converter.read(Organization.class, obj);
		return org;
	}
	

	public List<Organization> findAllOrg(){
		List<Organization> org=new ArrayList<Organization>();
		L.debug("findAllOrg req ");
		DBCursor cursor=getCollection().find();
		while(cursor.hasNext()){
			DBObject obj=cursor.next();
			Organization organ=converter.read(Organization.class,obj);
			org.add(organ);
		}
		L.debug("findAllOrg req success");
		return org;
	}
	
	public Organization findAllMember(long id) {
		BasicDBObject query = new BasicDBObject(ID, id);
		BasicDBObject fields = new BasicDBObject(MEMBER, 1).
				append(ID, 1).append(ORGANIZATION_DETAIL, 1);

		DBCursor cursor = getCollection().find(query, fields);
		L.debug("findAllFinancialPlanner res total count: {} ", cursor.count());
		DBObject obj = cursor.next();
		L.debug("findAllFinancialPlanner res obj: {} ", obj);
		return converter.read(Organization.class, obj);
	}
	
	public int updateOrganization(Organization org) {
		return updateOrganizationDetail(org.getId(), org.getDetail());
	}


	public Organization createMember(long baseDocIdVal,
			Member fp) {
		fp.setId(sequenceRepository.getNextSequence(MEMBER));
		BasicDBObject subDocForSave = (BasicDBObject) converter
				.convertToMongoType(fp);
		L.debug("createFinancialPlanner req {{}}: ", subDocForSave);
		DBObject responseObj = MongoDBUtil.saveSubDocumentToArray(
				getCollection(), ID, baseDocIdVal, MEMBER,
				subDocForSave);
		L.debug("createFinancialPlanner res obj {}", responseObj);
		return converter.read(Organization.class, responseObj);
	}
	
	
	// db.org.find( {"fp.id":"monappa"},{ "_id":"1","_id":1,"fp.$": 1
	// }).pretty()
	public Organization findMember(long baseDocIdVal, String username) {
		BasicDBObject query = new BasicDBObject(ID, baseDocIdVal).append(
				MEMBER + "." + USERNAME, username);
		BasicDBObject fields = new BasicDBObject(MEMBER + ".$", 1).append(ORGANIZATION_DETAIL, 1);
		L.debug("findFinancialPlanner req {{},{}}: ", query);

		DBCursor cursor = getCollection().find(query, fields);
		L.debug("findFinancialPlanner res total count: {} ", cursor.count());
		DBObject obj = cursor.next();
		L.debug("findFinancialPlanner res obj: {} ", obj);
		return converter.read(Organization.class, obj);
	}
	

	@Override
	public int updateMember(long baseDocIdVal,
			Member fp) {
		DBObject subDocForUpdate = (BasicDBObject) converter
				.convertToMongoType(fp);
		L.debug("updateFinancialPlanner req ");
		return MongoDBUtil.updateSubDocument(
				getCollection(), 
				ID, baseDocIdVal,
				MEMBER,USERNAME,
				fp.getUserName(),subDocForUpdate);
	}
	


	/**......................Backup Methods.............not used..................*/
	public int saveOrganizationDetail(long baseDocIdVal, Detail detail) {
		BasicDBObject subDocForSave = (BasicDBObject) converter
				.convertToMongoType(detail);
		return MongoDBUtil.saveSubDocument(getCollection(), ID,
				baseDocIdVal, ORGANIZATION_DETAIL, subDocForSave);
	}

	public int updateOrganizationDetail(long baseDocIdVal, Detail detail) {
		
		return saveOrganizationDetail(baseDocIdVal, detail);	}
	
	@Override
	public boolean updateOrganizationDetailAttribute(long baseDocIdVal,
			String orgDetailAttribute, String orgDetailAttributeValue) {
		// TODO Auto-generated method stub

		DBObject responseObj = MongoDBUtil.updateSubDocumentAttribute(
				getCollection(), ID, baseDocIdVal, ORGANIZATION_DETAIL,
				orgDetailAttribute, orgDetailAttributeValue);

		if (((DBObject) responseObj.get(ORGANIZATION_DETAIL))
				.containsField(orgDetailAttribute)) {
			return true;
		}

		return false;
	}





	public Iterator<DBObject> getAllMember(long baseDocIdVal,
			String subDocKey) {
		return MongoDBUtil.getSubDocument(getCollection(), ID, baseDocIdVal,
				MEMBER, subDocKey);
	}


	private DBCollection getCollection() {
		return mongoTemplate.getCollection(ORGANIZATION);
	}

	@Override
	public Organization updateMemberAttribute(long baseDocIdVal,
			String memberAttribute, String memberAttributeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organization updateMemberAttribute(long baseDocIdVal,
			String memberAttribute, long memberAttributeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organization findMemberDetail(long baseDocIdVal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveMemberDetail(long baseDocIdVal, Detail detail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Organization updateMemberDetail(long baseDocIdVal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organization updateMemberDetailAttribute(long baseDocIdVal,
			String memberDetailAttribute, String memberDetailAttributeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organization updateMemberDetailAttribute(long baseDocIdVal,
			String memberDetailAttribute, long memberDetailAttributeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMemberCount(long baseDocIdVal, String subDocKey,
			String subDocVal) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMemberCount(long baseDocIdVal, String subDocKey, int subDocVal) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMemberCount(long baseDocIdVal, String subDocKey,
			long subDocVal) {
		// TODO Auto-generated method stub
		return 0;
	}


}
