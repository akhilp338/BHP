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
import com.belhopat.backoffice.model.EmployeeSalary;
import com.belhopat.backoffice.model.SalaryGrade;
import com.belhopat.backoffice.model.TaskList;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.CandidateService;
import com.belhopat.backoffice.service.PDFService;
import com.itextpdf.text.DocumentException;

/**
 * @author Belhopat dev team Handler for all candidate related service calls
 *
 */
@Controller
@RequestMapping("/api/candidate")
public class CandidateController {

	@Autowired
	BaseService baseService;

	@Autowired
	CandidateService candidateService;

	@Autowired
	PDFService pdfService;

	/**
	 * @param datatablesinput
	 * @return list of candidates fetches the datatable output for candidates.
	 *         List all the candidates
	 * @throws ParseException
	 * @throws IOException
	 * @throws DocumentException
	 * @throws MalformedURLException
	 */
	@ResponseBody
	@RequestMapping(value = "/getCandidates", method = RequestMethod.GET)

	public DataTablesOutput<Candidate> getCandidates(@Valid DataTablesInput input, @RequestParam boolean employee)
			throws MalformedURLException, DocumentException, IOException, ParseException {
//		Employee employeeDTO = new Employee();
//		pdfService.generateOfferLetterPDF(employeeDTO);
//		SalaryDTO salaryDTO = new SalaryDTO();
		return candidateService.getCandidates(input, employee);
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
		return candidateService.getCandidate(requestObject.getId());
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
		return candidateService.getCandidateView(requestObject.getId());
	}

	/**
	 * @param candidate
	 * @return responseString To save the candidate after edit or add
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateCandidate", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> saveOrUpdateCandidate(@RequestBody Candidate candidate) {
		return candidateService.saveOrUpdateCandidate(candidate);
	}

	/**
	 * @param candidateIds
	 * @return response entity To delete a list of candidates
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCandidates", method = RequestMethod.POST)

	public ResponseEntity<Void> deleteCandidates(@RequestBody List<Long> candidateIds) {
		return candidateService.deleteCandidates(candidateIds);
	}

	/**
	 * @param requestObject
	 * @return responseObject Deletes a single candidate
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCandidate", method = RequestMethod.POST)

	public ResponseEntity<ResponseObject> deleteCandidate(@RequestBody RequestObject requestObject) {
		return candidateService.deleteCandidate(requestObject.getId());
	}

	/**
	 * @return Map of dropdown data gets a key value pair list of drop down data
	 */
	@ResponseBody
	@RequestMapping(value = "/getDropDownData", method = RequestMethod.POST)

	public ResponseEntity<Map<String, List<?>>> getDropDownData() {
		return baseService.getCandidateDropDownData();
	}

	@ResponseBody
	@RequestMapping(value = "/getSalarySplit", method = RequestMethod.GET)
	public ResponseEntity<EmployeeSalary> getSalarySplit(@RequestParam String fixed,@RequestParam String grade) {
		return baseService.getSalarySplit(Double.valueOf(fixed),grade);
	}

	@ResponseBody
	@RequestMapping(value = "/getSalaryGrades", method = RequestMethod.POST)
	public List<SalaryGrade> getSalaryGrades() {
		return candidateService.getSalaryGrades();
	}
	
	@ResponseBody
	@RequestMapping(value = "/requestForApproval", method = RequestMethod.POST)
	public ResponseEntity<EmployeeSalary> requestForApproval(@RequestBody EmployeeSalary employeeSalary) {
		return candidateService.requestForApproval(employeeSalary);
	}

	@ResponseBody
	@RequestMapping(value = "/saveSalaryAndOfferLetter", method = RequestMethod.POST)
	public ResponseEntity<EmployeeSalary> saveSalaryAndOfferLetter(@RequestBody EmployeeSalary employeeSalary) {
		return baseService.saveSalaryAndOfferLetter(employeeSalary);
	}

	@ResponseBody
	@RequestMapping(value = "/getOfferLetters", method = RequestMethod.GET)
	public DataTablesOutput<EmployeeSalary> getEmployee(@Valid DataTablesInput input) {
		return candidateService.getOfferLetters(input);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUnProcessedCandidates", method = RequestMethod.GET)
	public DataTablesOutput<Candidate> getUnProcessedCandidates(@Valid DataTablesInput input, @RequestParam boolean employee) {
		return candidateService.getUnProcessedCandidates(input,employee);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCurrentUserTasks", method = RequestMethod.GET)
	public ResponseEntity<List<TaskList>> getCurrentUserTasks() {
		ResponseEntity<List<TaskList>> tasks= baseService.getCurrentUserTasks();
		return tasks;
	}

}