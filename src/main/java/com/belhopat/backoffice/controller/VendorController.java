package com.belhopat.backoffice.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
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
import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.model.State;
import com.belhopat.backoffice.model.Vendor;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.VendorService;
import com.itextpdf.text.DocumentException;

/**
 * @author Belhopat dev team Serves as a webservice handler class
 *
 */
@Controller
@RequestMapping("/api/vendor")
public class VendorController {

	@Autowired
	BaseService baseService;
	
	@Autowired
	VendorService vendorService;

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
	 * @param datatablesinput
	 * @return list of approved vendors 
	 * @throws ParseException
	 * @throws IOException
	 * @throws DocumentException
	 * @throws MalformedURLException
	 */
	@ResponseBody
	@RequestMapping(value = "/getApprovedVendors", method = RequestMethod.GET)

	public DataTablesOutput<Vendor> getApprovedVendors(@Valid DataTablesInput input)
			throws MalformedURLException, DocumentException, IOException, ParseException {
		return vendorService.getApprovedVendors(input);
	}
	
	/**
	 * @param requestObject
	 * @return Vendor For edit vendor , gets the id and fetches the
	 *         vendor from database
	 * @throws ParseException
	 * @throws IOException
	 * @throws DocumentException
	 * @throws MalformedURLException
	 */
	@ResponseBody
	@RequestMapping(value = "/getVendor", method = RequestMethod.POST)

	public ResponseEntity<Vendor> getVendor(@RequestBody RequestObject requestObject) {
		return vendorService.getVendorById(requestObject.getId());
	}
	

	/**
	 * @param vendor
	 * @return responseString To save the vendor after edit or add
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateVendor", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> saveOrUpdateVendor(@RequestBody Vendor vendor) {
		return vendorService.saveOrUpdateVendor(vendor);
	}
	
	/**
	 * @param vendor
	 * @return responseString To save the vendor after edit or add
	 */
	@ResponseBody
	@RequestMapping(value = "/approveOrRejectVendor", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> approveOrRejectVendor(@RequestBody ResponseObject requestObject) {
		return vendorService.approveOrRejectVendor(requestObject);
	}
}
