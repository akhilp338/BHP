package com.belhopat.backoffice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.RoleTab;

@Service
public interface LoginService {

	public List<RoleTab> getUserTabs();

}
