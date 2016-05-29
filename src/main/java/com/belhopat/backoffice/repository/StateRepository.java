package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.State;
/**
 * @author BHP_DEV
 * Data repository for state entity 
 *
 */
@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	@Query("select s from State s where s.country.id =:countryId")
	List<State> findByCountry(@Param("countryId") Long countryId);
}
