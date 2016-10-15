package com.belhopat.backoffice.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.RequestObject;
import com.belhopat.backoffice.model.City;
import com.belhopat.backoffice.model.Currency;
import com.belhopat.backoffice.model.State;
import com.belhopat.backoffice.model.Task;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.session.SessionManager;
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
	public ResponseEntity<List<Task>> createOfferLetter(@RequestBody RequestObject requestObject)
			throws MalformedURLException, DocumentException, IOException, ParseException {
		return baseService.createOfferLetter(requestObject);
	}

	/**
	 * Method to download document from cloud
	 * 
	 * @param requestObject
	 * @return responseEntity Returns the document from cloud
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloadDocument", method = RequestMethod.GET)
	@ResponseBody
	public void downloadDocument(@RequestParam Long empSalId, HttpServletResponse response) throws IOException {
		baseService.getFileByNameAndCategory(empSalId, response);
	}

	/**
	 * Method to preview document from
	 * 
	 * @param requestObject
	 * @return responseEntity Returns the document from cloud
	 * @throws IOException
	 * @throws ParseException
	 * @throws DocumentException
	 */
	@RequestMapping(value = "/previewOfferLetter", method = RequestMethod.GET)
	@ResponseBody
	public void previewOfferLetter(@RequestParam Long empSalId, HttpServletResponse response)
			throws IOException, DocumentException, ParseException {
		baseService.previewOfferLetter(empSalId, response);
	}

	@ResponseBody
	@RequestMapping(value = "/getUserTasks", method = RequestMethod.GET)
	public DataTablesOutput<Task> getUserTasks(@Valid DataTablesInput input)
			throws MalformedURLException, DocumentException, IOException, ParseException {
		return baseService.getUserTasks(input);
	}

	@ResponseBody
	@RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
	public User getUserName() {
		User currentUser = SessionManager.getCurrentUserAsEntity();
		return currentUser;
	}

	@ResponseBody
	@RequestMapping(value = "/getCurrencies", method = RequestMethod.GET)
	public ResponseEntity<List<Currency>> getCurrencies() {
		ResponseEntity<List<Currency>> currencies = baseService.getCurrencies();
		return currencies;
	}

}
