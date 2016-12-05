package com.belhopat.backoffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.model.PurchaseOrder;
import com.belhopat.backoffice.model.Vendor;

@Service
public interface PurchaseOrderService {

	public DataTablesOutput<PurchaseOrder> getAllPurchaseOrders(DataTablesInput input);

	public ResponseEntity<PurchaseOrder> getPurchaseOrderById(Long id);

	public ResponseEntity<Map<String, String>> saveOrUpdatePurchaseOrder(PurchaseOrder purchaseOrder);

	public ResponseEntity<Map<String, String>> approveOrRejectPurchaseOrder(ResponseObject requestObject);

	public ResponseEntity<Map<String, List<?>>> getDropDownData();

	public List<Vendor> getVendors(String vendorName);

}
