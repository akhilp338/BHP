package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.Attendance;

/**
 * @author BHP_DEV Data repository for Attendance entity
 *
 */
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>, DataTablesRepository<Attendance, Long> {

	Attendance findById(Long attendanceId);

	List<Attendance> findByIdIn(List<Long> attendanceIds);

}
