package com.belhopat.backoffice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.model.OfficialCards;
import com.belhopat.backoffice.repository.OfficialDetailsRepository;
import com.belhopat.backoffice.service.OfficialDetailsService;

/**
 * @author BHP_DEV Handles candidate office details
 */
@Component
public class OfficialDetailsServiceImpl implements OfficialDetailsService {

	@Autowired
	OfficialDetailsRepository officialDetailsRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.OfficialDetailsService#getOfficialDetails
	 * (org.springframework.data.jpa.datatables.mapping.DataTablesInput)
	 * gets office details from repo
	 */
	@Override
	public DataTablesOutput<OfficialCards> getOfficialDetails(DataTablesInput input) {
		DataTablesOutput<OfficialCards> dataTablesOutput = officialDetailsRepository.findAll(input);
		return dataTablesOutput;
	}

}
