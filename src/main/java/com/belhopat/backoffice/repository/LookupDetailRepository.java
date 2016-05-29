package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.LookupDetail;
/**
 * @author BHP_DEV
 * Data repository for Lookup entity 
 *
 */
@Repository
public interface LookupDetailRepository extends JpaRepository<LookupDetail, Long> {

	@Query("select l from LookupDetail l where l.lookup.lookupKey=:lookupKey ")
	List<LookupDetail> findByLookupKey(@Param("lookupKey") String lookupKey);

}
