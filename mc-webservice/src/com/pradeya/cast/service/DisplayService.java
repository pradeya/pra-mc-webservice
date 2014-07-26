package com.pradeya.cast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeya.cast.domain.Display;
import com.pradeya.cast.repository.DisplayRepository;
import com.pradeya.cast.repository.SequenceRepository;

@Service
public class DisplayService {
	
	public static final String DISPLAY = "display";
	
	@Autowired
	private DisplayRepository displayRepository;
	
	@Autowired
	private SequenceRepository sequenceRepository;
	
	
	public Iterable<Display> findAll(){
		return displayRepository.findAll();
	}
	
	public Display create(Display display) {
		display.setId(sequenceRepository.getNextSequence(DISPLAY));
		return displayRepository.save(display);
	}

	public Display update(Display display){
		return displayRepository.save(display);
	}

	public Display findDisplay(long displayId){
		return displayRepository.findOne(displayId);
	}
}
