package com.belhopat.backoffice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.dto.RoleTabDTO;
import com.belhopat.backoffice.model.ModuleTab;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.SettingsService;

/**
 * @author Belhopat dev team Handler for all client related service calls
 *
 */
@Controller
@RequestMapping("/api/settings")
public class SettingsController {

	@Autowired
	BaseService baseService;

	@Autowired
	SettingsService settingsService;

	@ResponseBody
	@RequestMapping(value = "/getMasterRoles", method = RequestMethod.GET)
	public Map<String, ?> getMasterRoles() {
		return settingsService.getMasterRoles();
	}

	@ResponseBody
	@RequestMapping(value = "/getRoleTabs", method = RequestMethod.GET)
	public List<ModuleTab> getRoleTabs(Long masterRoleId) {
		return settingsService.getRoleTabs(masterRoleId);
	}

	@ResponseBody
	@RequestMapping(value = "/saveActiveTabs", method = RequestMethod.POST)
	public List<ModuleTab> saveActiveTabs(@RequestBody RoleTabDTO RoleTab) {
		return settingsService.saveActiveTabs(RoleTab);
	}

}
