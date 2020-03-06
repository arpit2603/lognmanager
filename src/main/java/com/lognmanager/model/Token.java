package com.lognmanager.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "token")
public class Token {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tokenId;
	
	
	@Lob
	@Column(unique = true)
	private String token;
	
	private long lastRequest;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getLastRequest() {
		return lastRequest;
	}
	public void setLastRequest(long lastRequest) {
		this.lastRequest = lastRequest;
	}

}
