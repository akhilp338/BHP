package com.belhopat.backoffice.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.EmploymentInfoDTO;
import com.belhopat.backoffice.dto.OfficialInfoDTO;
import com.belhopat.backoffice.dto.PersonalInfoDTO;
import com.belhopat.backoffice.dto.RequestObject;
import com.belhopat.backoffice.model.Candidate;
import com.belhopat.backoffice.model.City;
import com.belhopat.backoffice.model.Currency;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.EmployeeSalary;
import com.belhopat.backoffice.model.Skill;
import com.belhopat.backoffice.model.State;
import com.belhopat.backoffice.model.Task;
import com.belhopat.backoffice.model.User;
import com.itextpdf.text.DocumentException;

@Service
public interface BaseService {

	public ResponseEntity<Map<String, List<?>>> getCandidateDropDownData();

	public ResponseEntity<List<State>> getStatesByCountry(Long countryId);

	public ResponseEntity<List<City>> getCitiesByState(Long stateId);

	public <T> Long getSequenceIncrement(Class<T> clazz);

	public List<Skill> getUnselectedSkillSet(List<Skill> selectedSkillSet);

	ResponseEntity<Map<String, List<?>>> getEmployeeDropDownData();

	public Map<String, List<?>> getEmployeeDropdowns();

	public ResponseEntity<List<Task>> createOfferLetter(RequestObject requestObject)
			throws MalformedURLException, DocumentException, IOException, ParseException;

	public ResponseEntity<EmployeeSalary> saveSalaryAndOfferLetter(EmployeeSalary employeeSalary)
			throws MalformedURLException, DocumentException, IOException, ParseException;

	public ResponseEntity<List<Task>> getCurrentUserTasks();

	public ResponseEntity<EmployeeSalary> getSalarySplit(Double grossSalary, String grade);

	public List<Task> updateTaskList(String taskName);

	public Task createNewTaskList(String taskName);

	public void getFileByNameAndCategory(Long empSalId, HttpServletResponse response) throws IOException;

	public void previewOfferLetter(Long empSalId, HttpServletResponse response)
			throws IOException, DocumentException, ParseException;

	public DataTablesOutput<Task> getUserTasks(DataTablesInput input);

	public OfficialInfoDTO getOfficialInfo(Candidate candidate) throws ParseException;

	public EmploymentInfoDTO getEmploymentInfo(Candidate candidate) throws ParseException;

	public PersonalInfoDTO getPersonalInfo(Candidate candidate) throws ParseException;

	public ResponseEntity<List<Currency>> getCurrencies();

	public Map<String, Long> getEmployeeIdAndCodeMap();

	public User getCurrentUser();

	public void saveImageIntoUser() throws IOException;

	public Employee getloggedInEmployee();
}
