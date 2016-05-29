package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.Skill;
/**
 * @author BHP_DEV
 * Data repository for skills entity 
 *
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

}
