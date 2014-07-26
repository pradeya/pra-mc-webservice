package com.pradeya.spring.mvc.rest.exhandler;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pradeya.cast.domain.Member;
import com.pradeya.cast.domain.Organization;
import com.pradeya.cast.service.OrganizationService;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;

	@RequestMapping(method = POST)
	@ResponseBody
	public boolean createOrg(@RequestBody final Organization organization) {
		return organizationService.create(organization);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseBody
	public int updateOrg(@RequestBody Organization organization) {
		return organizationService.update(organization);
	}

	@RequestMapping(value = "/{id}", method = GET)
	@ResponseBody
	public Organization getOrg(@PathVariable long id) {
		return organizationService.findOne(id);
	}

	/*@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseBody
	public Organization deleteOrg(@PathVariable String id) {
		return organizationService.delete(id);
	}*/

	@RequestMapping(method = GET)
	@ResponseBody
	public Iterable<Organization> getALLOrg() {
		return organizationService.findAll();
	}
	
	@RequestMapping(value = "/domain/{domain}", method = GET)
	@ResponseBody
	public long getOrgIdForDomain(@PathVariable String domain) {
		return organizationService.findOrganizationIdByDomain(domain);
	}
	
	@RequestMapping(value = "/detail/{id}", method = GET)
	@ResponseBody
	public Organization getOrgIdDetail(@PathVariable long id) {
		return organizationService.findOrganizationDetail(id);
	}
	//Financial Planner updations...//
	
	@RequestMapping(value="/{id}/member",method = POST)
	@ResponseBody
	public  Organization createPlanner(@PathVariable long id, @RequestBody final Member fp) {
		return organizationService.createMember(id, fp);
	}

	
	@RequestMapping(value="/{id}/member",method = GET)
	@ResponseBody
	public  Organization findAllPlanners(@PathVariable long id) {
		return organizationService.findAllPlanners(id);
	}
	@RequestMapping(value = "/{id}/member/{userName}", method = GET)
	@ResponseBody
	public  Organization getPlanner(@PathVariable long id,@PathVariable String userName) {
		return organizationService.findMember(id, userName);
	}

	@RequestMapping(value = "/{id}/member/{userName}", method = PUT)
	@ResponseBody
	public int updatePlanner(@PathVariable long id, @RequestBody Member fp,
			@PathVariable String userName) {
		return organizationService.updateMember(id, fp);
	}
	
	/*@RequestMapping(value="/{id}/member/{orgName}",method = GET)
	@ResponseBody
	public Iterator<DBObject> getALLPlanner(@PathVariable long id,@PathVariable String orgName) {
		return organizationService.findAllPlanners(id, orgName);
	}*/

	/*@RequestMapping(value = "planner/{userName}", method = DELETE)
	@ResponseBody
	public Member deletePlanner(@PathVariable String id) {
		return organizationService.delete(id);
	}
*/

}


