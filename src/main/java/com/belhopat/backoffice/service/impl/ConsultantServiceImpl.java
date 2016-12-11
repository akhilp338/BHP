package com.belhopat.backoffice.service.impl;

import java.util.HashMap;
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

import com.belhopat.backoffice.model.BankAccount;
import com.belhopat.backoffice.model.Consultant;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.ConsultantRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.ConsultantService;
import com.belhopat.backoffice.service.MailService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.sequence.SequenceGenerator;

/**
 * @author BHP_DEV Service layer to implement consultant business
 */
@Component
public class ConsultantServiceImpl implements ConsultantService {

	@Autowired
	MailService mailService;
	
	@Autowired
	BaseService baseService;

	@Autowired
	ConsultantRepository consultantRepository;

	
	/* (non-Javadoc)
	 * @see com.belhopat.backoffice.service.ConsultantService#getConsultants
	 * (org.springframework.data.jpa.datatables.mapping.DataTablesInput)
	 * fetches the list of consultant records with matching specifications
	 */
	@Override
	public DataTablesOutput< Consultant > getConsultants( DataTablesInput input ) {

		Specification<Consultant> specification = new Specification<Consultant>() {
			@Override
			public Predicate toPredicate(Root<Consultant> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Predicate isNotDeleted = criteriaBuilder.equal( root.get("deleted"), false );
				return criteriaBuilder.and( isNotDeleted );
			}
		};
		DataTablesOutput<Consultant> dataTablesOutput = consultantRepository.findAll(input, specification);
		return dataTablesOutput;
	}
	
	/* (non-Javadoc)
	 * @see com.belhopat.backoffice.service.ConsultantService#getConsultant(java.lang.Long)
	 * gets single consultant entity from database with matching id
	 */
	@Override
	public ResponseEntity< Consultant > getConsultant( Long consultantId ) {
		Consultant consultant = consultantRepository.findOne( consultantId );
		if (consultant != null) {
			return new ResponseEntity< Consultant >( consultant, HttpStatus.OK);
		}
		return new ResponseEntity< Consultant >(HttpStatus.NO_CONTENT);
	}
	
	/* (non-Javadoc)
	 * @see com.belhopat.backoffice.service.ConsultantService#saveOrUpdateConsultant(com.belhopat.backoffice.model.Consultant)
	 * saves or updates the consultant to the database
	 */
	@Override
	public ResponseEntity< Map< String, String >> saveOrUpdateConsultant( Consultant consultantObj ) {
		Map< String, String > responseMap = new HashMap<>();
		Consultant consultant = null;
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		if (consultantObj.getId() == null) {
			consultant = addConsultant( loggedInUser, consultantObj );
		} else {
			consultant = updateConsultant( loggedInUser, consultantObj );
		}
		if (consultant != null) {
			responseMap.put( "Message", consultant.getFullName() );
			return new ResponseEntity< Map< String, String >>( responseMap, HttpStatus.OK );
		}
		return new ResponseEntity< Map< String, String >>( responseMap, HttpStatus.NO_CONTENT );
	}
	
	/**
	 * @param loggedInUser
	 * @param consultant
	 * @return Consultant
	 * saves a new consultant to database
	 */
	private Consultant addConsultant( User loggedInUser, Consultant consultant ) {
		consultant.setBaseAttributes(loggedInUser);
		Long increment = baseService.getSequenceIncrement( consultant.getClass() );
		String consultantId = SequenceGenerator.generateConsultantId(increment);
		consultant.setConsultantId( consultantId );
		Consultant persisted = consultantRepository.save( consultant );
		try{
			mailService.sendConsultantRegMail( persisted );
		}catch(MessagingException e){
			e.printStackTrace();
		}
		return persisted;
	}

