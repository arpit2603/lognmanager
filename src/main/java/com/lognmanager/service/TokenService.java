package com.lognmanager.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lognmanager.conf.LoginConf;
import com.lognmanager.model.Token;
import com.lognmanager.repository.TokenRepository;
import com.lognmanager.request.dto.TokenReqDto;
import com.lognmanager.response.dto.AppResponse;

@Service
public class TokenService {
	
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
				appResponse.setMessage("Authenticated.");
			}else {
				appResponse.setStatus(false);
				appResponse.setStatusCode(200);
				appResponse.setMessage("Token expired.");
			}
		}else {
			appResponse.setStatus(false);
			appResponse.setStatusCode(404);
			appResponse.setMessage("Unauthorized person.");
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
			appResponse.setMessage("Successfully updated.");
			appResponse.setStatusCode(200);
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage("Token not exists.");
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
			appResponse.setMessage("Successfully deleted.");
			appResponse.setStatusCode(200);
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage("Token not exist.");
			appResponse.setStatusCode(404);
		}
		return appResponse;
		
	}
}
