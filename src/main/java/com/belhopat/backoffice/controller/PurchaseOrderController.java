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
import com.belhopat.backoffice.model.PurchaseOrder;
import com.belhopat.backoffice.model.State;
import com.belhopat.backoffice.model.Vendor;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.PurchaseOrderService;
import com.itextpdf.text.DocumentException;

/**
 * @author Belhopat dev team Serves as a webservice handler class
 *
 */
@Controller
@RequestMapping("/api/purchaseOrder")
public class PurchaseOrderController {

	@Autowired
	BaseService baseService;

	@Autowired
	PurchaseOrderService purchaseOrderService;

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
	 * @return list of Purchase orders for this year
	 * @throws ParseException
	 * @throws IOException
	 * @throws DocumentException
	 * @throws MalformedURLException
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllPurchaseOrders", method = RequestMethod.GET)

	public DataTablesOutput<PurchaseOrder> getAllPurchaseOrders(@Valid DataTablesInput input)
			throws MalformedURLException, DocumentException, IOException, ParseException {
		return purchaseOrderService.getAllPurchaseOrders(input);
	}

	/**
	 * @param requestObject
	 * @return PurchaseOrder For edit PurchaseOrder , gets the id and fetches
	 *         the PurchaseOrder from database
	 * @throws ParseException
	 * @throws IOException
	 * @throws DocumentException
	 * @throws MalformedURLException
	 */
	@ResponseBody
	@RequestMapping(value = "/getPurchaseOrder", method = RequestMethod.POST)
	public ResponseEntity<PurchaseOrder> getPurchaseOrder(@RequestBody RequestObject requestObject) {
		return purchaseOrderService.getPurchaseOrderById(requestObject.getId());
	}

	/**
	 * @param PurchaseOrder
	 * @return responseString To save the PurchaseOrder after edit or add
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdatePurchaseOrder", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> saveOrUpdatePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
		return purchaseOrderService.saveOrUpdatePurchaseOrder(purchaseOrder);
	}

	/**
	 * @param PurchaseOrder
	 * @return responseString To save the PurchaseOrder after edit or add
	 */
	@ResponseBody
	@RequestMapping(value = "/approveOrRejectPurchaseOrder", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> approveOrRejectPurchaseOrder(@RequestBody ResponseObject requestObject) {
		return purchaseOrderService.approveOrRejectPurchaseOrder(requestObject);
	}

	/**
	 * @param requestObject
	 * @return response entity Lookup method fetches states for a country
	 */
	@ResponseBody
	@RequestMapping(value = "/getDropDownData", method = RequestMethod.GET)
	public ResponseEntity<Map<String, List<?>>> getDropDownData() {
		return purchaseOrderService.getDropDownData();
	}

	/**
	 * @param requestObject
	 * @return response entity Lookup method fetches states for a country
	 */
	@ResponseBody
	@RequestMapping(value = "/getVendors", method = RequestMethod.POST)
	public List<Vendor> getVendors(@RequestBody String vendorName) {
		return purchaseOrderService.getVendors(vendorName);
	}
}
