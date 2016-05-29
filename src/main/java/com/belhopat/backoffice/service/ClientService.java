package com.belhopat.backoffice.service;

import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.Client;

@Service
public interface ClientService {

	DataTablesOutput < Client > getClients( DataTablesInput input );

	ResponseEntity < Client > getClient( Long clientId );

	ResponseEntity < Map < String, String > > saveOrUpdateClient( Client clientObj );

}
