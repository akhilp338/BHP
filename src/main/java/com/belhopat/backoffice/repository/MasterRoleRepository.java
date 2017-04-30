package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.MasterRole;
import com.belhopat.backoffice.model.ModuleTab;

/**
 * @author BHP_DEV Data repository for Attendance entity
 *
 */
@Repository
public interface MasterRoleRepository extends JpaRepository<MasterRole, Long> {

	MasterRole findById(Long roleId);

	@Query("select m.moduleTabs from MasterRole m where m.id=:masterRoleId")
	List<ModuleTab> findModuleTabsByMasterRoleId(@Param(value = "masterRoleId") Long masterRoleId);
}
