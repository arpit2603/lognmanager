package com.lognmanager.request.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;
@Component
public class LoginReqDto {
	
	@NotEmpty(message = "{username.empty}")
	private String userName;
	@NotEmpty(message = "{password.empty}")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
