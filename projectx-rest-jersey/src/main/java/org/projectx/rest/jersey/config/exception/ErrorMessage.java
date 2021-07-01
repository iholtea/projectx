package org.projectx.rest.jersey.config.exception;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	
	private String message;
	private String code;
	private String documentation;
	
	public ErrorMessage() {}
	
	public ErrorMessage(String message, String code, String documentation) {
		this.message = message;
		this.code = code;
		this.documentation = documentation;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	
}
