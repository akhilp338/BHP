package com.belhopat.backoffice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.model.BHPBankAccount;
import com.belhopat.backoffice.service.BHPBankAccountService;
import com.belhopat.backoffice.service.BaseService;

/**
 * @author Belhopat dev team Serves as a webservice handler class
 *
 */
@Controller
@RequestMapping("/api/bankAccount")
public class BHPBankAccountController {

	@Autowired
	BaseService baseService;

	@Autowired
	BHPBankAccountService bankAccountService;

	@ResponseBody
	@RequestMapping(value = "/getBankAccounts", method = RequestMethod.GET)
	public DataTablesOutput<BHPBankAccount> getBankAccounts(@Valid DataTablesInput input) {
		return bankAccountService.getBankAccounts(input);
	}
}
