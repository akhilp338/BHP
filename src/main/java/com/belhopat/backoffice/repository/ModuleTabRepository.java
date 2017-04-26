package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.ModuleTab;

/**
 * @author BHP_DEV Data repository for RoleTab entity
 *
 */
@Repository
public interface ModuleTabRepository extends JpaRepository<ModuleTab, Long> {

	public List<ModuleTab> findByIds(List<Long> activeTabIds);
}
