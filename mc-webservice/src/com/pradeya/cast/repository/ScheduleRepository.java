package com.pradeya.cast.repository;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pradeya.cast.domain.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Long>, ScheduleRepositoryCustom{
	public Iterable<Schedule> findByPushState(String pushState);
	
	@Query("{ displayId: ?0, pushState: 'PENDING' }")
	public Iterable<Schedule> findByDisplayIdAndPushState(long displayId);
	
}
