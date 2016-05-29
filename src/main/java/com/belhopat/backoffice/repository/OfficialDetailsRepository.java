package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.OfficialCards;

/**
 * @author BHP_DEV
 * Data repository for official cards entity 
 *
 */
@Repository
public interface OfficialDetailsRepository extends JpaRepository<OfficialCards, Long>, DataTablesRepository<OfficialCards, Long> {}
