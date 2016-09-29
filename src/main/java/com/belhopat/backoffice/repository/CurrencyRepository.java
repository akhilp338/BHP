package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.Currency;

/**
 * @author BHP_DEV Data repository for employee entity
 *
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	Currency findById(Long currencyId);

}
