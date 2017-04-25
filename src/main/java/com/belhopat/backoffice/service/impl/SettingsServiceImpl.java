package com.belhopat.backoffice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.model.MasterRole;
import com.belhopat.backoffice.repository.MasterRoleRepository;
import com.belhopat.backoffice.service.SettingsService;

/**
 * @author BHP_DEV service implementation for general functionalities
 */
@Component
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	MasterRoleRepository masterRoleRepository;

	@Override
	public List<MasterRole> getMasterRoles() {
		return masterRoleRepository.findAll();
	}

}
