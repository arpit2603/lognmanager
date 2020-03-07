package com.lognmanager.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lognmanager.conf.LoginConf;
import com.lognmanager.model.Login;
import com.lognmanager.model.Token;
import com.lognmanager.repository.LoginRepository;
import com.lognmanager.repository.TokenRepository;
import com.lognmanager.request.dto.LoginRegisterReqDto;
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
			appResponse.setStatusCode(200);
			appResponse.setMessage("Successfully Login.");
		}else {
			appResponse.setStatus(false);
			appResponse.setStatusCode(404);
			appResponse.setMessage("User name or password not exist.");
		}
		return appResponse;
	}

	public AppResponse addLoginDetails(LoginRegisterReqDto loginRegisterReq) {
		AppResponse appResponse = loginConf.getAppResponse();
		Login loginDetails = loginConf.getObjectMapper().convertValue(loginRegisterReq, Login.class);
		loginDetails = lgnRepo.save(loginDetails);
		appResponse.setStatus(true);
		appResponse.setStatusCode(200);
		appResponse.setMessage("Successfully register.");
		return appResponse;
	}

	public AppResponse updateLoginDetails(LoginRegisterReqDto loginUpdateReq) {
		AppResponse appResponse = loginConf.getAppResponse();
		Login loginDetails = loginConf.getObjectMapper().convertValue(loginUpdateReq, Login.class);
		lgnRepo.save(loginDetails);
		appResponse.setStatus(true);
		appResponse.setStatusCode(200);
		appResponse.setMessage("Successfully updated.");
		return appResponse;
	}
}
