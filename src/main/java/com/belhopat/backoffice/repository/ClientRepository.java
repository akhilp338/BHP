package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.Client;

@Repository
public interface ClientRepository extends JpaRepository< Client, Long >, DataTablesRepository < Client, Long > {
}
