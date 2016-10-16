package com.belhopat.backoffice.service;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.ModuleTab;

@Service
public interface LoginService {

	public List<ModuleTab> getUserTabs();

	public void logout(HttpServletRequest request) throws ServletException;

}
