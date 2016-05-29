package com.belhopat.backoffice.service.impl;

import java.util.HashMap;
import java.util.Map;

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

import com.belhopat.backoffice.model.Client;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.ClientRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.ClientService;
import com.belhopat.backoffice.service.PointOfContactService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.sequence.SequenceGenerator;

/**
 * @author BHP_DEV Service layer to perform business operations of ClientEntity.
 *
 */
@Component
public class ClientServiceImpl implements ClientService {

	@Autowired
	BaseService baseService;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	PointOfContactService pointOfContactService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.belhopat.backoffice.service.ClientService#getClients
	 * (org.springframework.data.jpa.datatables.mapping.DataTablesInput) Gets
	 * the candidate with appropriate specifications.
	 * 
	 */
	@Override
	public DataTablesOutput<Client> getClients(DataTablesInput input) {

		Specification<Client> specification = new Specification<Client>() {
			@Override
			public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Predicate isNotDeleted = criteriaBuilder.equal(root.get("deleted"), false);
				return criteriaBuilder.and(isNotDeleted);
			}
		};
		DataTablesOutput<Client> dataTablesOutput = clientRepository.findAll(input, specification);
		return dataTablesOutput;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.belhopat.backoffice.service.ClientService#getClient
	 * (java.lang.Long) Returns the single client entity whose id is provided.
	 */
	@Override
	public ResponseEntity<Client> getClient(Long clientId) {
		Client client = clientRepository.findOne(clientId);
		if (client == null) {
			return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.belhopat.backoffice.service.ClientService#saveOrUpdateClient
	 * (com.belhopat.backoffice.model.Client) Save/Update Client to the
	 * database.
	 */
	@Override
	public ResponseEntity<Map<String, String>> saveOrUpdateClient(Client clientObj) {
		Map<String, String> responseMap = new HashMap<>();
		Client newClient = null;
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		if (clientObj.getId() == null) {
			newClient = registerNewClient(loggedInUser, clientObj);
		} else {
			newClient = updateClient(loggedInUser, clientObj);
		}
		if (newClient != null) {
			responseMap.put("Message ", newClient.getClientName() + " has been saved.");
			return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.OK);
		}
		return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.NO_CONTENT);
	}

	/**
	 * @param loggedInUser
	 * @param clientObj
	 * @return Newly persisted Client object.
	 */
	private Client registerNewClient(User loggedInUser, Client clientObj) {
		clientObj.setBaseAttributes(loggedInUser);
		Long increment = baseService.getSequenceIncrement(Client.class);
		String clientId = SequenceGenerator.generateCandidateId(increment);
		clientObj.setClientId(clientId);
		Client persisted = clientRepository.save(clientObj);
		return persisted;
	}

	/**
	 * @param loggedInUser
	 * @param clientObj
	 * @return Persisted client object with updated data.
	 */
	private Client updateClient(User loggedInUser, Client clientObj) {
		Client clientToUpdate = clientRepository.findOne(clientObj.getId());
		if (clientObj.getAccountManager() != null) {
			clientToUpdate.setAccountManager(clientObj.getAccountManager());
		}
		if (clientObj.getBussDManager() != null) {
			clientToUpdate.setBussDManager(clientObj.getBussDManager());
		}
		if (clientObj.getBussUnitHead() != null) {
			clientToUpdate.setBussUnitHead(clientObj.getBussUnitHead());
		}
		if (clientObj.getClientName() != null) {
			clientToUpdate.setClientName(clientObj.getClientName());
		}
		if (clientObj.getClientStatus() != null) {
			clientToUpdate.setClientStatus(clientObj.getClientStatus());
		}
		if (clientObj.getContactNo() != null) {
			clientToUpdate.setContactNo(clientObj.getContactNo());
		}
		if (clientObj.getEmailId() != null) {
			clientToUpdate.setEmailId(clientObj.getEmailId());
		}
		if (clientObj.getRevenue() != null) {
			clientToUpdate.setRevenue(clientObj.getRevenue());
		}
		if (clientObj.getWebUrl() != null) {
			clientToUpdate.setWebUrl(clientObj.getWebUrl());
		}
		clientToUpdate.setUpdateAttributes(loggedInUser);
		Client persisted = clientRepository.save(clientToUpdate);
		return persisted;
	}

}
