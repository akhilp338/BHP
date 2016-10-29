package com.belhopat.backoffice.service;

import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.Consultant;

@Service
public interface ConsultantService {

	DataTablesOutput< Consultant > getConsultants( DataTablesInput input );

	ResponseEntity< Consultant > getConsultant( Long id );

	ResponseEntity< Map< String, String >> saveOrUpdateConsultant( Consultant consultant );

}
