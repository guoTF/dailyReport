package com.seda.dailyReport.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationDto {

	private boolean success = true;
	private String errorCode;
	private String errorMessage;
	private Object data;

	public OperationDto() {
		super();
	}

	public OperationDto(boolean isSuccess) {
		this.success = isSuccess;
	}

	public OperationDto success() {
		this.success = true;
		return this;
	}

	public OperationDto success(Object data) {
		this.success = true;
		this.data = data;
		return this;
	}

	public OperationDto fail() {
		this.success = false;
		return this;
	}

	public OperationDto fail(String errorCode) {
		this.success = false;
		this.errorCode = errorCode;
		return this;
	}

	public OperationDto fail(String errorCode, String errorMessage) {
		this.success = false;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		return this;
	}

	public OperationDto success(String errorCode, String errorMessage) {
		this.success = true;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		return this;
	}

	public OperationDto fail(String errorCode, String errorMessage, Object data) {
		this.success = false;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.data = data;
		return this;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