	/**
	 * @param loggedInUser
	 * @param consultantObj
	 * @return Consultant
	 * sets the dto to entity
	 */
	private Consultant updateConsultant( User loggedInUser, Consultant consultantObj) {
		Consultant newConsultant = consultantRepository.findOne( consultantObj.getId() );
		newConsultant = getUpdatedWithBaseAttributes( consultantObj, newConsultant );
		if( consultantObj.getBankAccount() != null) {
			newConsultant = setBankAccountInDetail(consultantObj.getBankAccount(), newConsultant);
		}
		if(consultantObj.getCurrentAddress() != null) {
			newConsultant.setCurrentAddress(consultantObj.getCurrentAddress());
		}
		if( consultantObj.getDesignation() != null) {
			newConsultant.setDesignation(consultantObj.getDesignation());
		}
		if( consultantObj.getOnsiteAddress() != null) {
			newConsultant.setOnsiteAddress(consultantObj.getOnsiteAddress());
		}
		if(consultantObj.getPassport() != null) {
			newConsultant.setPassport(consultantObj.getPassport());
		}
		if(consultantObj.getPermanentAddress() != null) {
			newConsultant.setPermanentAddress(consultantObj.getPermanentAddress());
		}
		if(consultantObj.getSkillSet() != null ) {
			newConsultant.setSkillSet(consultantObj.getSkillSet());
		}
		newConsultant.setUpdateAttributes( loggedInUser );
		Consultant persisted = consultantRepository.save( newConsultant );
		return persisted;
	}
	
	/**
	 * @param consultantObj
	 * @param newConsultant
	 * @return Consultant
	 * sets the base attributes
	 */
	private Consultant getUpdatedWithBaseAttributes( Consultant consultantObj, Consultant newConsultant ) {
		newConsultant.setFullName( consultantObj.getFullName() );
		newConsultant.setGender( consultantObj.getGender());
		newConsultant.setPersonalEmail( consultantObj.getPersonalEmail());
		newConsultant.setPersonalContactNo( consultantObj.getPersonalContactNo());
		newConsultant.setFamilyContact1( consultantObj.getFamilyContact1());
		newConsultant.setFamilyContact2( consultantObj.getFamilyContact2());
		newConsultant.setFamilyEmail( consultantObj.getFamilyEmail());
		newConsultant.setOnsiteContactNo( consultantObj.getOnsiteContactNo());
		newConsultant.setPriorExperienceMonth( consultantObj.getPriorExperienceMonth());
		newConsultant.setPriorExperienceYear( consultantObj.getPriorExperienceYear());
		newConsultant.setSourcedBy( consultantObj.getSourcedBy());
		newConsultant.setDob( consultantObj.getDob());
		newConsultant.setDoj( consultantObj.getDoj());
		newConsultant.setAccountManager( consultantObj.getAccountManager() );
		newConsultant.setBussUnitHead( consultantObj.getBussUnitHead() );
		newConsultant.setStatus( consultantObj.getStatus() );
		newConsultant.setWorkLocation( consultantObj.getWorkLocation() );
		newConsultant.setCountryOfOrigin( consultantObj.getCountryOfOrigin() );
		return newConsultant;
	}
	
	/**
	 * @param bankAccount
	 * @param newConsultant
	 * @return Consultant
	 * sets the account details
	 */
	private Consultant setBankAccountInDetail( BankAccount bankAccount, Consultant newConsultant ) {
		if ( newConsultant.getBankAccount() == null ) {
			newConsultant.setBankAccount( bankAccount );
			newConsultant.getBankAccount().setAccountHolderName( bankAccount.getAccountHolderName() );
			newConsultant.getBankAccount().setAccountNo( bankAccount.getAccountNo() );
			newConsultant.getBankAccount().setBankAddress( bankAccount.getBankAddress() );
			newConsultant.getBankAccount().setBankName( bankAccount.getBankName() );
			newConsultant.getBankAccount().setBranch( bankAccount.getBranch() );
			newConsultant.getBankAccount().setIFSCCode( bankAccount.getIFSCCode() );
			newConsultant.getBankAccount().setSwiftCode( bankAccount.getSwiftCode() );
			newConsultant.getBankAccount().setInternationalBankAccountNo( bankAccount.getInternationalBankAccountNo() );
		} else {
			newConsultant.setBankAccount( bankAccount );
		}
		return newConsultant;
	}
	
}
