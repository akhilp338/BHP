package com.belhopat.backoffice.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.model.Currency;
import com.belhopat.backoffice.model.LookupDetail;
import com.belhopat.backoffice.model.PurchaseOrder;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.model.Vendor;
import com.belhopat.backoffice.repository.CurrencyRepository;
import com.belhopat.backoffice.repository.LookupDetailRepository;
import com.belhopat.backoffice.repository.PurchaseOrderRepository;
import com.belhopat.backoffice.repository.VendorRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.MailService;
import com.belhopat.backoffice.service.PurchaseOrderService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.Constants;
import com.belhopat.backoffice.util.TaskConstants;

/**
 * @author BHP_DEV service implementation for general functionalities
 *
 */
@Component
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	LookupDetailRepository lookupDetailRepository;

	@Autowired
	BaseService baseService;

	@Autowired
	MailService mailService;

	@Autowired
	VendorRepository vendorRepository;

	@Autowired
	CurrencyRepository currencyRepository;

	@Override
	public DataTablesOutput<PurchaseOrder> getAllPurchaseOrders(DataTablesInput input) {
		// Specification<PurchaseOrder> specification = new
		// Specification<PurchaseOrder>() {
		// @Override
		// public Predicate toPredicate(Root<PurchaseOrder> root,
		// CriteriaQuery<?> criteriaQuery,
		// CriteriaBuilder criteriaBuilder) {
		// Predicate inCurFinYear = criteriaBuilder.greaterThan(
		// root.< Date > get( "poDate" ), new Date(DateUtil.getFiscalYear()) );
		// return criteriaBuilder.and(inCurFinYear);
		// }
		// };
		DataTablesOutput<PurchaseOrder> dataTablesOutput = purchaseOrderRepository
				.findAll(input/* , specification */);
		return dataTablesOutput;
	}

	@Override
	public ResponseEntity<PurchaseOrder> getPurchaseOrderById(Long id) {
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id);
		if (purchaseOrder != null) {
			return new ResponseEntity<PurchaseOrder>(purchaseOrder, HttpStatus.OK);
		}
		return new ResponseEntity<PurchaseOrder>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Map<String, String>> saveOrUpdatePurchaseOrder(PurchaseOrder purchaseOrderObj) {
		PurchaseOrder purchaseOrder = null;
		Map<String, String> responseMap = new HashMap<>();
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		if (purchaseOrderObj.getId() == null) {
			purchaseOrder = addNewPurchaseOrder(loggedInUser, purchaseOrderObj);
		} else {
			purchaseOrder = updatePurchaseOrder(loggedInUser, purchaseOrderObj);
		}
		baseService.createNewTaskList(TaskConstants.PENDING_APPRVL_BY_CFO);
		if (purchaseOrder != null) {
			responseMap.put("Message", purchaseOrder.getVendor().getVendorName());
			return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.OK);
		}
		return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.NO_CONTENT);
	}

	private PurchaseOrder updatePurchaseOrder(User loggedInUser, PurchaseOrder purchaseOrderObj) {
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderObj.getId());
		purchaseOrder.setUpdateAttributes(loggedInUser);
		PurchaseOrder persisted = purchaseOrderRepository.save(purchaseOrder);
		return persisted;
	}

	private PurchaseOrder addNewPurchaseOrder(User loggedInUser, PurchaseOrder purchaseOrderObj) {
		purchaseOrderObj.setBaseAttributes(loggedInUser);
		purchaseOrderObj.setStatus(purchaseOrderObj.getStatus());
		purchaseOrderObj.setBalanceAmount(purchaseOrderObj.getPoValue());
		purchaseOrderObj.setBilledAmount(BigDecimal.ZERO);
		PurchaseOrder persisted = purchaseOrderRepository.save(purchaseOrderObj);
		return persisted;
	}

	@Override
	public ResponseEntity<Map<String, String>> approveOrRejectPurchaseOrder(ResponseObject requestObject) {
		LookupDetail statusLookup = null;
		Map<String, String> responseMap = new HashMap<>();
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(requestObject.getId());
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		if (purchaseOrder != null) {
			if (requestObject.isSuccess()) {
				statusLookup = lookupDetailRepository.findByLookupKey(TaskConstants.APPRVD_BY_CFO).get(0);
			} else {
				statusLookup = lookupDetailRepository.findByLookupKey(TaskConstants.RJCTD_BY_CFO).get(0);
			}
			// purchaseOrder.setStatus(statusLookup);
			purchaseOrder.setUpdateAttributes(loggedInUser);
			PurchaseOrder persisted = purchaseOrderRepository.saveAndFlush(purchaseOrder);
			try {
				mailService.sendVendorApprRejectMail(null,
						requestObject.isSuccess() ? "Approved successfully" : "Rejected successfully");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			responseMap.put("Message", requestObject.isSuccess() ? "Approved successfully" : "Rejected successfully");
			return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.OK);
		}
		return null;
	}

	@Override
	public ResponseEntity<Map<String, List<?>>> getDropDownData() {
		Map<String, List<?>> dropDownMap = new HashMap<>();
		List<Currency> currencies = currencyRepository.findAll();
		List<LookupDetail> poStatus = lookupDetailRepository.findByLookupKey(Constants.PO_STATUS);
		dropDownMap.put(Constants.CURRENCY, currencies);
		dropDownMap.put(Constants.PO_STATUS, poStatus);
		return new ResponseEntity<Map<String, List<?>>>(dropDownMap, HttpStatus.OK);
	}

	@Override
	public List<Vendor> getVendors(String vendorName) {
		return vendorRepository.findByVendorName(vendorName);
	}

}
