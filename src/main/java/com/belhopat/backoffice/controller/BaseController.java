package com.belhopat.backoffice.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.RequestObject;
import com.belhopat.backoffice.model.City;
import com.belhopat.backoffice.model.State;
import com.belhopat.backoffice.model.TaskList;
import com.belhopat.backoffice.service.BaseService;
import com.itextpdf.text.DocumentException;

/**
 * @author Belhopat dev team Serves as a webservice handler class
 *
 */
@Controller
@RequestMapping("/api")
public class BaseController {

	@Autowired
	BaseService baseService;

	/**
	 * @param requestObject
	 * @return response entity Lookup method fetches states for a country
	 */
	@ResponseBody
	@RequestMapping(value = "/getStatesByCountry", method = RequestMethod.POST)
	public ResponseEntity<List<State>> getStatesByCountry(@RequestBody RequestObject requestObject) {
		return baseService.getStatesByCountry(requestObject.getId());
	}

	/**
	 * @param requestObject
	 * @return responseEntity Returns the list of cities for a state
	 */
	@ResponseBody
	@RequestMapping(value = "/getCitiesByState", method = RequestMethod.POST)
	public ResponseEntity<List<City>> getCitiesByState(@RequestBody RequestObject requestObject) {
		return baseService.getCitiesByState(requestObject.getId());
	}

	/**
	 * @param requestObject
	 * @return responseEntity Returns the list of cities for a state
	 * @throws ParseException
	 * @throws IOException
	 * @throws DocumentException
	 * @throws MalformedURLException
	 */
	@ResponseBody
	@RequestMapping(value = "/createOfferLetter", method = RequestMethod.POST)
	public ResponseEntity<List<TaskList>> createOfferLetter(@RequestBody RequestObject requestObject)
			throws MalformedURLException, DocumentException, IOException, ParseException {
		return baseService.createOfferLetter(requestObject);
	}
	
	
	/**
	 * Method to download document from cloud
	 * @param requestObject
	 * @return responseEntity Returns the document from cloud
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloadDocument", method = RequestMethod.GET)
	@ResponseBody
	public void downloadDocument(@RequestParam Long empSalId, HttpServletResponse response) throws IOException {
		baseService.getFileByNameAndCategory(empSalId,response);
	}
	 
}
