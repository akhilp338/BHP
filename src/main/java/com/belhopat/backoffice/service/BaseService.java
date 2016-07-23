package com.belhopat.backoffice.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.RequestObject;
import com.belhopat.backoffice.model.City;
import com.belhopat.backoffice.model.EmployeeSalary;
import com.belhopat.backoffice.model.Skill;
import com.belhopat.backoffice.model.State;
import com.belhopat.backoffice.model.TaskList;
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

	public ResponseEntity<List<TaskList>> createOfferLetter(RequestObject requestObject)
			throws MalformedURLException, DocumentException, IOException, ParseException;

	public ResponseEntity<EmployeeSalary> saveSalaryAndOfferLetter(EmployeeSalary employeeSalary) throws MalformedURLException, DocumentException, IOException, ParseException;

	public ResponseEntity<List<TaskList>> getCurrentUserTasks();

	public ResponseEntity<EmployeeSalary> getSalarySplit(Double grossSalary, String grade);
	
	public List<TaskList> updateTaskList(String taskName) ;
	
	public TaskList createNewTaskList(String taskName);

}
