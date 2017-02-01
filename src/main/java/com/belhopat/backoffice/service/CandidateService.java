package com.belhopat.backoffice.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.CandidateViewDTO;
import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.dto.UploadDTO;
import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.model.Candidate;
import com.belhopat.backoffice.model.EmployeeSalary;
import com.belhopat.backoffice.model.SalaryGrade;

@Service
public interface CandidateService {

	public DataTablesOutput<Candidate> getCandidates(DataTablesInput input, boolean employee);

	public ResponseEntity<Candidate> getCandidate(Long candidateId);

	public ResponseEntity<Map<String, String>> saveOrUpdateCandidate(Candidate candidate);

	public ResponseEntity<Void> deleteCandidates(List<Long> candidateIds);

	public ResponseEntity<ResponseObject> deleteCandidate(Long candidateId);

	public ResponseEntity<CandidateViewDTO> getCandidateView(Long id) throws ParseException;

	public List<SalaryGrade> getSalaryGrades();

	public DataTablesOutput<EmployeeSalary> getOfferLetters(DataTablesInput input);

	public DataTablesOutput<Candidate> getUnProcessedCandidates(DataTablesInput input, boolean employee);

	public ResponseEntity<EmployeeSalary> requestForApproval(EmployeeSalary employeeSalary);

	public UploadResponse uploadFile(UploadDTO uploadDTO) throws IOException, Exception;

}
