package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.ClientSequence;
/**
 * @author BHP_DEV
 * Data repository for Sequence entity 
 *
 */
@Repository
public interface ClientSequenceRepository extends JpaRepository < ClientSequence, Long> {
}

