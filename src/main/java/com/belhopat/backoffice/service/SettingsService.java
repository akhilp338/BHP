package com.belhopat.backoffice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.MasterRole;

@Service
public interface SettingsService {

	public List<MasterRole> getMasterRoles();
}
