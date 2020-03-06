package com.lognmanager.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lognmanager.conf.LoginConf;
import com.lognmanager.model.Login;
import com.lognmanager.model.Token;
import com.lognmanager.repository.LoginRepository;
import com.lognmanager.repository.TokenRepository;
import com.lognmanager.request.dto.LoginReqDto;
import com.lognmanager.response.dto.AppResponse;
import com.lognmanager.response.dto.LoginResDto;

@Service
public class LoginService {
	
	@Autowired
	LoginRepository lgnRepo;
	
	@Autowired
	TokenRepository tokenRepo;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	LoginConf loginConf;
	
	public AppResponse getLoginDetails(LoginReqDto login) {
		AppResponse appResponse = loginConf.getAppResponse();
		Login loginInfo = lgnRepo.findByUserName(login.getUserName());
		if(loginInfo != null && loginInfo.getPassword().equals(login.getPassword())) {
			Token token = loginConf.getToken();
			LoginResDto loginResDto = loginConf.getObjectMapper().convertValue(loginInfo, LoginResDto.class);
			token.setLastRequest(new Date().getTime());
			token.setToken(commonService.getEncryptedPassword(loginInfo.getPassword()));
			loginResDto.setToken(token.getToken());
			tokenRepo.save(token);
			appResponse.setData(loginResDto);
			appResponse.setStatus(true);
			appResponse.setMessage("Successfully Login.");
			appResponse.setStatusCode(200);
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage("User name or password not exist.");
			appResponse.setStatusCode(404);
		}
		return appResponse;
	}

}
