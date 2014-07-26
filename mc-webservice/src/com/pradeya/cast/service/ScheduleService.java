package com.pradeya.cast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeya.cast.domain.Schedule;
import com.pradeya.cast.repository.ScheduleRepository;
import com.pradeya.cast.repository.SequenceRepository;

@Service
public class ScheduleService {
	
	public static final String SCHEDULE = "schedule";
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private SequenceRepository sequenceRepository;
	
	
	public Iterable<Schedule> findAll(){
		return scheduleRepository.findAll();
	}
	
	public Schedule create(Schedule schedule) {
		schedule.setId(sequenceRepository.getNextSequence(SCHEDULE));
		return scheduleRepository.save(schedule);
	}

	public Schedule update(Schedule schedule){
		return scheduleRepository.save(schedule);
	}

	public Schedule findSchedule(long scheduleId){
		return scheduleRepository.findOne(scheduleId);
	}
	
	public Iterable<Schedule> findByPushState(String pushState){
		return scheduleRepository.findByPushState(pushState);
	}
	
	public boolean updatePushState(long scheduleId,String status){
		return scheduleRepository.updatePushState(scheduleId,status);
	}
	
	public Iterable<Schedule> findScheduleForDiaplay(long id) {
		return scheduleRepository.findByDisplayIdAndPushState(id);
		
	}

}
