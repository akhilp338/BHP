package com.belhopat.backoffice.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.DashboardCount;
import com.belhopat.backoffice.dto.TaskDTO;
import com.belhopat.backoffice.model.Task;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.ReimburseService;
import com.belhopat.backoffice.service.TaskService;
import com.itextpdf.text.DocumentException;

/**
 * @author Belhopat dev team Serves as a webservice handler class
 *
 */
@Controller
@RequestMapping("/api/dashboard")
public class DashboardController {

	@Autowired
	BaseService baseService;

	@Autowired
	TaskService taskService;

	@Autowired
	ReimburseService reimburseService;

	@ResponseBody
	@RequestMapping(value = "/approveOrRejectReimburse", method = RequestMethod.POST)
	public ResponseEntity<String> approveOrRejectReimburseTask(@RequestBody TaskDTO task) {
		return reimburseService.approveOrRejectReimburseTask(task);
	}

	@ResponseBody
	@RequestMapping(value = "/getDashboardTasks", method = RequestMethod.GET)
	public DataTablesOutput<Task> getDashboardTasks(@Valid DataTablesInput input)
			throws MalformedURLException, DocumentException, IOException, ParseException {
		return taskService.getDashboardTasks(input);
	}

	@ResponseBody
	@RequestMapping(value = "/getDashboardCount", method = RequestMethod.GET)
	public DashboardCount getDashboardCount() {
		return taskService.getDashboardCount();
	}

}
