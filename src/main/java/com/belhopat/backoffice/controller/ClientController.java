package com.belhopat.backoffice.controller;

import java.util.List;
import java.util.Map;

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

import com.belhopat.backoffice.dto.RequestObject;
import com.belhopat.backoffice.model.Client;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.ClientService;

/**
 * @author Belhopat dev team
 * Handler for all client related service calls
 *
 */
@Controller
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	BaseService baseService;

	@Autowired
	ClientService clientService;
	
	/**
	 * @param DataTablesInput
	 * @return List of Clients
	 * Used to fetch client details present in the database based on input from the datatable. Includes sorting and pagination, i.e, returns DataTablesOutput.
	 */
	@ResponseBody
	@RequestMapping(value = "/getClients", method = RequestMethod.GET)
	public DataTablesOutput < Client > getClients( @Valid DataTablesInput input ) {
		return clientService.getClients(input);
	}

	/**
	 * @param RequestObject containing id of candidate to be fetched from database.
	 * @return Candidate
	 * Fetches details of client with specified id for edit/update purposes.
	 */
	@ResponseBody
	@RequestMapping(value = "/getClient", method = RequestMethod.POST)
	public ResponseEntity < Client > getClient( @RequestBody RequestObject requestObject ) {
		return clientService.getClient( requestObject.getId() );
	}
	
	/**
	 * @param client
	 * @return ResponseEntity
	 * Saves or updates client and returns status message of request.
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateClient", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> saveOrUpdateClient(@RequestBody Client client) {
		return clientService.saveOrUpdateClient(client);
	}
	
	/**
	 * @return Map of dropdown data
	 * gets a key value pair list of drop down data
	 */
	@ResponseBody
	@RequestMapping(value = "/getDropDownData", method = RequestMethod.POST)
	public Map<String, List<?>> getDropDownData() {
		return baseService.getEmployeeDropdowns();
	}

}
