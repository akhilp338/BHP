package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.Country;
/**
 * @author BHP_DEV
 * Data repository for Country entity 
 *
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
