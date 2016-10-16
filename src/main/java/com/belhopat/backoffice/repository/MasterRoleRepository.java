package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.MasterRole;

/**
 * @author BHP_DEV Data repository for Attendance entity
 *
 */
@Repository
public interface MasterRoleRepository extends JpaRepository<MasterRole, Long> {

	MasterRole findById(Long roleId);
}
