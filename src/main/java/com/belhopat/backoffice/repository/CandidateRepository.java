package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.Candidate;

/**
 * @author BHP_DEV
 * Data repository for candidate entity 
 *
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>, DataTablesRepository<Candidate, Long> {

	Candidate findById(Long candidateId);

	List<Candidate> findByIdIn(List<Long> candidateIds);

}
