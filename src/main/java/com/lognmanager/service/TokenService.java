package com.lognmanager.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lognmanager.conf.LoginConf;
import com.lognmanager.conf.Messages;
import com.lognmanager.model.Token;
import com.lognmanager.repository.TokenRepository;
import com.lognmanager.request.dto.TokenReqDto;
import com.lognmanager.response.dto.AppResponse;

@Service
public class TokenService {
	
	@Autowired
	Messages messages;
	
	@Autowired
	TokenRepository tokenRepo;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	LoginConf loginConf;
	
	public AppResponse isExpired(String token) {
		AppResponse appResponse = loginConf.getAppResponse();
		Token tokenObj = tokenRepo.findByToken(token);
		if(tokenObj != null) {
			int tokenTime = loginConf.getTokenTime();
			long lastReq = tokenObj.getLastRequest();
			long currentTime = new Date().getTime();
			long difference = currentTime - lastReq;
			long diffMinutes = difference / (60 * 1000) % 60;
			if(diffMinutes<=tokenTime) {
				appResponse.setStatus(true);
				appResponse.setStatusCode(200);
				appResponse.setMessage(messages.get("token.authenticate"));
			}else {
				appResponse.setStatus(false);
				appResponse.setStatusCode(200);
				appResponse.setMessage(messages.get("token.expire.error"));
			}
		}else {
			appResponse.setStatus(false);
			appResponse.setStatusCode(404);
			appResponse.setMessage(messages.get("token.authenticate.error"));
		}
		return appResponse;
	}
	
	public AppResponse updateToken(TokenReqDto tokenReq) {
		AppResponse appResponse = loginConf.getAppResponse();
		Token token = tokenRepo.findByToken(tokenReq.getToken());
		if(token != null) {
			token.setLastRequest(new Date().getTime());
			tokenRepo.save(token);
			appResponse.setStatus(true);
			appResponse.setMessage(messages.get("token.update"));
			appResponse.setStatusCode(200);
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(messages.get("token.authenticate.error"));
			appResponse.setStatusCode(404);
		}
		return appResponse;
	}

	@Transactional
	public AppResponse deleteToken(String token) {
		AppResponse appResponse = loginConf.getAppResponse();
		long deletedToken = tokenRepo.deleteByToken(token);
		if(deletedToken > 0) {
			appResponse.setStatus(true);
			appResponse.setMessage(messages.get("token.delete"));
			appResponse.setStatusCode(200);
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(messages.get("token.authenticate.error"));
			appResponse.setStatusCode(404);
		}
		return appResponse;
		
	}
}
