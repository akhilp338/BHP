package com.belhopat.backoffice.dto;

/**
 * @author BHP_DEV Generic response object
 *
 */
public class ResponseObject {

	private Long id;

	private boolean success;

	private String data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public ResponseObject() {
	}

	public ResponseObject(boolean success, String data) {
		this.success = success;
		this.data = data;
	}

	public ResponseObject(Long id, boolean success, String data) {
		this.id = id;
		this.success = success;
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseObject [id=" + id + ", success=" + success + ", data=" + data + "]";
	}
}
