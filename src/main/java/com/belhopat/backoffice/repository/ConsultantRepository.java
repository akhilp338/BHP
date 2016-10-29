package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.Consultant;

/**
 * @author BHP_DEV
 * Data repository for candidate entity 
 *
 */
@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long>, DataTablesRepository<Consultant, Long> {

}
