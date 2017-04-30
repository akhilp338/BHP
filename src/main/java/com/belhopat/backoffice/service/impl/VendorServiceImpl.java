package com.belhopat.backoffice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.model.Lookup;
import com.belhopat.backoffice.model.LookupDetail;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.model.Vendor;
import com.belhopat.backoffice.repository.CountryRepository;
import com.belhopat.backoffice.repository.LookupDetailRepository;
import com.belhopat.backoffice.repository.LookupRepository;
import com.belhopat.backoffice.repository.TimeZoneRepository;
import com.belhopat.backoffice.repository.UserRepository;
import com.belhopat.backoffice.repository.VendorRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.MailService;
import com.belhopat.backoffice.service.VendorService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.Constants;
import com.belhopat.backoffice.util.TaskConstants;
import com.belhopat.backoffice.util.sequence.SequenceGenerator;

/**
 * @author BHP_DEV service implementation for general functionalities
 *
 */
@Component
public class VendorServiceImpl implements VendorService {
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	LookupDetailRepository lookupDetailRepository;
	
	@Autowired
	LookupRepository lookupRepository;
	
	@Autowired
	BaseService baseService;

	@Autowired
	MailService mailService;
        
    @Autowired
    UserRepository userRepository;
    
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	TimeZoneRepository timezoneRepository;
        
	
	

	@Override
	public DataTablesOutput<Vendor> getApprovedVendors(DataTablesInput input) {
		Specification<Vendor> specification = new Specification<Vendor>() {
			@Override
			public Predicate toPredicate(Root<Vendor> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Predicate isApprovedByCFO = null;
				User loggedInUser = SessionManager.getCurrentUserAsEntity();
                                loggedInUser = userRepository.findOne(loggedInUser.getId());
				if(loggedInUser.getPrimaryRole().equals("FM")){
					isApprovedByCFO = criteriaBuilder.equal(root.get("status").get("code"), TaskConstants.APPRVD_BY_CFO);
				}else{
					isApprovedByCFO = criteriaBuilder.equal(root.get("status").get("code"), "PENDING_APPROVAL");
				}
				return criteriaBuilder.and(isApprovedByCFO);
			}
		};
		DataTablesOutput<Vendor> dataTablesOutput = vendorRepository.findAll(input);
		return dataTablesOutput;
	}



	@Override
	public ResponseEntity<Vendor> getVendorById(Long id) {
		Vendor vendor = vendorRepository.findById(id);
		if (vendor != null) {
			return new ResponseEntity<Vendor>(vendor, HttpStatus.OK);
		}
		return new ResponseEntity<Vendor>(HttpStatus.NO_CONTENT);
	}



	@Override
	public ResponseEntity<Map<String, String>> saveOrUpdateVendor(Vendor vendorObj) {
		Vendor vendor = null;
		Map<String, String> responseMap = new HashMap<>();
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		if (vendorObj.getId() == null) {
			vendor = addNewVendor(loggedInUser, vendorObj);
		} else {
			vendor = updateVendor(loggedInUser, vendorObj);
		}
		baseService.createNewTaskList(TaskConstants.PENDING_APPRVL_BY_CFO,null);
		if (vendor != null) {
			responseMap.put("Message", vendor.getVendorName());
			return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.OK);
		}
		return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.NO_CONTENT);
	}
	
	private Vendor updateVendor(User loggedInUser, Vendor vendorObj) {
		Vendor vendor = vendorRepository.findById(vendorObj.getId());
		vendor.setUpdateAttributes(loggedInUser);
		Vendor persisted = vendorRepository.save(vendor);
		return persisted;
	}



	private Vendor addNewVendor(User loggedInUser, Vendor vendorObj) {
		Long increment = baseService.getSequenceIncrement( vendorObj.getClass() );
		String vendorCode = SequenceGenerator.generateVendorId(increment);
		LookupDetail statusLookup = lookupDetailRepository.findByLookupKey("PNDNG_APPRVL").get(0);
		vendorObj.setVendorCode(vendorCode);
		vendorObj.setStatus(statusLookup);
		vendorObj.setBaseAttributes(loggedInUser);
		Vendor persisted = vendorRepository.save(vendorObj);
		return persisted;
	}



	@Override
	public ResponseEntity<Map<String, String>> approveOrRejectVendor(ResponseObject requestObject) {
		LookupDetail statusLookup = null;
		Map<String, String> responseMap = new HashMap<>();
		Vendor vendor = vendorRepository.findById(requestObject.getId());
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		if (vendor != null) {
			if (requestObject.isSuccess()) {
				statusLookup = lookupDetailRepository.findByLookupKey(TaskConstants.APPRVD_BY_CFO).get(0);
			} else {
				statusLookup = lookupDetailRepository.findByLookupKey(TaskConstants.RJCTD_BY_CFO).get(0);
			}
			vendor.setStatus(statusLookup);
			vendor.setUpdateAttributes(loggedInUser);
			Vendor persisted = vendorRepository.saveAndFlush(vendor);
			try {
				mailService.sendVendorApprRejectMail(persisted,
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
	public Map<String, List<?>> getDropDownData() {
		Map <String,List<?>> dropDownMaps = new HashMap<String,List<?>>(); 
		dropDownMaps.put(Constants.COUNTRY, countryRepository.findAll());
		dropDownMaps.put(Constants.TIMEZONE, timezoneRepository.findAll());
//		Lookup vendorLkup = lookupRepository.findByLookupKey(Constants.VENDOR_CATEGORY);
//		dropDownMaps.put(Constants.VENDOR_CATEGORY, lookupDetailRepository.findByLookup(vendorLkup));
		dropDownMaps.put(Constants.VENDOR_CATEGORY, lookupDetailRepository.findByLookupKey(Constants.VENDOR_CATEGORY));
		dropDownMaps.put(Constants.VENDOR_STATUS, lookupDetailRepository.findByLookupKey(Constants.VENDOR_STATUS));
		dropDownMaps.put(Constants.VENDOR_RATING, lookupDetailRepository.findByLookupKey(Constants.VENDOR_RATING));
		dropDownMaps.put(Constants.TDS_CATEGORY, lookupDetailRepository.findByLookupKey(Constants.TDS_CATEGORY));
		dropDownMaps.put(Constants.POC_DESGN, lookupDetailRepository.findByLookupKey(Constants.POC_DESGN));
		return dropDownMaps;
	}
	
}
