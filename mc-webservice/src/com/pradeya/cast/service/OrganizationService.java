package com.pradeya.cast.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeya.cast.domain.Member;
import com.pradeya.cast.domain.Organization;
import com.pradeya.cast.repository.OrganizationRepository;
import com.pradeya.spring.mvc.rest.exhandler.UnknownResourceException;
@SuppressWarnings("serial")
@Service
public class OrganizationService implements Serializable{
	
	@Autowired
	private OrganizationRepository organizationRepository;

	public Organization findOne(long id) throws UnknownResourceException{
		Organization organization = organizationRepository.findOrganization(id);
		if(null == organization)
			throw new UnknownResourceException("Unable to find organization with id '" + id + "'");
		else return organization;
	}

	public Organization findOrganizationDetail(long id) throws UnknownResourceException{
		Organization organization = organizationRepository.findOrganizationDetail(id);
		if(null == organization)
			throw new UnknownResourceException("Unable to find organization with id '" + id + "'");
		else return organization;
	}

	public long findOrganizationIdByDomain(String domain) throws UnknownResourceException{
		return organizationRepository.findOrganizationIdByDomain(domain);
	}

	
	public List<Organization> findAll(){
		return organizationRepository.findAllOrg();
	}
	
	public boolean create(Organization organization) {
		return organizationRepository.createOrganization(organization);
	}

	public int update(Organization organization) throws UnknownResourceException{
		return organizationRepository.updateOrganization(organization);
	}

	/*public Organization delete(long id) throws UnknownResourceException{
		Organization organization=findOne(id);
		
		return organizationRepository.delete(organization);
	}*/
	
	//Financial planner related methods...//
	
	public Organization findMember(long id,String userName) throws UnknownResourceException{
		Organization organization = organizationRepository.findMember(id, userName);
		if(null == organization)
			throw new UnknownResourceException("Unable to find financial planner with id '" + id + "'");
		else return organization;
	}

	/*public List<Member> findAllPlanners(long id,String orgName){
		return organizationRepository.getAllMember(id, orgName);
	}
*/
	public Organization createMember(long id,Member fp) {
		return organizationRepository.createMember(id, fp);
	}

	public int updateMember(long id,Member fp) throws UnknownResourceException{
		return organizationRepository.updateMember(id, fp);
		
	}
	public Organization findAllPlanners(long id){
		return organizationRepository.findAllMember(id);
	}

	/*public Planner delete(String id) throws UnknownResourceException{
		Planner existingOrg = fpRepository.findOne(id);
		existingOrg.setStatus("DELETED");
		return fpRepository.save(existingOrg);
	}*/
}
