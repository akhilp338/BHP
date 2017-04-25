package com.belhopat.backoffice.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface SettingsService {

	public Map<String, ?> getMasterRoles();
}
