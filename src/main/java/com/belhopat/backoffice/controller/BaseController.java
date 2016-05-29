package com.belhopat.backoffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.RequestObject;
import com.belhopat.backoffice.dto.SalaryDTO;
import com.belhopat.backoffice.model.City;
import com.belhopat.backoffice.model.State;
import com.belhopat.backoffice.model.TaskList;
import com.belhopat.backoffice.service.BaseService;

/**
 * @author Belhopat dev team
 * Serves as a webservice handler class
 *
 */
@Controller
@RequestMapping("/api")
public class BaseController {

	@Autowired
	BaseService baseService;

	/**
	 * @param requestObject
	 * @return response entity
	 * Lookup method fetches states for a country
	 */
	@ResponseBody
	@RequestMapping(value = "/getStatesByCountry", method = RequestMethod.POST)
		public ResponseEntity<List<State>> getStatesByCountry(@RequestBody RequestObject requestObject) {
		return baseService.getStatesByCountry(requestObject.getId());
	}

	/**
	 * @param requestObject
	 * @return responseEntity
	 * Returns the list of cities for a state
	 */
	@ResponseBody
	@RequestMapping(value = "/getCitiesByState", method = RequestMethod.POST)
	public ResponseEntity<List<City>> getCitiesByState(@RequestBody RequestObject requestObject) {
		return baseService.getCitiesByState(requestObject.getId());
	}
	
	/**
	 * @param requestObject
	 * @return responseEntity
	 * Returns the list of cities for a state
	 */
	@ResponseBody
	@RequestMapping(value = "/createOfferLetter", method = RequestMethod.POST)
	public ResponseEntity<List<TaskList>> createOfferLetter(@RequestBody RequestObject requestObject) {
		return baseService.createOfferLetter(requestObject);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSalarySplit", method = RequestMethod.POST)
	public ResponseEntity<List<TaskList>> getSalarySplit(@RequestBody SalaryDTO salaryDTO) {
		return baseService.getSalarySplit(salaryDTO);
	}

}
