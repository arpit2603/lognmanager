package com.lognmanager.conf;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.lognmanager.model.Token;
import com.lognmanager.response.dto.AppResponse;

@Configuration
public class LoginConf {
	
	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
	@Bean
	@Scope(scopeName = "prototype")
	public AppResponse getAppResponse() {
		return new AppResponse();
	}
	@Bean
	@Scope(scopeName = "prototype")
	public Token getToken() {
		return new Token();
	}

}
