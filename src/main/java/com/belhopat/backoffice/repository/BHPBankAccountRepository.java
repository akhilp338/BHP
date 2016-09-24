package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.BHPBankAccount;

/**
 * @author BHP_DEV Data repository for BHPBankAccount entity
 *
 */
@Repository
public interface BHPBankAccountRepository
		extends JpaRepository<BHPBankAccount, Long>, DataTablesRepository<BHPBankAccount, Long> {

	BHPBankAccount findById(Long bankAccountId);

	List<BHPBankAccount> findByIdIn(List<Long> bankAccountIds);

}
