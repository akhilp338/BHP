package com.belhopat.backoffice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.repository.PointOfContactRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.PointOfContactService;

@Component
public class PointOfContactServiceImpl implements PointOfContactService{

	@Autowired
	BaseService baseService;
	
	@Autowired
	PointOfContactRepository pointOfContactRepository;
}
