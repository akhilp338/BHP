package com.belhopat.backoffice.service;

import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.model.Vendor;

@Service
public interface VendorService {

	public DataTablesOutput<Vendor> getApprovedVendors(DataTablesInput input);

	public ResponseEntity<Vendor> getVendorById(Long id);

	public ResponseEntity<Map<String, String>> saveOrUpdateVendor(Vendor vendor);

	public ResponseEntity<Map<String, String>> approveOrRejectVendor(ResponseObject requestObject);
	
	
}
