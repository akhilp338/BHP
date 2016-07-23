package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.model.Client;

@Repository
public interface ClientRepository extends JpaRepository< Client, Long >, DataTablesRepository < Client, Long > {
	
	@Query("select c.id as id ,c.clientId as data from Client c")
	List<ResponseObject> getClientDropdownData();
}
