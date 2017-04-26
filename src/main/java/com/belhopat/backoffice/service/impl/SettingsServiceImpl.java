package com.belhopat.backoffice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.dto.RoleTabDTO;
import com.belhopat.backoffice.model.MasterRole;
import com.belhopat.backoffice.model.ModuleTab;
import com.belhopat.backoffice.repository.MasterRoleRepository;
import com.belhopat.backoffice.repository.ModuleTabRepository;
import com.belhopat.backoffice.service.SettingsService;

/**
 * @author BHP_DEV service implementation for general functionalities
 */
@Component
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	MasterRoleRepository masterRoleRepository;

	@Autowired
	ModuleTabRepository moduleTabRepository;

	@Override
	public Map<String, ?> getMasterRoles() {
		Map<String, List<?>> map = new HashMap<>();
		map.put("roles", masterRoleRepository.findAll());
		map.put("tabs", moduleTabRepository.findAll());
		return map;
	}

	@Override
	public List<ModuleTab> getRoleTabs(Long masterRoleId) {
		List<ModuleTab> tabs = moduleTabRepository.findAll();
		List<ModuleTab> activeTabs = masterRoleRepository.findModuleTabsByMasterRoleId(masterRoleId);
		for (ModuleTab activeTab : activeTabs) {
			int index = tabs.indexOf(activeTab);
			if (index != -1) {
				tabs.get(index).setActive(true);
			}
		}
		return tabs;
	}

	@Override
	public ResponseObject saveActiveTabs(RoleTabDTO roleTab) {
		ResponseObject response = new ResponseObject();
		response.setSuccess(true);
		response.setData("Role Tabs Successfully Updated");
		MasterRole masterRole = masterRoleRepository.findById(roleTab.getMasterRoleId());
		List<ModuleTab> moduleTabs = moduleTabRepository.findByIds(roleTab.getActiveTabIds());
		masterRole.setModuleTabs(moduleTabs);
		masterRoleRepository.save(masterRole);
		return response;
	}

}
