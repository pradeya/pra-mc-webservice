package com.pradeya.cast.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pradeya.cast.domain.Sequence;

public interface SequenceRepository extends MongoRepository<Sequence, Long>, SequenceRepositoryCustom {
	
	

}
