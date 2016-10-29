package com.belhopat.backoffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.RequestObject;
import com.belhopat.backoffice.model.State;
import com.belhopat.backoffice.service.BaseService;

/**
 * @author Belhopat dev team Handler for all consultant related service calls
 *
 */
@Controller
@RequestMapping("/api/consultant")
public class ConsultantController {

	@Autowired
	BaseService baseService;



}
