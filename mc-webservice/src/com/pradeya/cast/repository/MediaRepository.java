package com.pradeya.cast.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pradeya.cast.domain.Media;

public interface MediaRepository extends CrudRepository<Media, Long>, MediaRepositoryCustom{
	
	@Query("{ userName: ?0 }")
	public Iterable<Media> findByUserNameSortCDate(String userName,Sort sort);
	
}
