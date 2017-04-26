package com.belhopat.backoffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.ModuleTab;

@Service
public interface SettingsService {

	public Map<String, ?> getMasterRoles();

	public List<ModuleTab> getRoleTabs(Long masterRoleId);
}
