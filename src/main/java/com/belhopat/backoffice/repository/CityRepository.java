package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.City;
/**
 * @author BHP_DEV
 * Data repository for City entity 
 *
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long>, DataTablesRepository<City, Long> {

	@Query("select c from City c where c.state.id =:stateId")
	List<City> findByStateId(@Param("stateId") Long stateId);

}
