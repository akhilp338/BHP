package com.belhopat.backoffice.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.ExcelService;
import com.belhopat.backoffice.session.SessionManager;

@Component
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	BaseService baseService;

	@Override
	public UploadResponse uploadExcel(String type, MultipartFile file) throws IOException {
		UploadResponse response = new UploadResponse();
		response = validateExcelFile(file);
		if (!response.isActionStatus()) {
			return response;
		}
		HSSFWorkbook workBook = getHSSFWorkbook(file);
		if (workBook == null) {
			response = getErrorResponse();
			return response;
		}
		switch (type) {
		case "LL":
			response = uploadLiquidityLimitsExcel(workBook);
			break;
		case "CPL":
			response = uploadLiquidityLimitsExcel(workBook);
			break;
		case "CCL":
			response = uploadLiquidityLimitsExcel(workBook);
			break;
		case "GP":
			response = uploadLiquidityLimitsExcel(workBook);
			break;
		case "CRM":
			response = uploadLiquidityLimitsExcel(workBook);
			break;
		}
		return response;
	}

	private HSSFWorkbook getHSSFWorkbook(MultipartFile file) throws IOException {
		HSSFWorkbook workBook = new HSSFWorkbook(new ByteArrayInputStream(file.getBytes()));
		return workBook;

	}

	private UploadResponse validateExcelFile(MultipartFile multipartFile) throws IOException {
		UploadResponse response = getErrorResponse();
		if (!multipartFile.isEmpty()) {
			if (multipartFile.getOriginalFilename().endsWith("XLS")) {
				response = getSuccessResponse();
			} else {
				response.setStatusMessage("Select XLS File");
			}
		} else {
			response.setStatusMessage("Selected File is Empty");
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	private UploadResponse uploadLiquidityLimitsExcel(HSSFWorkbook workBook) throws IOException {
		UploadResponse response = getErrorResponse();
//		User loggedInUser = SessionManager.getCurrentUserAsEntity();
//		try {
//			String noOfDays = baseService.getNoOfDays();
//			int intDays = Integer.parseInt(noOfDays);
//			AttendanceExcelParser llExcelParser = new AttendanceExcelParser(workBook,
//					liquiditySourceLimitViewRepository);
//			response = llExcelParser.getParsedData(intDays);
//			if (response.isActionStatus()) {
//				List<LiquiditySourcesLimitViewEntity> lsLimits = (List<LiquiditySourcesLimitViewEntity>) response
//						.getList();
//				List<LiquiditySourcesLimitViewEntity> lsLimitsToAdd = new ArrayList<>();
//				List<LiquiditySourcesLimitViewEntity> lsLimitsToEdit = new ArrayList<>();
//				for (LiquiditySourcesLimitViewEntity lsLimit : lsLimits) {
//					if (lsLimit.getId() == null) {
//						lsLimitsToAdd.add(lsLimit);
//					} else {
//						lsLimitsToEdit.add(lsLimit);
//					}
//				}
//				ResponseObject responseObj = null;
//				if (!lsLimitsToEdit.isEmpty()) {
//					responseObj = adminService.updateLiquidityLimits(lsLimitsToEdit, loggedInUser);
//				}
//				if (!lsLimitsToAdd.isEmpty()) {
//					for (LiquiditySourcesLimitViewEntity lsLimit : lsLimitsToAdd) {
//						responseObj = adminService.addLiquiditySource(lsLimit, loggedInUser);
//					}
//				}
//				response.setActionStatus(responseObj.isActionStatus());
//				response.setStatusMessage(responseObj.getStatusMessage());
//				response.setStatus(responseObj.getComment());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return response;
	}

	private UploadResponse getSuccessResponse() {
		UploadResponse response = new UploadResponse();
		response.setStatus("success");
		response.setActionStatus(true);
		return response;
	}

	private UploadResponse getErrorResponse() {
		UploadResponse response = new UploadResponse();
		response.setStatusMessage("Unexpected Error");
		response.setStatus("error");
		response.setActionStatus(false);
		return response;
	}

}
