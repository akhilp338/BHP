package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.Lookup;
/**
 * @author BHP_DEV
 * Data repository for Lookup entity 
 *
 */
@Repository
public interface LookupRepository extends JpaRepository<Lookup, Long> {

	Lookup findByLookupKey(String key);
	
}
