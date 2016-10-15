package com.belhopat.backoffice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.model.Attendance;
import com.belhopat.backoffice.service.AttendanceService;
import com.belhopat.backoffice.service.BaseService;

/**
 * @author Belhopat dev team Handler for all client related service calls
 *
 */
@Controller
@RequestMapping("/api/attendance")
public class AttendanceController {

	@Autowired
	BaseService baseService;

	@Autowired
	AttendanceService attendanceService;

	/**
	 * @param DataTablesInput
	 * @return List of Clients Used to fetch client details present in the
	 *         database based on input from the datatable. Includes sorting and
	 *         pagination, i.e, returns DataTablesOutput.
	 */
	@ResponseBody
	@RequestMapping(value = "/getAttendances", method = RequestMethod.GET)
	public DataTablesOutput<Attendance> getAttendances(@Valid DataTablesInput input) {
		return attendanceService.getAttendances(input);
	}

}
