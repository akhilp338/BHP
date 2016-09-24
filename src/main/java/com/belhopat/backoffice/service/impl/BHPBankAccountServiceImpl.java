package com.belhopat.backoffice.service.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.model.BHPBankAccount;
import com.belhopat.backoffice.repository.BHPBankAccountRepository;
import com.belhopat.backoffice.service.BHPBankAccountService;
import com.belhopat.backoffice.service.BaseService;

/**
 * @author BHP_DEV Service layer to implement candidates business
 */
@Component
public class BHPBankAccountServiceImpl implements BHPBankAccountService {

	@Autowired
	BaseService baseService;

	@Autowired
	BHPBankAccountRepository bankAccountRepository;

	@Override
	public DataTablesOutput<BHPBankAccount> getBankAccounts(DataTablesInput input) {

		Specification<BHPBankAccount> specification = new Specification<BHPBankAccount>() {
			@Override
			public Predicate toPredicate(Root<BHPBankAccount> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Predicate isNotDeleted = criteriaBuilder.equal(root.get("deleted"), false);
				return criteriaBuilder.and(isNotDeleted);
			}
		};
		DataTablesOutput<BHPBankAccount> dataTablesOutput = bankAccountRepository.findAll(input, specification);
		return dataTablesOutput;
	}

}
