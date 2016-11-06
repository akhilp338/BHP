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
import com.belhopat.backoffice.model.Consultant;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.ConsultantService;

/**
 * @author Belhopat dev team Handler for all consultant related service calls
 *
 */
@Controller
@RequestMapping("/api/consultant")
public class ConsultantController {

	@Autowired
	ConsultantService consultantService;
	
	@Autowired
	BaseService baseService;
	
	/**
	 * @param datatablesinput
	 * @return datatablesoutput<Consultant>
	 * list of consultants fetches the datatable output for consultants.
	 * List all the consultants
	 */
	@ResponseBody
	@RequestMapping(value = "/getConsultants", method = RequestMethod.GET)

	public DataTablesOutput < Consultant > getConsultants( @Valid DataTablesInput input ) {
		return consultantService.getConsultants( input );
	}

	/**
	 * @param requestObject
	 * @return Consultant For edit consultant, gets the id and fetches the
	 *         consultant from database
	 */
	@ResponseBody
	@RequestMapping(value = "/getConsultant", method = RequestMethod.POST)

	public ResponseEntity< Consultant > getConsultant( @RequestBody RequestObject requestObject ) {
		return consultantService.getConsultant( requestObject.getId() );
	}
	
	/**
	 * @param consultant
	 * @return responseString 
	 * To save the consultant after edit or add
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateConsultant", method = RequestMethod.POST)
	public ResponseEntity< Map< String, String >> saveOrUpdateConsultant( @RequestBody Consultant consultant ) {
		return consultantService.saveOrUpdateConsultant( consultant );
	}
	
	/**
	 * @return Map of dropdown data gets a key value pair list of drop down data
	 */
	@ResponseBody
	@RequestMapping(value = "/getDropDownData", method = RequestMethod.POST)

	public ResponseEntity<Map<String, List<?>>> getDropDownData() {
		return baseService.getConsultantDropDownData();
	}

}
