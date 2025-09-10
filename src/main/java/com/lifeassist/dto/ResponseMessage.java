package com.lifeassist.dto;

import lombok.Data;

@Data
public class ResponseMessage {
	
	
	private Integer statusCode;
	private String status;
	private String message;
	private Object data;
	
	public ResponseMessage(Integer statusCode, String status, String message, Object data) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public ResponseMessage(Integer statusCode, String status, String message) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
	}

}
