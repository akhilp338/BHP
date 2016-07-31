package com.belhopat.backoffice.service;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.RoleTab;

@Service
public interface LoginService {

	public List<RoleTab> getUserTabs();

	public void logout(HttpServletRequest request) throws ServletException;

}
