package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.RoleTab;

/**
 * @author BHP_DEV Data repository for RoleTab entity
 *
 */
@Repository
public interface RoleTabRepository extends JpaRepository<RoleTab, Long> {

	@Query("select r from RoleTab r where r.masterRole.id=:roleId ")
	List<RoleTab> getUserTabsByRole(@Param("roleId") Long roleId);

}
