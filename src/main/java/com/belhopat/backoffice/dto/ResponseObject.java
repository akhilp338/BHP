package com.belhopat.backoffice.dto;

/**
 * @author BHP_DEV
 * Generic response object
 *
 */
public class ResponseObject {

	private boolean success;

	private String data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ResponseObject(boolean success, String data) {
		this.success = success;
		this.data = data;
	}

	public ResponseObject() {
	}

}
