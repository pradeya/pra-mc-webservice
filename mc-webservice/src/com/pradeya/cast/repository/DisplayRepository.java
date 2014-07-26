package com.pradeya.cast.repository;

import org.springframework.data.repository.CrudRepository;
import com.pradeya.cast.domain.Display;

public interface DisplayRepository extends CrudRepository<Display, Long>{
	//public Iterable<Schedule> findByPushState(String pushState);
	
}
