package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.ConsultantSequence;
/**
 * @author BHP_DEV
 * Data repository for Sequence entity 
 *
 */
@Repository
public interface ConsultantSequenceRepository extends JpaRepository<ConsultantSequence, Long> {
}
