package com.belhopat.backoffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.model.MasterRole;
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
	public List<MasterRole> getMasterRoles() {
		return settingsService.getMasterRoles();
	}

}
