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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.CandidateViewDTO;
import com.belhopat.backoffice.dto.RequestObject;
import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.model.Candidate;
import com.belhopat.backoffice.model.Reimburse;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.ReimburseService;
import com.itextpdf.text.DocumentException;

/**
 * @author Belhopat dev team Serves as a webservice handler class
 *
 */
@Controller
@RequestMapping("/api/reimburse")
public class ReimburseController {

	@Autowired
	BaseService baseService;

	@Autowired
	ReimburseService reimburseService;

	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateReimburse", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> saveOrUpdateCandidate(@RequestBody Reimburse reimburse) {
		return reimburseService.saveOrUpdateReimburse(reimburse);
	}

	@ResponseBody
	@RequestMapping(value = "/getCandidates", method = RequestMethod.GET)
	public DataTablesOutput<Candidate> getCandidates(@Valid DataTablesInput input, @RequestParam boolean employee)
			throws MalformedURLException, DocumentException, IOException, ParseException {
		return reimburseService.getCandidates(input, employee);
	}

	/**
	 * @param requestObject
	 * @return Candidate For edit candidate , gets the id and fetches the
	 *         candidate from database
	 * @throws ParseException
	 * @throws IOException
	 * @throws DocumentException
	 * @throws MalformedURLException
	 */
	@ResponseBody
	@RequestMapping(value = "/getCandidate", method = RequestMethod.POST)

	public ResponseEntity<Candidate> getCandidate(@RequestBody RequestObject requestObject) {
		return reimburseService.getCandidate(requestObject.getId());
	}

	/**
	 * @param requestObject
	 * @return Candidate For edit candidate , gets the id and fetches the
	 *         candidate from database
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getCandidateView", method = RequestMethod.POST)

	public ResponseEntity<CandidateViewDTO> getCandidateView(@RequestBody RequestObject requestObject)
			throws ParseException {
		return reimburseService.getCandidateView(requestObject.getId());
	}

	/**
	 * @param candidateIds
	 * @return response entity To delete a list of candidates
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCandidates", method = RequestMethod.POST)

	public ResponseEntity<Void> deleteCandidates(@RequestBody List<Long> candidateIds) {
		return reimburseService.deleteCandidates(candidateIds);
	}

	/**
	 * @param requestObject
	 * @return responseObject Deletes a single candidate
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCandidate", method = RequestMethod.POST)

	public ResponseEntity<ResponseObject> deleteCandidate(@RequestBody RequestObject requestObject) {
		return reimburseService.deleteCandidate(requestObject.getId());
	}

	/**
	 * @return Map of dropdown data gets a key value pair list of drop down data
	 */
	@ResponseBody
	@RequestMapping(value = "/getDropDownData", method = RequestMethod.POST)

	public ResponseEntity<Map<String, List<?>>> getDropDownData() {
		return baseService.getCandidateDropDownData();
	}

}
