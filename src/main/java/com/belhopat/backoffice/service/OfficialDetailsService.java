package com.belhopat.backoffice.service;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.OfficialCards;

@Service
public interface OfficialDetailsService {

	DataTablesOutput<OfficialCards> getOfficialDetails(DataTablesInput input);

}
