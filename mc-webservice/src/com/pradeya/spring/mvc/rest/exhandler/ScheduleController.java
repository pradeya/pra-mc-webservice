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

import com.pradeya.cast.domain.Schedule;
import com.pradeya.cast.service.ScheduleService;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;

	@RequestMapping(method = POST)
	@ResponseBody
	public Schedule createSchedule(@RequestBody final Schedule schedule) {
		return scheduleService.create(schedule);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseBody
	public Schedule updateSchedule(@RequestBody Schedule schedule) {
		return scheduleService.update(schedule);
	}

	@RequestMapping(value = "/{id}", method = GET)
	@ResponseBody
	public Schedule getSchedule(@PathVariable long id) {
		return scheduleService.findSchedule(id);
	}
	
	@RequestMapping(method = GET)
	@ResponseBody
	public Iterable<Schedule> getAllSchedule() {
		return scheduleService.findAll();
	}
	
	@RequestMapping(value = "/display/{id}",method = GET)
	@ResponseBody
	public Iterable<Schedule> findScheduleForDiaplay(@PathVariable long id) {
		return scheduleService.findScheduleForDiaplay(id);
	}
	
	@RequestMapping(value = "/pushstatus/{status}", method = GET)
	@ResponseBody
	public Iterable<Schedule> findByPushState(@PathVariable String status ) {
		return scheduleService.findByPushState(status.toUpperCase());
	}
	
}
