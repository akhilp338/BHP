package com.belhopat.backoffice.service;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.BHPBankAccount;

@Service
public interface BHPBankAccountService {

	public DataTablesOutput<BHPBankAccount> getBankAccounts(DataTablesInput input);
}
