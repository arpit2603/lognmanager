package com.lognmanager.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lognmanager.conf.LoginConf;
import com.lognmanager.model.Token;
import com.lognmanager.repository.TokenRepository;
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
			long lastReq = tokenObj.getLastRequest();
			System.out.println("Last Request time = "+new Date(lastReq));
			long currentTime = new Date().getTime();
			System.out.println("Current Request time = "+new Date(currentTime));
			long difference = currentTime - lastReq;
			long diffMinutes = difference / (60 * 1000) % 60;
			if(diffMinutes<=10) {
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
	

}
