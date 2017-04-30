package com.belhopat.backoffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.dto.RoleTabDTO;
import com.belhopat.backoffice.model.ModuleTab;

@Service
public interface SettingsService {

	public Map<String, ?> getMasterRoles();

	public List<ModuleTab> getRoleTabs(Long masterRoleId);

	public ResponseObject saveActiveTabs(RoleTabDTO roleTab);
}
