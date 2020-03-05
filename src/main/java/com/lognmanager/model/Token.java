package com.lognmanager.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "token")
public class Token {
	
	@Id
	private String token;
	private Date lastRequest;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getLastRequest() {
		return lastRequest;
	}
	public void setLastRequest(Date lastRequest) {
		this.lastRequest = lastRequest;
	}

}
