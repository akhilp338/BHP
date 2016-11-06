package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.VendorSequence;
/**
 * @author BHP_DEV
 * Data repository for Vendor entity 
 *
 */
@Repository
public interface VendorSequenceRepository extends JpaRepository<VendorSequence, Long> {
}
