package com.pradeya.cast.repository;

import org.springframework.data.repository.CrudRepository;

import com.pradeya.cast.domain.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Long>, OrganizationRepositoryCustom{
	

}
