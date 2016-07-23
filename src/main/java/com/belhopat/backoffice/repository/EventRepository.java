package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.Event;

/**
 * @author BHP_DEV Data repository for event entity
 *
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long>, DataTablesRepository<Event, Long> {

	Event findById(Long eventId);

	List<Event> findByIdIn(List<Long> eventIds);

	@Query("select e from Event e " + "where e.createdBy.id=:userId and e.deleted = false")
	List<Event> getEvents(@Param("userId") Long userId);

}
