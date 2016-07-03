package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belhopat.backoffice.model.TimeZone;

/**
 * @author BHP_DEV Data repository for timezone entity
 *
 */
public interface TimeZoneRepository extends JpaRepository< TimeZone, Long >{

}
