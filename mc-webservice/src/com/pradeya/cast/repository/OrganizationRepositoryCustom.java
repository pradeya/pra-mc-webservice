package com.pradeya.cast.repository;

import java.util.Iterator;
import java.util.List;

import com.mongodb.DBObject;
import com.pradeya.cast.domain.Detail;
import com.pradeya.cast.domain.Member;
import com.pradeya.cast.domain.Organization;

public interface OrganizationRepositoryCustom {

	public static final String ORGANIZATION = "organization";
	public static final String MEMBER = "member";
	public static final String ORGANIZATION_DETAIL = "detail";
	public static final String ID = "_id";
	public static final String USERNAME = "userName";
	public static final String STATUS = "status";
	public static final String ESTABLISHED = "established";
	public static final String ACTIVE = "active";
	public static final String DOMAIN = "detail.domain";
	


	public boolean createOrganization(Organization org);
	public Organization findOrganization(long baseDocIdVal);
	public long findOrganizationIdByDomain(String domain);
	//Use default saveOrganization
	public int updateOrganization(Organization org);
	public List<Organization> findAllOrg();
	public Organization findOrganizationDetail(long baseDocIdVal);
	public int saveOrganizationDetail(long baseDocIdVal, Detail detail);// Works for update too
	public boolean updateOrganizationDetailAttribute(long baseDocIdVal, String orgDetailAttribute,String orgDetailAttributeValue);
	
	
	public Organization findMember(long baseDocIdVal, String username);
	public Organization createMember(long baseDocIdVal,Member fp);
	public int updateMember(long baseDocIdVal,Member fp);
	public Organization updateMemberAttribute(long baseDocIdVal, String memberAttribute,String memberAttributeValue);
	public Organization updateMemberAttribute(long baseDocIdVal, String memberAttribute,long memberAttributeValue);
	public Organization findAllMember(long baseDocVal);
	
	public Organization findMemberDetail(long baseDocIdVal);
	public int saveMemberDetail(long baseDocIdVal, Detail detail);
	public Organization updateMemberDetail(long baseDocIdVal);
	public Organization updateMemberDetailAttribute(long baseDocIdVal, String memberDetailAttribute,String memberDetailAttributeValue);
	public Organization updateMemberDetailAttribute(long baseDocIdVal, String memberDetailAttribute,long memberDetailAttributeValue);

	
	public Iterator<DBObject> getAllMember(long baseDocIdVal,String subDocKey);
	public int getMemberCount(long baseDocIdVal, String subDocKey, String subDocVal);
	public int getMemberCount(long baseDocIdVal, String subDocKey, int subDocVal);
	public int getMemberCount(long baseDocIdVal, String subDocKey, long subDocVal);
}
