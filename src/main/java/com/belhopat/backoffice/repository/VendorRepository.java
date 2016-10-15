package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.Vendor;

/**
 * @author BHP_DEV
 * Data repository for candidate entity 
 *
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>, DataTablesRepository<Vendor, Long> {

	Vendor findById(Long vendorId);

	List<Vendor> findByIdIn(List<Long> vendorIds);

}
